package by.epam.casino.command.punter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.command.Command;
import by.epam.casino.command.RegisterCommand;
import by.epam.casino.domain.Bet;
import by.epam.casino.domain.Punter;
import by.epam.casino.domain.RoleType;
import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.BetService;

/**
 * Command for removing bet from history.
 *
 */
public class RemoveBetCommand extends Command {

	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

	/**
	 * Constructor.
	 */
	public RemoveBetCommand() {
		getAllowRoles().add(RoleType.PUNTER);
	}
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		response.setHeader("Cache-Control", "private,no-store,no-cache");
		LOGGER.debug("Remove bet command is running");
		String betIdentity = request.getParameter("betIdentity");
		if (!isGet() && betIdentity != null) {
			Bet bet = new Bet();
			Punter punter = new Punter();
			punter.setIdentity(getAuthorizedUser().getIdentity());
			bet.setPunter(punter);
			bet.setIdentity(Integer.valueOf(betIdentity));
			BetService service = factory.getService(BetService.class);
			service.removeBet(bet);
		}
		return new Forward("/punter/history.html");
	}
}
