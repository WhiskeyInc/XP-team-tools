package tests.auth;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import server.db.DBConnection;
import server.db.IDBConnection;
import server.utils.auth.CreateUser;

public class CreateUserMainTest {
	public static void main(String[] args) {

		IDBConnection db = new DBConnection();

		try {
			db.connect("root", "", 3307, "localhost", "extreme01");
		} catch (Exception e) {
			e.printStackTrace();
		}

		CreateUser user = new CreateUser();
		
		try {
			if (user.createUser(db.getConnection(), "Alb", "Alb123")) {
				System.out.print("Utente creato");
			}else {
				System.out.print("Utete NON creato");
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
