package by.epam.casino.sevice;

import by.epam.casino.domain.Croupier;
import by.epam.casino.exception.PersistentException;
/**
 * Croupier service.
 *
 */
public interface CroupierService extends Service{
	/**
	 * Finding croupier by id.
	 * @param identity id
	 * @return Croupier.
	 * @throws PersistentException Thrown if it is impossible.
	 */
	Croupier find(Integer identity) throws PersistentException;
}
