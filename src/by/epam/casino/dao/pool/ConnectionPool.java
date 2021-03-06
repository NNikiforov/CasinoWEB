package by.epam.casino.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.exception.PersistentException;

/**
 * This class provides connection pool.(Singleton)
 *
 */
final public class ConnectionPool {
	/**
	 * This field used for logging.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

	/**
	 * Database URL.
	 */
	private String url;
	/**
	 * User login to connect to database.
	 */
	private String user;
	/**
	 * User password to connect to database.
	 */
	private String password;
	/**
	 * Max size of connection pool.
	 */
	private int maxSize;
	/**
	 * DB connection timeout.
	 */
	private int checkConnectionTimeout;

	/**
	 * Collection for storing free connections.
	 */
	private BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();

	/**
	 * Collection for storing used connections.
	 */
	private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

	/**
	 * Private constructor.
	 */
	private ConnectionPool() {
	}

	/**
	 * Instance of connection pool.
	 */
	private static ConnectionPool instance = new ConnectionPool();

	/**
	 * Getter of the instance.
	 * 
	 * @return instance
	 */
	public static ConnectionPool getInstance() {
		return instance;
	}

	/**
	 * Synchronized getter of connection.
	 * 
	 * @return connection
	 * @throws PersistentException Thrown if getting connection is impossible.
	 */
	public synchronized Connection getConnection() throws PersistentException {
		PooledConnection connection = null;
		while (connection == null) {
			try {
				if (!freeConnections.isEmpty()) {
					connection = freeConnections.take();
					if (!connection.isValid(checkConnectionTimeout)) {
						try {
							connection.getConnection().close();
						} catch (SQLException e) {
						}
						connection = null;
					}
				} else if (usedConnections.size() < maxSize) {
					connection = createConnection();
				} else {
					LOGGER.error("The limit of number of database connections is exceeded");
					throw new PersistentException();
				}
			} catch (InterruptedException | SQLException e) {
				LOGGER.error("It is impossible to connect to a database" + e);
				throw new PersistentException(e);
			}
		}
		usedConnections.add(connection);
		LOGGER.debug(String.format(
				"Connection was received from pool. Current pool size: %d used connections; %d free connection",
				usedConnections.size(), freeConnections.size()));
		return connection;
	}

	/**
	 * Synchronized releasing of connection.
	 * 
	 * @param connection used connection
	 */
	synchronized void freeConnection(PooledConnection connection) {
		try {
			connection.clearWarnings();
			connection.setAutoCommit(true);
			usedConnections.remove(connection);
			freeConnections.put(connection);
			LOGGER.debug(String.format(
					"Connection was returned into pool. Current pool size: %d used connections; %d free connection",
					usedConnections.size(), freeConnections.size()));
		} catch (SQLException | InterruptedException e1) {
			LOGGER.error("It is impossible to return database connection into pool" + e1);
			try {
				connection.getConnection().close();
			} catch (SQLException e2) {
			}
		}
	}

	/**
	 * Initialization of connection pool.
	 * 
	 * @param driverClass            driverClass
	 * @param url                    DB url.
	 * @param user                   DB user.
	 * @param password               User password.
	 * @param startSize              Start size of connection pool.
	 * @param maxSize                Max size of connection pool.
	 * @param checkConnectionTimeout Collection for storing free connections.
	 * @throws PersistentException
	 */
	public synchronized void init(String driverClass, String url, String user, String password, int startSize,
			int maxSize, int checkConnectionTimeout) throws PersistentException {
		try {
			destroy();
			Class.forName(driverClass);
			this.url = url;
			this.user = user;
			this.password = password;
			this.maxSize = maxSize;
			this.checkConnectionTimeout = checkConnectionTimeout;
			for (int counter = 0; counter < startSize; counter++) {
				freeConnections.put(createConnection());
			}
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			LOGGER.error("It is impossible to initialize connection pool" + e);
			throw new PersistentException(e);
		}
	}

	/**
	 * Creating of connection.
	 * 
	 * @return connection
	 * @throws SQLException Thrown if creation is impossible.
	 */
	private PooledConnection createConnection() throws SQLException {
		return new PooledConnection(DriverManager.getConnection(url, user, password));
	}

	/**
	 * Removing connection pool.
	 */
	public synchronized void destroy() {
		usedConnections.addAll(freeConnections);
		freeConnections.clear();
		for (PooledConnection connection : usedConnections) {
			try {
				connection.getConnection().close();
			} catch (SQLException e) {
				LOGGER.error("Can not destroy connection pool.");
			}
		}
		usedConnections.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void finalize() throws Throwable {
		destroy();
	}
}
