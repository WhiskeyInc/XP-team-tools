package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * A general server with the possibility to add services, it needs a client
 * manager to manage all the connecting clients Implementation of the
 * "design pattern strategy"
 * 
 * @author Nicola, Alberto
 */
public class ServerStrategyAuth extends AbstractServer {

	private HashMap<Integer, IService> services = new HashMap<Integer, IService>();

	private Socket clientSocket;
	private ClientsManagerAuth clientsManager;

	private BufferedReader in;

	public ServerStrategyAuth(ClientsManagerAuth clientsManager) {
		super();
		this.clientsManager = clientsManager;
	}

	@Override
	public void listenClients() {

		try {

			while (true) {

				clientSocket = serverSocket.accept();
				setUpStream();
				try {
					clientsManager.setAuth(getAuthData());
					clientsManager.handleClient(clientSocket, getTeamName());
				} catch (NoSuchAlgorithmException | SQLException e) {
					// TODO Auto-generated catch block
					System.err
							.println("Problem about handling clients (getAuth stuff)");
					e.printStackTrace();
				}

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

	private String getAuthData() throws IOException {
		return getLine(in); // TODO come sopra
	}

	private String getLine(BufferedReader in) throws IOException {
		String t = "";

		try {
			t = in.readLine();
		} catch (Exception e) {
			clientsManager.removeClient(clientSocket);
			e.printStackTrace();
		}

		return t;
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
						e.printStackTrace();
					}
				}
			}

		};
		return runnable;
	}

	/**
	 * Adds a service to the server
	 * 
	 * @param code
	 *            Code of the service, is one of the {@link JsonParser}
	 *            constants
	 * @param service
	 *            Implementation of the servise to add
	 */
	public void addService(int code, IService service) {
		services.put(code, service);
	}

}
