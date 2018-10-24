package by.epam.casino.dao;

import java.util.List;

import by.epam.casino.domain.Bet;
import by.epam.casino.exception.PersistentException;

/**
 * Interface for working with bet entity.
 *
 */
public interface BetDao extends Dao<Bet> {
	/**
	 * This method read all punter's bets according identity.
	 * @param punterIdentity id
	 * @return Bets.
	 * @throws PersistentException Thrown if reading is impossible.
	 */
	List<Bet> readPunterBets(Integer punterIdentity) throws PersistentException;
}
