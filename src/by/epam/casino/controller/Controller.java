package by.epam.casino.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.command.Command;
import by.epam.casino.command.CommandManager;
import by.epam.casino.command.CommandManagerFactory;
import by.epam.casino.dao.mysql.TransactionFactoryImpl;
import by.epam.casino.dao.pool.ConnectionPool;
import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.ServiceFactory;
import by.epam.casino.sevice.ServiceFactoryImpl;

/**
 * This is the servlet that manages the entire application.
 *
 */
public class Controller extends HttpServlet {
	/**
	 * Special field for serialization.
	 */
	private static final long serialVersionUID = 1L;
	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
	/**
	 * Database driver class.
	 */
	public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	/**
	 * Database URL.
	 */
	public static final String DB_URL = "jdbc:mysql://localhost:3306/?"
			+ "allowPublicKeyRetrieval=true"
			+ "&verifyServerCertificate=false"
            + "&useSSL=false"
            + "&requireSSL=false"
            + "&useLegacyDatetimeCode=false"
            + "&amp"
            + "&serverTimezone=UTC";
	/**
	 * User login to connect to database.
	 */
	public static final String DB_USER = "admin";
	/**
	 * User password to connect to database.
	 */
	public static final String DB_PASSWORD = "admin";
	/**
	 * Start size of connection pool.
	 */
	public static final int DB_POOL_START_SIZE = 10;
	/**
	 * Max size of connection pool.
	 */
	public static final int DB_POOL_MAX_SIZE = 100;
	/**
	 * DB connection timeout.
	 */
	public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

	/**
	 * Creating o service factory.
	 * @return ServiceFactory
	 * @throws PersistentException Thrown if the factory cannot be created.
	 */
	public ServiceFactory getFactory() throws PersistentException {
		return new ServiceFactoryImpl(new TransactionFactoryImpl());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER,
					DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE,
					DB_POOL_CHECK_CONNECTION_TIMEOUT);
			LOGGER.debug("Controller initialized.");
		} catch (PersistentException e) {
			LOGGER.error("It is impossible to initialize application", e);
			destroy();
		}
	}
	/**
	 * Method for processing GET requests.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.debug(request.getRequestURL()
				+ ": The request is processed by doGet.");
		Command command = (Command)request.getAttribute("command");
		command.setGet(true);
		processRequest(command, request, response);
	}

	/**
	 * Method for processing POST requests
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.debug(request.getRequestURL()
				+ ": The request is processed by doPost.");
		Command command = (Command)request.getAttribute("command");
		command.setGet(false);
		processRequest(command, request, response);
	}

	/**
	 * Method for processing all requests.
	 * @param command Command for executing.
	 * @param request request
	 * @param response response
	 * @throws ServletException ServletException
	 * @throws IOException IOException
	 */
	private void processRequest(Command command, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if(session != null) {
				request.setAttribute("winSequence", session.getAttribute("winSequence"));
				request.setAttribute("loseSequence", session.getAttribute("loseSequence"));
				request.setAttribute("gameResult", session.getAttribute("gameResult"));
				request.setAttribute("lastBet", session.getAttribute("lastBet"));
				session.removeAttribute("winSequence");
				session.removeAttribute("loseSequence");
				session.removeAttribute("gameResult");
				session.removeAttribute("lastBet");
			}
			CommandManager commandManager = CommandManagerFactory.getManager(getFactory());
			Command.Forward forward = commandManager.execute(command, request, response);
			commandManager.close();
			String requestedUri = request.getRequestURI();
			if(forward != null && forward.isRedirect()) {
				String redirectedUri = request.getContextPath() + forward.getForward();
				LOGGER.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri, redirectedUri));
				response.sendRedirect(redirectedUri);
			} else {
				String jspPage;
				if(forward != null) {
					jspPage = forward.getForward();
				} else {
					jspPage = command.getName() + ".jsp";
				}
				jspPage = "/WEB-INF/jsp" + jspPage;
				LOGGER.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
				getServletContext().getRequestDispatcher(jspPage).forward(request, response);
			}
		} catch(PersistentException e) {
			LOGGER.error("It is impossible to process request", e);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/general/error.jsp").forward(request, response);
		}
	}
}
