package by.epam.casino.exception;

/**
 * Exception that can be thrown by DAO.
 *
 */
public class PersistentException extends Exception {
	/**
	 * Special field for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public PersistentException() {}

	/**
	 * Constructor
	 * @param message message
	 * @param cause cause
	 */
	public PersistentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message message
	 */
	public PersistentException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param cause cause
	 */
	public PersistentException(Throwable cause) {
		super(cause);
	}
}
