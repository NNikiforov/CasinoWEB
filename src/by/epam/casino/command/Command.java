package by.epam.casino.command;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.casino.domain.RoleType;
import by.epam.casino.domain.User;
import by.epam.casino.exception.PersistentException;
import by.epam.casino.sevice.ServiceFactory;
/**
 * This class is abstract representation of some action that can be perform
 * allowed users.
 *
 */
abstract public class Command {
	/**
	 * Set of allowed users.
	 */
	private Set<RoleType> allowRoles = new HashSet<>();
	/**
	 * Current user executing command. 
	 */
	private User authorizedUser;
	/**
	 * Name of command.
	 */
	private String name;
	/**
	 * This field stores type of request from which this command trying to execute.
	 */
	private boolean isGet;

	/**
	 * Service factory.
	 */
	protected ServiceFactory factory;

	/***
	 * Getter of allowed users.
	 * @return Set of allowed users.
	 */
	public Set<RoleType> getAllowRoles() {
		return allowRoles;
	}

	/**
	 * Getter of authorized user.
	 * @return authorized user.
	 */
	public User getAuthorizedUser() {
		return authorizedUser;
	}

	/**
	 * Setter of authorized user.
	 * @param authorizedUser authorized user.
	 */
	public void setAuthorizedUser(User authorizedUser) {
		this.authorizedUser = authorizedUser;
	}

	/**
	 * Getter of name.
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of name.
	 * @param name Name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Factory setter.
	 * @param factory factory.
	 */
	public void setFactory(ServiceFactory factory) {
		this.factory = factory;
	}

	/**
	 * Getter of isGet field.
	 * @return value of isGet.
	 */
	public boolean isGet() {
		return isGet;
	}

	/**
	 * Getter of isGet field.
	 * @param isGet value of isGet.
	 */
	public void setGet(boolean isGet) {
		this.isGet = isGet;
	}

	/**
	 * Abstract method for executing this command. 
	 * @param request request
	 * @param response response
	 * @return object of Forward.
	 * @throws PersistentException PersistentException.
	 */
	abstract public Command.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException;

	/**
	 * Nested class used to define a redirect.
	 *
	 */
	public static class Forward {
		/**
		 * Value of redirection.
		 */
		private String forward;
		/**
		 * This field stores type of redirect.
		 */
		private boolean redirect;

		/**
		 * Constructor.
		 * @param forward Value of redirection.
		 * @param redirect Type of redirect.
		 */
		public Forward(String forward, boolean redirect) {
			this.forward = forward;
			this.redirect = redirect;
		}

		/**
		 * Constructor.
		 * @param forward Value of redirection.
		 */
		public Forward(String forward) {
			this(forward, true);
		}

		/**
		 * Getter of value of forward.
		 * @return Forward.
		 */
		public String getForward() {
			return forward;
		}

		/**
		 * Setter of forward. 
		 * @param forward Value of forward.
		 */
		public void setForward(String forward) {
			this.forward = forward;
		}

		/**
		 * Getter of field of redirect.
		 * @return Value of redirect.
		 */
		public boolean isRedirect() {
			return redirect;
		}

		/**
		 * Setter of field of redirect.
		 * @param redirect redirect
		 */
		public void setRedirect(boolean redirect) {
			this.redirect = redirect;
		}
	}
}
