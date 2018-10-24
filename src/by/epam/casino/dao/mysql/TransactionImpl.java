package by.epam.casino.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.dao.BetDao;
import by.epam.casino.dao.CroupierDao;
import by.epam.casino.dao.Dao;
import by.epam.casino.dao.Transaction;
import by.epam.casino.dao.UserDao;
import by.epam.casino.exception.PersistentException;
/**
 * Transaction to the DB.
 *
 */
public class TransactionImpl implements Transaction {
	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(TransactionImpl.class);

	/**
	 * This field stores all allowed DAO implementation.
	 */
	private static Map<Class<? extends Dao<?>>, Class<? extends BaseDaoImpl>> classes = new ConcurrentHashMap<>();

	/**
	 * Filling of classes.
	 */
	static {
		classes.put(UserDao.class, UserDaoImpl.class);
		classes.put(BetDao.class, BetDaoImpl.class);
		classes.put(CroupierDao.class, CroupierDaoImpl.class);
	}

	/**
	 * Connection to the DB.
	 */
	private Connection connection;

	/**
	 * Constructor.
	 * @param connection connection.
	 */
	public TransactionImpl(Connection connection) {
		this.connection = connection;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException {
		Class<? extends BaseDaoImpl> value = classes.get(key);
		if(value != null) {
			try {
				BaseDaoImpl dao = value.newInstance();
				dao.setConnection(connection);
				return (Type)dao;
			} catch(InstantiationException | IllegalAccessException e) {
				LOGGER.error("It is impossible to create data access object", e);
				throw new PersistentException(e);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void commit() throws PersistentException {
		try {
			connection.commit();
		} catch(SQLException e) {
			LOGGER.error("It is impossible to commit transaction", e);
			throw new PersistentException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollback() throws PersistentException {
		try {
			connection.rollback();
		} catch(SQLException e) {
			LOGGER.error("It is impossible to rollback transaction", e);
			throw new PersistentException(e);
		}
	}
}
