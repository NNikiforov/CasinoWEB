package by.epam.casino.dao.mysql;

import java.sql.Connection;
/**
 * Base DAO implementation.
 *
 */
abstract public class BaseDaoImpl {
	/**
	 * Connection to the DB.
	 */
	protected Connection connection;

	/**
	 * Setter of connection. 
	 * @param connection Connection.
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
