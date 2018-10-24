package by.epam.casino.command;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.domain.RoleType;
import by.epam.casino.exception.PersistentException;
/**
 * Command to get main page.
 *
 */
public class MainCommand extends Command{
	/**
     * This field used for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(MainCommand.class);

	/**
	 * Constructor.
	 */
	public MainCommand() {
		getAllowRoles().addAll(Arrays.asList(RoleType.values()));
	}
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		LOGGER.debug("Main command is running");
		return new Forward("/general/main.jsp", false);
	}
}
