package by.epam.casino.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.ServiceFactory;

/**
 * Implementation of interface CommandManager used for 
 * command managing. 
 *
 */
public class CommandManagerImpl implements CommandManager {
	/**
	 * Factory with the help of which commands will execute.
	 */
	private ServiceFactory factory;

	/**
	 * Constructor.
	 * @param factory Factory for using.
	 */
	public CommandManagerImpl(ServiceFactory factory) {
		this.factory = factory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Command.Forward execute(Command action, HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		action.setFactory(factory);
		return action.exec(request, response);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		factory.close();
	}
}
