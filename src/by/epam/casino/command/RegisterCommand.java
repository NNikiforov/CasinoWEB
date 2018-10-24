package by.epam.casino.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.domain.GenderType;
import by.epam.casino.domain.Punter;
import by.epam.casino.domain.RoleType;
import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.UserService;

/**
 * Command to user identification.
 *
 */
public class RegisterCommand extends Command {

	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

	/**
	 * Constructor.
	 */
	public RegisterCommand() {
		getAllowRoles().add(RoleType.GUEST);
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		response.setHeader("Cache-Control", "private,no-store,no-cache");
        LOGGER.debug("Register command is running");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if (!isGet() && login != null && password != null && name != null && surname != null) {
			int age = Integer.valueOf(request.getParameter("age"));
			GenderType gender = GenderType.valueOf(request.getParameter("gender").toUpperCase());
			UserService service = factory.getService(UserService.class);
			Punter punter = new Punter();
			punter.setName(name);
			punter.setSurname(surname);
			punter.setAge(age);
			punter.setGender(gender);
			punter.setLogin(login);
			punter.setPassword(password);
			punter.setRole(RoleType.PUNTER);
			try {
				punter.setIdentity(service.create(punter));
			} catch (PersistentException e) {
				request.setAttribute("message", "A user with this login is already registered. Try another one.");
				return null;
			}
			HttpSession session = request.getSession();
			session.setAttribute("authorizedUser", punter);
			return new Forward("/general/main.html");
		}
		return null;
	}

}
