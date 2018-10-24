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
 * Command for executing bank operations.
 *
 */
public class BankOperationCommand extends Command {

	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

	/**
	 * Constructor.
	 */
	public BankOperationCommand() {
		getAllowRoles().add(RoleType.ADMIN);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		response.setHeader("Cache-Control", "private,no-store,no-cache");
		LOGGER.debug("Bank operation command is running");
		String amountParam = request.getParameter("amount");
		String operation = request.getParameter("operation");
		String identityParam = request.getParameter("login");
		if (!isGet() && amountParam != null && operation != null && identityParam != null) {
			Integer amount = null;
			Integer identity = null;
			try {
				amount = Integer.parseInt(amountParam);
				identity = Integer.parseInt(identityParam);
			} catch (NumberFormatException e) {
				return new Forward("/general/error.html");
			}
			if (amount <= 0) {
				return new Forward("/general/error.html");
			}
			switch (operation) {
			case "Withdraw":
				amount *= -1;
				break;
			case "Deposit":
				break;
			default:
				return new Forward("/general/error.html");
			}
			UserService service = factory.getService(UserService.class);
			Punter punter = (Punter) service.findByIdentity(identity);
			punter.setCash(punter.getCash() + amount);
			service.update(punter);
			return new Forward("/admin/bank.html");
		} else {
			UserService service = factory.getService(UserService.class);
			request.setAttribute("punters", service.findAllPunters());
			return new Forward("/admin/bank.jsp", false);
		}
	}
}
