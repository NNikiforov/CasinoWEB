package by.epam.casino.action;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import by.epam.casino.domain.Card;
import by.epam.casino.domain.ResultType;

/**
 * This class provides different actions that can be executed by application.
 *
 */
public class CasinoAction {
	/**
	 * Field that stores deck for the game that consists of 54 cards. 
	 */
	private static List<Card> deck = new ArrayList<Card>();
	/**
	 * Filling the deck with cards.
	 */
	static {
		deck.add(new Card("SA"));
		deck.add(new Card("S2"));
		deck.add(new Card("S3"));
		deck.add(new Card("S4"));
		deck.add(new Card("S5"));
		deck.add(new Card("S6"));
		deck.add(new Card("S7"));
		deck.add(new Card("S8"));
		deck.add(new Card("S9"));
		deck.add(new Card("S10"));
		deck.add(new Card("SJ"));
		deck.add(new Card("SQ"));
		deck.add(new Card("SK"));
		deck.add(new Card("CA"));
		deck.add(new Card("C2"));
		deck.add(new Card("C3"));
		deck.add(new Card("C4"));
		deck.add(new Card("C5"));
		deck.add(new Card("C6"));
		deck.add(new Card("C7"));
		deck.add(new Card("C8"));
		deck.add(new Card("C9"));
		deck.add(new Card("C10"));
		deck.add(new Card("CJ"));
		deck.add(new Card("CQ"));
		deck.add(new Card("CK"));
		deck.add(new Card("HA"));
		deck.add(new Card("H2"));
		deck.add(new Card("H3"));
		deck.add(new Card("H4"));
		deck.add(new Card("H5"));
		deck.add(new Card("H6"));
		deck.add(new Card("H7"));
		deck.add(new Card("H8"));
		deck.add(new Card("H9"));
		deck.add(new Card("H10"));
		deck.add(new Card("HJ"));
		deck.add(new Card("HQ"));
		deck.add(new Card("HK"));
		deck.add(new Card("DA"));
		deck.add(new Card("D2"));
		deck.add(new Card("D3"));
		deck.add(new Card("D4"));
		deck.add(new Card("D5"));
		deck.add(new Card("D6"));
		deck.add(new Card("D7"));
		deck.add(new Card("D8"));
		deck.add(new Card("D9"));
		deck.add(new Card("D10"));
		deck.add(new Card("DJ"));
		deck.add(new Card("DQ"));
		deck.add(new Card("DK"));
	}

	/**
	 * This method return shuffled deck.
	 * @return deck
	 */
	public static List<Card> generateDeck() {
		List<Card> generatedDeck = new ArrayList<Card>(deck);
		Collections.shuffle(generatedDeck);
		return generatedDeck;
	}

	/**
	 * Return the result of the game.
	 * @param deck Deck.
	 * @param card Punter's card.
	 * @return Result.
	 */
	public static ResultType getResult(List<Card> deck, Card card) {
		for (int i = 0; i < deck.size(); i++) {
			if (card.getValue().equals(deck.get(i).getValue())) {
				if (card.getSuit().equals(deck.get(i).getSuit())) {
					return ResultType.SLOSE;
				} else {
					return ResultType.LOSE;
				}
			}
			if (card.getValue().equals(deck.get(++i).getValue())) {
				if (card.getSuit().equals(deck.get(i).getSuit())) {
					return ResultType.BIGWIN;
				} else {
					return ResultType.WIN;
				}
			}
		}
		throw new InvalidParameterException("Invalid deck.");
	}

	/**
	 * This method convert deck to string for DB.
	 * @param deck Deck.
	 * @return Deck.
	 */
	public static String deckToString(List<Card> deck) {
		String result = new String();
		for (Card card : deck) {
			result = String.join(" ", result, card.toString());
		}
		return result;
	}

	/**
	 * This method divides the deck into losing and winning sequences of cards.
	 * @param deck Deck
	 * @param selectedCard Punter's card.
	 * @return losing and winning sequences.
	 */
	public static String[] deckToStrings(List<Card> deck, Card selectedCard) {
		String[] result = {"", ""};
		boolean isWinCard = false;
		boolean hasResult = false;
		for (Card card : deck) {
			if (selectedCard.getValue().equals(card.getValue())) {
				hasResult = true;
			}
			if (isWinCard) {
				result[1] = String.join(" ", result[1], card.toString());
				if (hasResult) {
					break;
				}
			} else {
				result[0] = String.join(" ", result[0], card.toString());
			}
			isWinCard = !isWinCard;
		}
		return result;
	}
}
