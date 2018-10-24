package by.epam.casino.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.dao.UserDao;
import by.epam.casino.domain.GenderType;
import by.epam.casino.domain.Position;
import by.epam.casino.domain.Punter;
import by.epam.casino.domain.RoleType;
import by.epam.casino.domain.User;
import by.epam.casino.exception.PersistentException;

/**
 * Implementation of user DAO.
 *
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	/**
	 * This field used for logging.
	 */
	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer create(User user) throws PersistentException {
		String sql = "INSERT INTO `casinodb`.`users` (`name`, `surname`, `age`, `gender`, `login`, `password`, `role`) VALUES (?, ?, ?, ?, ?, md5(?), ?);";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setInt(3, user.getAge());
			statement.setString(4, user.getGender().name());
			statement.setString(5, user.getLogin());
			statement.setString(6, user.getPassword());
			statement.setString(7, user.getRole().name());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				LOGGER.debug("User is created.");
				return resultSet.getInt(1);
			} else {
				LOGGER.error("There is no autoincremented index after trying to add record into table `bets`");
				throw new PersistentException();
			}
		} catch (SQLException e) {
			LOGGER.error("Can not to read create user:", e);
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
	public User read(Integer identity) throws PersistentException {
		String sql = "SELECT id, name, surname, age, gender, login, password, role, cash, rating, is_blocked FROM casinodb.users WHERE `id` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, identity);
			resultSet = statement.executeQuery();
			User user = null;
			if (resultSet.next()) {
				if (RoleType.PUNTER == RoleType.valueOf(resultSet.getString("role"))) {
					user = new Punter();
					user.setRole(RoleType.PUNTER);
					((Punter) user).setCash(resultSet.getInt("cash"));
					((Punter) user).setRating(resultSet.getInt("rating"));
					((Punter) user).setIsBlocked(resultSet.getByte("is_blocked"));
				} else {
					user = new User();
					user.setRole(RoleType.ADMIN);
				}
				user.setIdentity(identity);
				user.setName(resultSet.getString("name"));
				user.setSurname(resultSet.getString("surname"));
				user.setGender(GenderType.valueOf(resultSet.getString("gender")));
				user.setAge(resultSet.getInt("age"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
			}
			LOGGER.debug("User is read.");
			return user;
		} catch (SQLException e) {
			LOGGER.error("Can not to read User:", e);
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
	public void update(User user) throws PersistentException {
		String sql = "UPDATE `casinodb`.`users` SET `cash` = ?, `is_blocked` = ? WHERE (`id` = ?);";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, ((Punter) user).getCash());
			statement.setByte(2, ((Punter) user).isBlocked());
			statement.setInt(3, user.getIdentity());
			statement.executeUpdate();
			LOGGER.debug("User is updated.");
		} catch (SQLException e) {
			LOGGER.error("Can not to update User:", e);
			throw new PersistentException(e);
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				LOGGER.error("Can not to close statement");
			}
		}
	}

	@Override
	public void delete(User user) throws PersistentException {
		String sql = "DELETE FROM `casinodb`.`users` WHERE (`id` = ?);";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getIdentity());
			statement.executeUpdate();
			LOGGER.debug("User is deleted.");
		} catch (SQLException e) {
			LOGGER.error("Can not to delete User:", e);
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
	public User read(String login, String password) throws PersistentException {
		String sql = "SELECT id, name, surname, age, gender, role, cash, rating, is_blocked FROM casinodb.users WHERE `login` = ? AND `password` = md5(?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			User user = null;
			if (resultSet.next()) {
				if (RoleType.PUNTER == RoleType.valueOf(resultSet.getString("role"))) {
					user = new Punter();
					user.setRole(RoleType.PUNTER);
					((Punter) user).setCash(resultSet.getInt("cash"));
					((Punter) user).setRating(resultSet.getInt("rating"));
					((Punter) user).setIsBlocked(resultSet.getByte("is_blocked"));
				} else {
					user = new User();
					user.setRole(RoleType.ADMIN);
				}
				user.setIdentity(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setSurname(resultSet.getString("surname"));
				user.setGender(GenderType.valueOf(resultSet.getString("gender")));
				user.setAge(resultSet.getInt("age"));
				user.setLogin(login);
				user.setPassword(password);
			}
			LOGGER.debug("User is read.");
			return user;
		} catch (SQLException e) {
			LOGGER.error("Can not to read user:", e);
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
	public List<User> read() throws PersistentException {
		String sql = "SELECT id, name, surname, age, gender, login, password, role, cash, rating, is_blocked FROM casinodb.users";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			List<User> users = new ArrayList<>();
			User user = null;
			while (resultSet.next()) {
				if (RoleType.PUNTER == RoleType.valueOf(resultSet.getString("role"))) {
					user = new Punter();
					user.setRole(RoleType.PUNTER);
					((Punter) user).setCash(resultSet.getInt("cash"));
					((Punter) user).setRating(resultSet.getInt("rating"));
					((Punter) user).setIsBlocked(resultSet.getByte("is_blocked"));
				} else {
					user = new User();
					user.setRole(RoleType.ADMIN);
				}
				user.setIdentity(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setSurname(resultSet.getString("surname"));
				user.setGender(GenderType.valueOf(resultSet.getString("gender")));
				user.setAge(resultSet.getInt("age"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
			}
			LOGGER.debug("Users are read");
			return users;
		} catch (SQLException e) {
			LOGGER.error("Can not to read users:", e);
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
	public List<Punter> readTopPunters() throws PersistentException {
		String sql = "SELECT name, surname, age, gender, rating FROM casinodb.users WHERE role = 'PUNTER' ORDER BY rating DESC LIMIT 10;";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			List<Punter> punters = new ArrayList<>();
			Punter punter = null;
			while (resultSet.next()) {
				punter = new Punter();
				punter.setRole(RoleType.PUNTER);
				punter.setRating(resultSet.getInt("rating"));
				punter.setName(resultSet.getString("name"));
				punter.setSurname(resultSet.getString("surname"));
				punter.setGender(GenderType.valueOf(resultSet.getString("gender")));
				punter.setAge(resultSet.getInt("age"));
				punters.add(punter);
			}
			LOGGER.debug("Top punters are read");
			return punters;
		} catch (SQLException e) {
			LOGGER.error("Can not to read top punters:", e);
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
	public Position readTopPosition(Integer identity) throws PersistentException {
		String sql = "SELECT \n" + "COUNT( \n" + "	CASE\n"
				+ "		WHEN rating > (SELECT rating FROM casinodb.users WHERE id = ?) \n" + "			THEN 1\n"
				+ "		ELSE NULL\n" + "	END\n" + ") + 1 AS startPosition,\n" + "COUNT( \n" + "	CASE\n"
				+ "		WHEN rating >= (SELECT rating FROM casinodb.users WHERE id = ?) \n" + "			THEN 1\n"
				+ "		ELSE NULL\n" + "	END\n" + ") AS endPosition \n" + "FROM casinodb.users;";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, identity);
			statement.setInt(2, identity);
			resultSet = statement.executeQuery();
			Position position = null;
			if (resultSet.next()) {
				position = new Position();
				position.setBegin(resultSet.getInt("startPosition"));
				position.setEnd(resultSet.getInt("endPosition"));
			}
			LOGGER.debug("Position is read");
			return position;
		} catch (SQLException e) {
			LOGGER.error("Can not to read position:", e);
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
