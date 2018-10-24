package by.epam.casino.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.command.Command;
import by.epam.casino.command.GetTopCommand;
import by.epam.casino.command.LoginCommand;
import by.epam.casino.command.LogoutCommand;
import by.epam.casino.command.MainCommand;
import by.epam.casino.command.RegisterCommand;
import by.epam.casino.command.admin.BLockingCommand;
import by.epam.casino.command.admin.BankOperationCommand;
import by.epam.casino.command.admin.GetStatisticCommand;
import by.epam.casino.command.punter.GetHistoryCommand;
import by.epam.casino.command.punter.PutBetCommand;
import by.epam.casino.command.punter.RemoveBetCommand;

/**
 * This filter is used to get command from URI.
 *
 */
public class CommandFromUriFilter implements Filter {
	/**
	 * This field used for logging.
	 */
	private static final Logger LOGGER
        = LogManager.getLogger(CommandFromUriFilter.class);

	/**
	 * This field stores all allowed commands.
	 */
	private static Map<String, Class<? extends Command>> commands
	    = new ConcurrentHashMap<>();

	static {
		commands.put("/general/login", LoginCommand.class);
		commands.put("/general/main", MainCommand.class);
		commands.put("/general/register", RegisterCommand.class);
		commands.put("/general/logout", LogoutCommand.class);
		commands.put("/general/top", GetTopCommand.class);

		commands.put("/punter/game", PutBetCommand.class);
		commands.put("/punter/history", GetHistoryCommand.class);
		commands.put("/punter/remove", RemoveBetCommand.class);

		commands.put("/admin/bank", BankOperationCommand.class);
		commands.put("/admin/statistic", GetStatisticCommand.class);
		commands.put("/admin/block", BLockingCommand.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String contextPath = httpRequest.getContextPath();
			String uri = httpRequest.getRequestURI();
			LOGGER.debug(uri + ": The request is in the CommandFromUriFilter.");
			int beginCommand = contextPath.length();
			int endCommand = uri.lastIndexOf('.');
			String commandName;
			if (endCommand >= 0) {
				commandName = uri.substring(beginCommand, endCommand);
			} else {
				commandName = uri.substring(beginCommand);
			}
			Class<? extends Command> commandClass = commands.get(commandName);
			try {
				Command command = commandClass.newInstance();
				command.setName(commandName);
				httpRequest.setAttribute("command", command);
				chain.doFilter(request, response);
			} catch (InstantiationException | IllegalAccessException
					| NullPointerException e) {
				LOGGER.error("It is impossible to create command"
						+ " handler object", e);
				httpRequest.getServletContext().getRequestDispatcher(
						"/WEB-INF/jsp/general/error.jsp").forward(request,
						response);
			}
		} else {
			LOGGER.error("It is impossible to use HTTP filter");
			request.getServletContext().getRequestDispatcher(
					"/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}
}
