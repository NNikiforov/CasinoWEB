package by.epam.casino.domain;

import java.util.Date;

/**
 * Bet.
 *
 */
public class Bet extends Entity {
	/**
	 * Amount
	 */
	private int amount;
	/**
	 * Deck.
	 */
	private String deck;
	/**
	 * Time of putting bet.
	 */
	private Date time;
	/**
	 * Selected card.
	 */
	private Card card;
	/**
	 * Croupier.
	 */
	private Croupier croupier;
	/**
	 * Punter.
	 */
	private Punter punter;
	/**
	 * Result.
	 */
	private ResultType result;
	/**
	 * Profit.
	 */
	private int profit;

	/**
	 * Constructor.
	 */
	public Bet() {
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the deck
	 */
	public String getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(String deck) {
		this.deck = deck;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	/**
	 * @return the croupier
	 */
	public Croupier getCroupier() {
		return croupier;
	}

	/**
	 * @param croupier the croupier to set
	 */
	public void setCroupier(Croupier croupier) {
		this.croupier = croupier;
	}

	/**
	 * @return the punter
	 */
	public Punter getPunter() {
		return punter;
	}

	/**
	 * @param punter the punter to set
	 */
	public void setPunter(Punter punter) {
		this.punter = punter;
	}

	/**
	 * @return the result
	 */
	public ResultType getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(ResultType result) {
		this.result = result;
	}

	/**
	 * @return the profit
	 */
	public int getProfit() {
		return profit;
	}

	/**
	 * @param profit the profit to set
	 */
	public void setProfit(int profit) {
		this.profit = profit;
	}

	
}