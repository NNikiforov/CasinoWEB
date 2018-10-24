package by.epam.casino.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.dao.BetDao;
import by.epam.casino.domain.Bet;
import by.epam.casino.domain.Card;
import by.epam.casino.domain.Croupier;
import by.epam.casino.domain.Punter;
import by.epam.casino.domain.ResultType;
import by.epam.casino.exception.PersistentException;

/**
 * Implementation of bet DAO.
 *
 */
public class BetDaoImpl extends BaseDaoImpl implements BetDao {
	/**
	 * This field used for logging.
	 */
	private static final Logger LOGGER = LogManager.getLogger(BetDaoImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer create(Bet bet) throws PersistentException {
		String sql = "INSERT INTO `casinodb`.`bets` (`croupier_id`, `punter_id`, `selected_card`, `amount`, `deck`, `time`, `result`, `profit`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, bet.getCroupier().getIdentity());
			statement.setInt(2, bet.getPunter().getIdentity());
			statement.setString(3, bet.getCard().toString());
			statement.setInt(4, bet.getAmount());
			statement.setString(5, bet.getDeck());
			statement.setString(6, new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(bet.getTime()));
			statement.setString(7, bet.getResult().name());
			statement.setInt(8, bet.getProfit());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				LOGGER.debug("Bet is created.");
				return resultSet.getInt(1);
			} else {
				LOGGER.error("There is no autoincremented index after trying to add record into table `bets`");
				throw new PersistentException();
			}
		} catch (SQLException e) {
			LOGGER.error("Can not to create Bet:", e);
			throw new PersistentException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.error("Can not to close ResultSet");
			}
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.error("Can not to close statement");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bet read(Integer identity) throws PersistentException {
		String sql = "SELECT croupier_id, punter_id, selected_card, amount, deck, time, result FROM casinodb.bets WHERE `id` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, identity);
			resultSet = statement.executeQuery();
			Bet bet = new Bet();
			if (resultSet.next()) {
				Croupier croupier = new Croupier();
				Punter punter = new Punter();
				croupier.setIdentity(resultSet.getInt("croupier_id"));
				punter.setIdentity(resultSet.getInt("punter_id"));
				bet.setCroupier(croupier);
				bet.setPunter(punter);
				bet.setCard(new Card(resultSet.getString("selected_card")));
				bet.setAmount(resultSet.getInt("amount"));
				bet.setDeck(resultSet.getString("deck"));
				bet.setTime(resultSet.getDate("time"));
				bet.setResult(ResultType.valueOf(resultSet.getString("result")));
			}
			LOGGER.debug("Bet is read.");
			return bet;
		} catch (SQLException e) {
			LOGGER.error("Can not to read Bet:", e);
			throw new PersistentException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.error("Can not to close ResultSet");
			}
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.error("Can not to close statement");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Bet entity) throws PersistentException {
		LOGGER.debug("Trying to update bet.");
		return;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Bet bet) throws PersistentException {
		String sql = "DELETE FROM `casinodb`.`bets` WHERE (`id` = ?) AND (`punter_id` = ?);";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, bet.getIdentity());
			statement.setInt(2, bet.getPunter().getIdentity());
			statement.executeUpdate();
			LOGGER.debug("Bet is deleted.");
		} catch (SQLException e) {
			LOGGER.error("Can not to delete Bet:", e);
			throw new PersistentException(e);
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.error("Can not to close statement");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Bet> readPunterBets(Integer punterIdentity) throws PersistentException {
		String sql = "SELECT id, croupier_id, punter_id, selected_card, amount, deck, time, result, profit FROM casinodb.bets WHERE `punter_id` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, punterIdentity);
			resultSet = statement.executeQuery();
			List<Bet> bets = new LinkedList<>();
			while (resultSet.next()) {
				Bet bet = new Bet();
				Croupier croupier = new Croupier();
				Punter punter = new Punter();
				croupier.setIdentity(resultSet.getInt("croupier_id"));
				punter.setIdentity(resultSet.getInt("punter_id"));
				bet.setIdentity(resultSet.getInt("id"));
				bet.setCroupier(croupier);
				bet.setPunter(punter);
				bet.setCard(new Card(resultSet.getString("selected_card")));
				bet.setAmount(resultSet.getInt("amount"));
				bet.setDeck(resultSet.getString("deck"));
				bet.setTime(new Date(resultSet.getTimestamp("time").getTime()));
				bet.setResult(ResultType.valueOf(resultSet.getString("result")));
				bet.setProfit(resultSet.getInt("profit"));
				bets.add(bet);
			}
			LOGGER.debug("Bets is read.");
			return bets;
		} catch (SQLException e) {
			LOGGER.error("Can not to read punters bet:", e);
			throw new PersistentException(e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.error("Can not to close ResultSet");
			}
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.error("Can not to close statement");
			}
		}
	}
}
