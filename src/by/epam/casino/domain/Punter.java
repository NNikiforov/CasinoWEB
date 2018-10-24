package by.epam.casino.domain;

import java.util.List;
/**
 * Punter. Entity of application that can put bet.
 *
 */
public class Punter extends User {
	/**
	 * Cash.
	 */
	private Integer cash;
	/**
	 * Current state.
	 */
	private Byte isBlocked;
	/**
	 * Rating.
	 */
	private Integer rating;
	/**
	 * Rates.
	 */
	private List<Bet> bets;

	/**
	 * Constructor.
	 */
	public Punter() {
	}

	/**
	 * Getter of isBlocked
	 * @return State.
	 */
	public Byte isBlocked() {
		return isBlocked;
	}

	/**
	 * Setter of isBlocked.
	 * @param isBlocked State.
	 */
	public void setIsBlocked(Byte isBlocked) {
		this.isBlocked = isBlocked;
	}

	/**
	 * @return the cash
	 */
	public Integer getCash() {
		return cash;
	}

	/**
	 * @param cash the cash to set
	 */
	public void setCash(Integer cash) {
		this.cash = cash;
	}

	/**
	 * @return the rating
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	/**
	 * @return the bets
	 */
	public List<Bet> getBets() {
		return bets;
	}

	/**
	 * @param bets the bets to set
	 */
	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}

	/**
	 * adding bet
	 * @param bet bet
	 * @return
	 */
	public Bet addBet(Bet bet) {
		getBets().add(bet);
		bet.setPunter(this);

		return bet;
	}

	/**
	 * removing bet.
	 * @param bet
	 * @return
	 */
	public Bet removeBet(Bet bet) {
		getBets().remove(bet);
		bet.setPunter(null);

		return bet;
	}
}