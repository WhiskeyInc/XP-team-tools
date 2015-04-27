package tests.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLAccessTestable {

	private Connection connection;
	private Statement statement;
	private PreparedStatement ps;
	private ResultSet resultSet;

	public MySQLAccessTestable() throws Exception {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("");
		dataSource.setPort(3307);
		dataSource.setServerName("localhost");

		this.connection = dataSource.getConnection();

	}

	public void readDataBase() throws Exception {

		try {

			statement = connection.createStatement();

			/**
			 * Get the result of the inputed query in the statement
			 */
			resultSet = statement.executeQuery("select * from login.comments");

			showResultSet(resultSet);

			/**
			 * Performs an insert
			 * 
			 */

			ps = connection
					.prepareStatement("insert into login.comments values (default, ?, ?, ?, ?)");

			ps.setString(1, "albe");
			ps.setString(2, "albe123");
			ps.setString(3, "albe@gmail.com");
			ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			;
			ps.executeUpdate();

			ps = connection
					.prepareStatement("SELECT user, pwd, email, datum from login.comments");

			resultSet = ps.executeQuery();
			showResultSet(resultSet);

			ps = connection
					.prepareStatement("delete from login.comments where user=? ; ");
			ps.setString(1, "albe");
			ps.executeUpdate();

			resultSet = statement.executeQuery("select * from login.comments");
			showMetaData(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private void showMetaData(ResultSet resultSet) throws SQLException {
		System.out.println("The columns are:");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " "
					+ resultSet.getMetaData().getColumnName(i));
		}

	}

	private void showResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			/**
			 * It is possible to get the columns via name also possible to get
			 * the columns via the column number which starts at 1 e.g.
			 * resultSet.getSTring(2);
			 */

			String user = resultSet.getString("user");
			String pwd = resultSet.getString("pwd");
			Date date = resultSet.getDate("datum");

			System.out.println("User: " + user);
			System.out.println("Password: " + pwd);
			System.out.println("Date: " + date);

		}
	}

	/**
	 * close all the opened connections and statements
	 */
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {

		}
	}

}
