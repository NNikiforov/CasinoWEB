package by.epam.casino.dao;

import by.epam.casino.domain.Entity;
import by.epam.casino.exception.PersistentException;

/**
 * Interface that provides method for working with DB.
 *
 * @param <Type> Type of DAO.
 */
public interface Dao<Type extends Entity> {
	/**
	 * Creating entity of DB according type of DAO.
	 * @param entity Entity.
	 * @return Identity of entity.
	 * @throws PersistentException Thrown if creating is impossible. 
	 */
	Integer create(Type entity) throws PersistentException;

	/**
	 * Reading from DB Entity by id.
	 * @param identity id
	 * @return entity
	 * @throws PersistentException Thrown if reading is impossible.
	 */
	Type read(Integer identity) throws PersistentException;

	/**
	 * Updating of entity a DB.
	 * @param entity Updated entity.
	 * @throws PersistentException Thrown if updating is impossible.
	 */
	void update(Type entity) throws PersistentException;

	/**
	 * Removing entity from DB.
	 * @param entity Entity.
	 * @throws PersistentException Thrown if removing is impossible.
	 */
	void delete(Type entity) throws PersistentException;
}
