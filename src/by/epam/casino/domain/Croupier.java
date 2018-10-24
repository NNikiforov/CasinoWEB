package by.epam.casino.domain;

/**
 * Croupier that can be taken out from DB.
 *
 */
public class Croupier extends Entity {
	/**
	 * Name.
	 */
	private String name;
	/**
	 * Yield.
	 */
	private int yield;

	/**
	 * Constructor.
	 */
	public Croupier() {
	}

	/**
	 * Name getter.
	 * @return Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Name setter 
	 * @param name Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Yield getter.
	 * @return Yield
	 */
	public int getYield() {
		return this.yield;
	}

	/**
	 * Yield setter.
	 * @param yield Yield
	 */
	public void setYield(int yield) {
		this.yield = yield;
	}
}