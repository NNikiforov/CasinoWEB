package by.epam.casino.dao;

import by.epam.casino.exception.PersistentException;
/**
 * Creation of Transaction.
 *
 */
public interface TransactionFactory {
	/**
	 * Creation of Transaction.
	 * @return Transaction
	 * @throws PersistentException  PersistentException
	 */
	Transaction createTransaction() throws PersistentException;

	/**
	 * Closing transaction factory.
	 */
	void close();
}
