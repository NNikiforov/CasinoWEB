package by.epam.casino.sevice;

import java.util.List;

import by.epam.casino.domain.Position;
import by.epam.casino.domain.Punter;
import by.epam.casino.domain.User;
import by.epam.casino.exception.PersistentException;

/**
 * User service.
 *
 */
public interface UserService extends Service {
	/**
	 * Finding all users.
	 * @return users.
	 * @throws PersistentException Thrown if it is impossible.
	 */
	List<User> findAll() throws PersistentException;

	/**
	 * Finding all punters.
	 * @return punters
	 * @throws PersistentException Thrown if it is impossible.
	 */
	List<Punter> findAllPunters() throws PersistentException;

	/**
	 * Finding top 10 punters.
	 * @return top punters.
	 * @throws PersistentException Thrown if it is impossible.
	 */
	List<Punter> findTopPunters() throws PersistentException;

	/**
	 * Finding position of punter at top.
	 * @param identity id
	 * @return position
	 * @throws PersistentException Thrown if it is impossible.
	 */
	Position getTopPosition(Integer identity) throws PersistentException;

	/**
	 * Finding user by id.
	 * @param identity id
	 * @return User
	 * @throws PersistentException Thrown if it is impossible.
	 */
	User findByIdentity(Integer identity) throws PersistentException;

	/**
	 * Finding user by login and password
	 * @param login login
	 * @param password password
	 * @return User
	 * @throws PersistentException Thrown if it is impossible.
	 */
	User findByLoginAndPassword(String login, String password) throws PersistentException;

	/**
	 * Updating user info.
	 * @param user user
	 * @throws PersistentException Thrown if it is impossible.
	 */
	void update(User user) throws PersistentException;

	/**
	 * Creating user.
	 * @param user user
	 * @return id
	 * @throws PersistentException Thrown if it is impossible.
	 */
	int create(User user) throws PersistentException;

	/**
	 * Removing user.
	 * @param user user
	 * @throws PersistentException Thrown if it is impossible.
	 */
	void delete(User user) throws PersistentException;
}
