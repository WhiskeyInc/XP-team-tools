package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

/**
 * A general server with the possibility to add services, it needs a client
 * manager to manage all the connecting clients
 * Implementation of the "design pattern strategy"
 * @author nicola, Alberto
 *
 */
public class ServerStrategy extends AbstractServer {

	private HashMap<Integer, IService> services = new HashMap<Integer, IService>();

	//private HashMap<String, List<Socket>> clientMap;

	private Socket clientSocket;
	private ClientsManager clientsManager;

	private BufferedReader in;

	
	public ServerStrategy(ClientsManager clientsManager) {
		super();

		this.clientsManager = clientsManager;

		//clientMap = clientsManager.getMap();
	}

	@Override
	public void listenClients() {

		try {

			while (true) {

				clientSocket = serverSocket.accept();
				setUpStream();
				clientsManager.handleClient(clientSocket, getTeamName());

				Runnable runnable = generateRunnable();
				Thread thread = new Thread(runnable);
				thread.start();

			}
		} catch (IOException e) {

		}
	}

	private String getTeamName() throws IOException {
		return getLine(in); // TODO
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

	public void addService(int timer, IService service) {
		services.put(timer, service);
	}

}
