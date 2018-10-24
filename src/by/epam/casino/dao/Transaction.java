package by.epam.casino.dao;

import by.epam.casino.exception.PersistentException;
/**
 * Used for creating necessary DAO and transaction's method.
 *
 */
public interface Transaction {
	/**
	 * DAO creation.
	 * @param key 
	 * @return DAO
	 * @throws PersistentException PersistentException.
	 */
	<Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException;

	/**
	 * Commit current transaction.
	 * @throws PersistentException
	 */
	void commit() throws PersistentException;

	/**
	 * Rollback current transaction.
	 * @throws PersistentException
	 */
	void rollback() throws PersistentException;
}
