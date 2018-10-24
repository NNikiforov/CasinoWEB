package by.epam.casino.command.punter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.command.Command;
import by.epam.casino.command.RegisterCommand;
import by.epam.casino.domain.Punter;
import by.epam.casino.domain.RoleType;
import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.BetService;

/**
 * Command that gives bet's history of punter.
 *
 */
public class GetHistoryCommand extends Command{

	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

	/**
	 * Constructor.
	 */
	public GetHistoryCommand() {
		getAllowRoles().add(RoleType.PUNTER);
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		response.setHeader("Cache-Control", "private,no-store,no-cache");
		LOGGER.debug("Get history command is running");
		Punter punter = (Punter)this.getAuthorizedUser();
		BetService service = factory.getService(BetService.class);
		request.setAttribute("bets", service.findPunterBets(punter.getIdentity()));
		return new Forward("/punter/history.jsp", false);
	}
}
