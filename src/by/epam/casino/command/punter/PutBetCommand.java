package by.epam.casino.command.punter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.command.Command;
import by.epam.casino.command.RegisterCommand;
import by.epam.casino.domain.Bet;
import by.epam.casino.domain.Card;
import by.epam.casino.domain.Punter;
import by.epam.casino.domain.RoleType;
import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.BetService;
import by.epam.casino.sevice.UserService;
/**
 * Command for putting bet by punter.
 *
 */
public class PutBetCommand extends Command {

	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

	/**
	 * Constructor.
	 */
	public PutBetCommand() {
		getAllowRoles().add(RoleType.PUNTER);
	}

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		response.setHeader("Cache-Control", "private,no-store,no-cache");
		LOGGER.debug("Put bet command is running");
		String cardValue = request.getParameter("card-value");
		String cardSuit = request.getParameter("card-suit");
		UserService userService = factory.getService(UserService.class);
		HttpSession session = request.getSession();
		Punter punter = (Punter) userService.findByIdentity(getAuthorizedUser().getIdentity());
		session.setAttribute("authorizedUser", punter);
		if (!isGet() && cardValue != null && cardSuit != null) {
			BetService betService = factory.getService(BetService.class);
			
			if (punter.isBlocked() == 0) {
				Bet bet = new Bet();
				bet.setPunter(punter);
				bet.setCard(new Card(cardSuit, cardValue));
				bet.setAmount(Integer.parseInt(request.getParameter("bet")));

				String[] sequences = betService.putBet(bet);
				session.setAttribute("authorizedUser", (Punter) userService.findByIdentity(punter.getIdentity()));
				session.setAttribute("gameResult", bet.getResult().getText() + " Total: " + bet.getProfit() + "$");
				session.setAttribute("lastBet",
						"Card: " + bet.getCard().toCustomString() + "   Amount: " + bet.getAmount() + "$");
				session.setAttribute("winSequence", sequences[1]);
				session.setAttribute("loseSequence", sequences[0]);
				return new Forward("/punter/game.html");
			}
		}
		return null;
	}
}
