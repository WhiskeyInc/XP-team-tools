package server.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * An interface with abstract methods for the connection to database
 * 
 *
 */
public interface IDBConnection {

	/**
	 * An abstract method for the connection to database, he receives in input all the paramaters 
	 * required for the connection 
	 * @param user
	 * @param pass
	 * @param port
	 * @param host
	 * @param dbName
	 * @return a statement (@Connection) object for the sending of 
	 * SQL stataments to database
	 * @throws Exception
	 */
	public abstract Connection connect(String user, String pass, int port, String host, String dbName) throws Exception;

	/**
	 * An abstract method that returns the @Connection, necessary for authentication operations
	 * @return
	 */
	public abstract Connection getConnection();
}
