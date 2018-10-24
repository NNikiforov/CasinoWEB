package by.epam.casino.sevice;

import by.epam.casino.exception.PersistentException;

/**
 * Factory  of Service.
 *
 */
public interface ServiceFactory {
	/**
	 * Creating service.
	 * @param key Key.
	 * @return Service.
	 * @throws PersistentException Thrown if it is impossible.
	 */
	<Type extends Service> Type getService(Class<Type> key) throws PersistentException;

	/**
	 * Closing Service factory.
	 */
	void close();
}
