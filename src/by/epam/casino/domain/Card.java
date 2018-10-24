package by.epam.casino.domain;

/**
 * Card.
 *
 */
public class Card {
	/**
	 * Suit
	 */
	private String suit;
	/**
	 * value
	 */
	private String value;

	/**
	 * Constructor.
	 */
	public Card() {
	}

	/**
	 * Constructor
	 * 
	 * @param card Card.
	 */
	public Card(String card) {
		this.suit = card.substring(0, 1);
		this.value = card.substring(1, card.length());
	}

	/**
	 * Constructor
	 * 
	 * @param suit  suit
	 * @param value value
	 */
	public Card(String suit, String value) {
		this.suit = suit;
		this.value = value;
	}

	/**
	 * getter value.
	 * 
	 * @return
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * setter value.
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * getter suit.
	 * 
	 * @return
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * setter suit.
	 * 
	 * @param suit
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (suit == null) {
			if (other.suit != null)
				return false;
		} else if (!suit.equals(other.suit))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/**
	 * Converting card to the custom string.
	 */
	public String toCustomString() {
		String customSuit = "";
		switch (suit) {
		case "S":
			customSuit = " &#9824";
			break;
		case "C":
			customSuit = " &#9827;";
			break;
		case "D":
			customSuit = " &#9830";
			break;
		case "H":
			customSuit = " &#9829";
			break;
		}
		return value + customSuit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return suit + value;
	}
}