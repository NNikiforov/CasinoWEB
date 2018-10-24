package by.epam.casino.dao;

import java.util.List;

import by.epam.casino.domain.Position;
import by.epam.casino.domain.Punter;
import by.epam.casino.domain.User;
import by.epam.casino.exception.PersistentException;

/**
 * Interface for working with user entities.
 *
 */
public interface UserDao extends Dao<User> {
	/**
	 * Reading of user from DB according login and password.
	 * @param login Login.
	 * @param password Password.
	 * @return User.
	 * @throws PersistentException Thrown if reading is impossible.
	 */
	User read(String login, String password) throws PersistentException;

	/**
	 * Reading of all users from DB.
	 * @return All users.
	 * @throws PersistentException Thrown if reading is impossible.
	 */
	List<User> read() throws PersistentException;

	/**
	 * Reading of top punters ordered by rating.
	 * @return Top punters.
	 * @throws PersistentException Thrown if reading is impossible.
	 */
	List<Punter> readTopPunters() throws PersistentException;

	/**
	 * Reading of punter's position at the top.
	 * @param identity id
	 * @return Position.
	 * @throws PersistentException Thrown if reading is impossible.
	 */
	Position readTopPosition(Integer identity) throws PersistentException;
}
