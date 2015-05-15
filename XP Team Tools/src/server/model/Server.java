package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.parser.ParseException;

import protocol.JsonParser;
import server.model.propagator.ClientsManager;
import server.model.services.IService;
import client.model.ClientConnectionDetails;
import client.model.ClientDetails;

/**
 * A generic server with the possibility to add services, it needs a client
 * manager to manage all the connecting clients
 * Implementation of the design pattern "strategy"
 * @author nicola, Alberto
 *
 */
public class Server extends AbstractServer {

	private HashMap<Integer, IService> services = new HashMap<Integer, IService>();

	private Socket clientSocket;
	private Socket requestSocket;

	private volatile ClientsManager clientsManager1;
	private BufferedReader in;
	
	public Server(ClientsManager clientsManager1) {
		super();
		this.clientsManager1 = clientsManager1;
		
	}

	@Override
	public void listenClients() {

		try {

			while (true) {

				clientSocket = serverSocket.accept();
				requestSocket = serverSocket.accept();
				System.out.println("Chat socket: " + clientSocket.getLocalAddress()+ " " + clientSocket.getPort()+ " " + Server.class);
				System.out.println("Request socket: " + requestSocket.getLocalAddress()+ " " + requestSocket.getPort()+ " " + Server.class);

				
				setUpStream();
				
					try {
						ClientDetails det = JsonParser.parseConnectToServerRequest(getLine(in));
						ClientConnectionDetails cDet = new ClientConnectionDetails(det.getNickname(), det.getTeamName(), det.getPwd());
						cDet.setOnline(true);
						cDet.setRealTimeSocket(clientSocket);
						cDet.setRequestSocket(requestSocket);
						try {
							clientsManager1.registerClient(cDet);
						} catch (NoSuchAlgorithmException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Iterator<ClientConnectionDetails> iter = clientsManager1.getClients().iterator();
						while(iter.hasNext()) {
							System.err.println(iter.next().getNickname() + " " + Server.class);

						}
						System.err.println(clientsManager1.size() + " SIZE " + Server.class);

					} catch (ParseException e) {
						e.printStackTrace();
					}
				Runnable runnable = generateRunnable();
				Thread thread = new Thread(runnable);
				thread.start();

			}
		} catch (IOException e) {

		}
	}

	private String getLine(BufferedReader in) throws IOException {
		return in.readLine();
	}

	private void setUpStream() throws IOException {
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
	}

	private Runnable generateRunnable() {
		Runnable runnable = new Runnable() {
			final BufferedReader input = in;

			@Override
			public void run() {
				while (true) {
					try {

						String line = getLine(input);
						if (line != null) {
							IService service = services.get(JsonParser
									.getRequest(line));

							// TODO controllare se service non c'è... gestire
							if (service != null) {
								service.doAction(line);
								System.out.println(service + " " + Server.class);
							}

						}
					} catch (Exception e) {
//						clientMap.remove(clientMap.get("Prova"));
						e.printStackTrace();
					}
				}
			}

		};
		return runnable;
	}

	/**
	 * add a new Service to this server
	 * @param request the key of the service
	 * @param service a @IService
	 */
	public void addService(int request, IService service) {
		services.put(request, service);
	}
	
	public synchronized ClientsManager getClientsManager() {
		return clientsManager1;
	}

}
