package by.epam.casino.domain;

/**
 * Entity that can be taken out from DB.
 *
 */
abstract public class Entity {
	/**
	 * id
	 */
	private Integer identity;

	/**
	 * Getter of id
	 * 
	 * @return id
	 */
	public Integer getIdentity() {
		return identity;
	}

	/**
	 * Setter of id.
	 * 
	 * @param identity id
	 */
	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		if (object != null) {
			if (object != this) {
				if (object.getClass() == getClass() && identity != null) {
					return identity.equals(((Entity) object).identity);
				}
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return identity != null ? identity.hashCode() : 0;
	}
}
