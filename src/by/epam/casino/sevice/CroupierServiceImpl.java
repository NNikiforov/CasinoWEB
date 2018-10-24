package by.epam.casino.sevice;

import by.epam.casino.dao.CroupierDao;
import by.epam.casino.domain.Croupier;
import by.epam.casino.exception.PersistentException;

/**
 * Implementation of croupier service.
 *
 */
public class CroupierServiceImpl extends ServiceImpl implements CroupierService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Croupier find(Integer identity) throws PersistentException {
		CroupierDao dao = transaction.createDao(CroupierDao.class);
		return dao.read(identity);
	}
}
