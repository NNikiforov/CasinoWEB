package by.epam.casino.command;

import by.epam.casino.domain.RoleType;

/**
 * Summary for commands for authorized users.
 *
 */
public abstract class AuthorizedUserCommand extends Command{
	/**
	 * Constructor.
	 */
	public AuthorizedUserCommand() {
		getAllowRoles().add(RoleType.ADMIN);
		getAllowRoles().add(RoleType.PUNTER);
	}
}
