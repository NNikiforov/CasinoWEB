package by.epam.casino.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.exception.PersistentException;
/**
 * Command to exit from authorized account.
 *
 */
public class LogoutCommand extends AuthorizedUserCommand{
	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		LOGGER.debug("Logout command is running");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return new Forward("/general/main.html");
	}
}
