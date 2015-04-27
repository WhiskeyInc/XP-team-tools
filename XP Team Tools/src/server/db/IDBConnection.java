package server.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBConnection {

	/**
	 * TODO abstract?
	 * @param user
	 * @param pass
	 * @param port
	 * @param host
	 * @param dbName TODO
	 * @return Connection An open connection to database
	 * @throws Exception
	 * @throws SQLException
	 */
	public abstract Connection connect(String user, String pass, int port, String host, String dbName) throws Exception;

	public abstract Connection getConnection();
}
