package server.model.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import protocol.JsonMaker;
import protocol.JsonParser;
import server.db.DBConnection;
import server.db.IDBConnection;
import server.model.propagator.ClientsManager;
import server.model.propagator.MessagePropagator;
import server.utils.auth.Authenticate;
import client.model.ClientDetails;

/**
 * A Service providing the authorization and authentication control for
 * accessing other Services and permanent connection to the Server.
 * 
 * @author pavlo
 *
 */

public class AuthService implements IService {

	private volatile ClientsManager clientsManager;
	private volatile MessagePropagator messagePropagator;
	private Authenticate auth = new Authenticate();
	private IDBConnection db = new DBConnection();

	public AuthService(ClientsManager clientsmanager,
			MessagePropagator propagator, IDBConnection db) {
		this.clientsManager = clientsManager;
		this.messagePropagator = propagator;
		this.db = db;

	}

	@Override
	public void doAction(String line) throws IOException, ParseException {
		ClientDetails det = JsonParser.parseConnectToServerRequest(line);

		try {
			if (!authenticate(det.getNickname(), det.getPwd())) {

				System.out.println(JsonMaker.disconnectRequestByServer(false));
				messagePropagator.propagateMessage(
						JsonMaker.disconnectRequestByServer(false), det);

			} else {
				System.out.println(JsonMaker.disconnectRequestByServer(true));
				messagePropagator.propagateMessage(
						JsonMaker.disconnectRequestByServer(true), det);

			}

		} catch (NoSuchAlgorithmException | SQLException e1) {
			//TODO auto-generated

			e1.printStackTrace();
		}

	}

	private boolean authenticate(String nickname, String pwd)
			throws IOException, NoSuchAlgorithmException, SQLException { // TODO

		boolean autheniticated = auth.authenticate(db.getConnection(),
				nickname, pwd);

		return autheniticated;
	}

}
