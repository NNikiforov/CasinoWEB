package by.epam.casino.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.dao.CroupierDao;
import by.epam.casino.domain.Croupier;
import by.epam.casino.exception.PersistentException;
/**
 * Implementation of croupier DAO.
 *
 */
public class CroupierDaoImpl extends BaseDaoImpl implements CroupierDao {
	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(CroupierDaoImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer create(Croupier entity) throws PersistentException {
		String sql = "INSERT INTO `casinodb`.`croupiers` (`name`) VALUES (?);";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getName());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				LOGGER.debug("Croupier is created.");
				return resultSet.getInt(1);
			} else {
				LOGGER.error("There is no autoincremented index after trying to add record into table `croupiers`");
				throw new PersistentException();
			}
		} catch (SQLException e) {
			LOGGER.error("Can not to create croupier:", e);
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
	public Croupier read(Integer identity) throws PersistentException {
		String sql = "SELECT `croupiers`.`id`, `croupiers`.`name`, `croupiers`.`yield` FROM `casinodb`.`croupiers` WHERE `id` = ?;";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, identity);
			resultSet = statement.executeQuery();
			Croupier croupier = new Croupier();
			if (resultSet.next()) {
				croupier.setIdentity(resultSet.getInt("id"));
				croupier.setName(resultSet.getString("name"));
				croupier.setYield(resultSet.getInt("yield"));
			}
			LOGGER.debug("Croupier is read.");
			return croupier;
		} catch (SQLException e) {
			LOGGER.error("Can not to read Croupier:", e);
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
	public void update(Croupier entity) throws PersistentException {
		LOGGER.debug("Trying to update croupier.");
		return;
	}

	@Override
	public void delete(Croupier entity) throws PersistentException {
		String sql = "DELETE FROM `casinodb`.`croupiers` WHERE (`id` = ?);";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, entity.getIdentity());
			statement.executeUpdate();
			LOGGER.debug("Croupier is deleted.");
		} catch (SQLException e) {
			LOGGER.error("Can not to delete Croupier:", e);
			throw new PersistentException(e);
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.error("Can not to close statement");
			}
		}	
	}

}
