package by.epam.casino.command;

import by.epam.casino.sevice.ServiceFactory;
/**
 * Factory of command manager. 
 *
 */
public class CommandManagerFactory {
	/**
	 * Creating of command manager.
	 * @param factory Factory with the help of which commands will execute.
	 * @return command manager.
	 */
	public static CommandManager getManager(ServiceFactory factory) {
		return new CommandManagerImpl(factory);
	}
}
