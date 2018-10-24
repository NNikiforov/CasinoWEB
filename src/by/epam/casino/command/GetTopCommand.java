package by.epam.casino.command;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.domain.RoleType;
import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.UserService;

/**
 * Command to get top punters.
 *
 */
public class GetTopCommand extends Command {

	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(GetTopCommand.class);

	/**
	 * Constructor.
	 */
	public GetTopCommand() {
		getAllowRoles().addAll(Arrays.asList(RoleType.values()));
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		LOGGER.debug("Get top command is running");
		UserService service = factory.getService(UserService.class);
		try {
			request.setAttribute("top", service.findTopPunters());
			if (getAuthorizedUser() != null && getAuthorizedUser().getRole().equals(RoleType.PUNTER)) {
				request.setAttribute("currentPosition", service.getTopPosition(this.getAuthorizedUser().getIdentity()));
			}
			} catch (PersistentException e) {
				request.setAttribute("message", "Unable to show top.");
			}			
		return new Forward("/general/top.jsp", false);
	}
}
