package by.epam.casino.sevice;

import java.util.List;

import by.epam.casino.domain.Bet;
import by.epam.casino.exception.PersistentException;

/**
 * Bet service. 
 *
 */
public interface BetService extends Service{

	/**
	 * Putting bet.
	 * @param bet Bet
	 * @return Losing and winning sequences.
	 * @throws PersistentException Thrown if it is impossible.
	 */
	String[] putBet(Bet bet) throws PersistentException;

	/**
	 * Remove bet from DB.
	 * @param bet Bet
	 * @throws PersistentException Thrown if it is impossible.
	 */
	void removeBet(Bet bet) throws PersistentException;

	/**
	 * Finding of punters bets.
	 * @param identity id
	 * @return Bets.
	 * @throws PersistentException Thrown if it is impossible.
	 */
	List<Bet> findPunterBets(Integer identity) throws PersistentException;
}
