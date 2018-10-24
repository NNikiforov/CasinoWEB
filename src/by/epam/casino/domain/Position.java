package by.epam.casino.domain;

/**
 * Position at the top.
 *
 */
public class Position {
	/**
	 * Begin location.
	 */
	private int begin;
	/**
	 * End location.
	 */
	private int end;

	/**
	 * Constructor.
	 */
	public Position() {
	}
	
	/**
	 * Constructor
	 * @param BEGIN Begin location.
	 * @param END End location.
	 */
	public Position(final int BEGIN, final int END) {
		setBegin(BEGIN);
		setEnd(END);
	}

	/**
	 * @return the begin
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * @param begin the begin to set
	 */
	public void setBegin(int begin) {
		this.begin = begin;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (begin == end) {
			return Integer.toString(begin);
		} else {
			return Integer.toString(begin) + " - " + Integer.toString(end);
		}
	}
}
