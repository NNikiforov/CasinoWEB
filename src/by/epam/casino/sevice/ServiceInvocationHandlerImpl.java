package by.epam.casino.sevice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.exception.PersistentException;

/**
 * Service invocation handler.
 *
 */
public class ServiceInvocationHandlerImpl implements InvocationHandler {
	/**
	 * This field used for logging.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ServiceInvocationHandlerImpl.class);

	/**
	 * Service.
	 */
	private ServiceImpl service;

	/**
	 * Constructor.
	 * @param service Service.
	 */
	public ServiceInvocationHandlerImpl(ServiceImpl service) {
		this.service = service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
		try {
			Object result = method.invoke(service, arguments);
			service.transaction.commit();
			return result;
		} catch (PersistentException e) {
			rollback(method);
			throw e;
		} catch (InvocationTargetException e) {
			rollback(method);
			throw e.getCause();
		}
	}

	/**
	 * Rollback of service.
	 * @param method method.
	 */
	private void rollback(Method method) {
		try {
			service.transaction.rollback();
		} catch (PersistentException e) {
			LOGGER.warn("It is impossible to rollback transaction", e);
		}
	}
}
