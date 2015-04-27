package server.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBConnection implements IDBConnection {

	private final MysqlDataSource dataSource;
	private Connection connection;

	public DBConnection() {
		this.dataSource = new MysqlDataSource();
	}

	@Override
	public Connection connect(String user, String pass, int port, String host, String dbName)
			throws Exception, SQLException {

		dataSource.setUser(user);
		dataSource.setPassword(pass);
		dataSource.setPort(port);
		dataSource.setServerName(host);
		dataSource.setDatabaseName(dbName);

		connection = dataSource.getConnection();

		return connection;
	}

	@Override
	public Connection getConnection() {

		return connection;
	}

}
