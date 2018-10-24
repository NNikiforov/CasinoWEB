package by.epam.casino.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.command.Command;
import by.epam.casino.domain.RoleType;
import by.epam.casino.domain.User;

/**
 * This filter is used to check the access rights to the command.
 *
 */
public class SecurityFilter implements Filter {
	/**
	 * This field used for logging.
	 */
	private static final Logger LOGGER
        = LogManager.getLogger(SecurityFilter.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			LOGGER.debug(httpRequest.getRequestURI()
					+ ": The request is in the security filter.");
			Command command = (Command)httpRequest.getAttribute("command");
			Set<RoleType> allowRoles = command.getAllowRoles();
			String userName = "unauthorized user";
			HttpSession session = httpRequest.getSession(false);
			User user = null;
			if(session != null) {
				user = (User)session.getAttribute("authorizedUser");
				command.setAuthorizedUser(user);
			}
			boolean canExecute = allowRoles.contains(RoleType.GUEST);
			if(user != null) {
				userName = "\"" + user.getLogin() + "\" user";
				canExecute = allowRoles.contains(user.getRole());
			}
			if(canExecute) {
				chain.doFilter(request, response);
			} else {
				LOGGER.info(String.format("Trying of %s access to forbidden"
						+ " resource \"%s\"", userName, command.getName()));
				if (command.getName().equals("/general/login")) {
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/general/main.html");
				} else {
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/general/login.html");
				}
			}
		} else {
			LOGGER.error("It is impossible to use HTTP filter");
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {}
}
