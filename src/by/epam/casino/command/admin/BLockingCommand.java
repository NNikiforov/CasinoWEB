package by.epam.casino.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.command.Command;
import by.epam.casino.command.RegisterCommand;
import by.epam.casino.domain.Punter;
import by.epam.casino.domain.RoleType;
import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.UserService;
/**
 * Command for blocking or unlocking punter.
 *
 */
public class BLockingCommand extends Command {

	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

	/**
	 * Constructor.
	 */
	public BLockingCommand() {
		getAllowRoles().add(RoleType.ADMIN);
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		LOGGER.debug("Blocking command is running");
		if (!isGet()) {
			Integer identity = null;
			try {
				identity = Integer.valueOf(request.getParameter("identity"));
			} catch (NumberFormatException e) {
				return new Forward("/general/error.jsp");
			}
			UserService service = factory.getService(UserService.class);
			Punter punter = (Punter) service.findByIdentity(identity);
			punter.setIsBlocked((byte) (punter.isBlocked() != 0 ? 0 : 1));
			service.update(punter);
			return new Forward("/admin/statistic.html");
		} else {
			return new Forward("/general/error.jsp");
		}
	}
}
