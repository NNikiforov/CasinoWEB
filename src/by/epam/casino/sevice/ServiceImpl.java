package by.epam.casino.sevice;

import by.epam.casino.dao.Transaction;

/**
 * Base implementation of Service.
 *
 */
abstract public class ServiceImpl implements Service {
	/**
	 * transaction
	 */
	protected Transaction transaction = null;

	/**
	 * Setter of transaction.
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
