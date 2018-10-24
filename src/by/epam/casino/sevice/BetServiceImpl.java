package by.epam.casino.sevice;

import java.util.Date;
import java.util.List;

import by.epam.casino.action.CasinoAction;
import by.epam.casino.dao.BetDao;
import by.epam.casino.domain.Bet;
import by.epam.casino.domain.Card;
import by.epam.casino.domain.Croupier;
import by.epam.casino.exception.PersistentException;

/**
 * Bet service implementation.
 *
 */
public class BetServiceImpl extends ServiceImpl implements BetService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] putBet(Bet bet) throws PersistentException {
		BetDao dao = transaction.createDao(BetDao.class);
		Croupier croupier = new Croupier();
		croupier.setIdentity(1);
		bet.setCroupier(croupier);
		List<Card> generatedDeck = CasinoAction.generateDeck();
		bet.setDeck(CasinoAction.deckToString(generatedDeck));
		bet.setResult(CasinoAction.getResult(generatedDeck, bet.getCard()));
		bet.setTime(new Date());
		bet.setProfit((int) (bet.getAmount() * bet.getResult().getCoefficient()));
		dao.create(bet);
		return CasinoAction.deckToStrings(generatedDeck, bet.getCard());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Bet> findPunterBets(Integer punterIdentity) throws PersistentException {
		BetDao dao = transaction.createDao(BetDao.class);
		return dao.readPunterBets(punterIdentity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeBet(Bet bet) throws PersistentException {
		BetDao dao = transaction.createDao(BetDao.class);
		dao.delete(bet);
	}
}
