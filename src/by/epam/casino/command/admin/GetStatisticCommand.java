package by.epam.casino.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.command.Command;
import by.epam.casino.command.RegisterCommand;
import by.epam.casino.domain.RoleType;
import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.CroupierService;
import by.epam.casino.sevice.UserService;
/**
 * Command that gives statistic about punters, and casino.
 *
 */
public class GetStatisticCommand extends Command{

	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

	/**
	 * Constructor.
	 */
	public GetStatisticCommand() {
		getAllowRoles().add(RoleType.ADMIN);
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		response.setHeader("Cache-Control", "private,no-store,no-cache");
		LOGGER.debug("Get statistic command is running");
		UserService userService = factory.getService(UserService.class);
		CroupierService croupierService = factory.getService(CroupierService.class);
		request.setAttribute("punters", userService.findAllPunters());
		request.setAttribute("yield", croupierService.find(1).getYield());
		return new Forward("/admin/statistic.jsp", false);
	}
}
