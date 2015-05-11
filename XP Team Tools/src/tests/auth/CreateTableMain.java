package tests.auth;

import java.sql.SQLException;

import server.db.DBConnection;
import server.db.IDBConnection;
import server.utils.auth.Authenticate;

public class CreateTableMain {

	public static void main(String[] args) {
		IDBConnection db = new DBConnection();

		try {
			db.connect("alemonta", "protgamba", 3306, "52.74.20.119",
					"extreme01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Authenticate auth = new Authenticate();
		try {
			auth.createTable(db.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
