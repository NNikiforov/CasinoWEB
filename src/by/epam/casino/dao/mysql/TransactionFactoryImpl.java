package by.epam.casino.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.command.RegisterCommand;
import by.epam.casino.dao.Transaction;
import by.epam.casino.dao.TransactionFactory;
import by.epam.casino.dao.pool.ConnectionPool;
import by.epam.casino.exception.PersistentException;

/**
 * Factory of transaction.
 *
 */
public class TransactionFactoryImpl implements TransactionFactory {
	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

	/**
	 * Connection to the DB.
	 */
	private Connection connection;

	/**
	 * Constructor.
	 * @throws PersistentException Thrown if transaction creating is impossible.
	 */
	public TransactionFactoryImpl() throws PersistentException {
		connection = ConnectionPool.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
		} catch(SQLException e) {
			LOGGER.error("It is impossible to turn off autocommiting for database connection", e);
			throw new PersistentException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Transaction createTransaction() throws PersistentException {
		return new TransactionImpl(connection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		try {
			connection.close();
		} catch(SQLException e) {}
	}
}
