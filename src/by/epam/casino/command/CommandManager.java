package by.epam.casino.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.casino.exception.PersistentException;
/**
 * Interface for command managing.
 *
 */
public interface CommandManager {
	/**
	 * Method for executing command received from request.
	 * @param command Command.
	 * @param request Request.
	 * @param response Response.
	 * @return Forward.
	 * @throws PersistentException PersistentException.
	 */
	Command.Forward execute(Command command, HttpServletRequest request, HttpServletResponse response) throws PersistentException;
	/**
	 * Closing of command manager.
	 */
	void close();
}
