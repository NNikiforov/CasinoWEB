package by.epam.casino.sevice;

import java.util.ArrayList;
import java.util.List;

import by.epam.casino.dao.UserDao;
import by.epam.casino.domain.Position;
import by.epam.casino.domain.Punter;
import by.epam.casino.domain.RoleType;
import by.epam.casino.domain.User;
import by.epam.casino.exception.PersistentException;

/**
 * Implementation of user service.
 *
 */
public class UserServiceImpl extends ServiceImpl implements UserService {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findAll() throws PersistentException {
		UserDao dao = transaction.createDao(UserDao.class);
		return dao.read();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Punter> findAllPunters() throws PersistentException {
		UserDao dao = transaction.createDao(UserDao.class);
		List<User> users = dao.read();
		List<Punter> punters = new ArrayList<>(); 
		users.removeIf(user -> !user.getRole().equals(RoleType.PUNTER));
		users.forEach(user -> punters.add((Punter)user));
		return punters;				
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByIdentity(Integer identity) throws PersistentException {
		UserDao dao = transaction.createDao(UserDao.class);
		return dao.read(identity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByLoginAndPassword(String login, String password) throws PersistentException {
		UserDao dao = transaction.createDao(UserDao.class);
		return dao.read(login, password);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(User user) throws PersistentException {
		UserDao dao = transaction.createDao(UserDao.class);
		dao.update(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(User user) throws PersistentException {
		UserDao dao = transaction.createDao(UserDao.class);
		dao.delete(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int create(User user) throws PersistentException {
		UserDao dao = transaction.createDao(UserDao.class);
		return dao.create(user);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Punter> findTopPunters() throws PersistentException {
		UserDao dao = transaction.createDao(UserDao.class);
		return dao.readTopPunters();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Position getTopPosition(Integer identity) throws PersistentException {
		UserDao dao = transaction.createDao(UserDao.class);
		return dao.readTopPosition(identity);
	}
}
