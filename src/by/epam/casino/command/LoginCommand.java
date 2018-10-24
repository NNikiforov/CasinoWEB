package by.epam.casino.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.domain.RoleType;
import by.epam.casino.domain.User;
import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.UserService;

/**
 * Command to user authorization.
 *
 */
public class LoginCommand extends Command {

	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

	/**
	 * Constructor.
	 */
	public LoginCommand() {
		getAllowRoles().add(RoleType.GUEST);
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public Command.Forward exec(HttpServletRequest request,
			HttpServletResponse response) throws PersistentException {
		response.setHeader("Cache-Control", "private,no-store,no-cache");
		LOGGER.debug("Login command is running");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if (!isGet() && login != null && password != null) {
			UserService service = factory.getService(UserService.class);
			User user = service.findByLoginAndPassword(login, password);
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("authorizedUser", user);
				return new Forward("/general/main.html");
			} else {
				LOGGER.debug("Password or email is wrong.");
				request.setAttribute("message", "Wrong password or email. Try again.");
			}
		}
		return null;
	}
}
