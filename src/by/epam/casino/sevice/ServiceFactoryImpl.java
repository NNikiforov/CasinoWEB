package by.epam.casino.sevice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.casino.dao.Transaction;
import by.epam.casino.dao.TransactionFactory;
import by.epam.casino.exception.PersistentException;

/**
 * Implementation of Service factory.
 *
 */
public class ServiceFactoryImpl implements ServiceFactory {
	/**
	 * This field used for logging.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ServiceFactoryImpl.class);

	/**
	 * All allowed services.
	 */
	private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

	/***
	 * Filling services.
	 */
	static {
		SERVICES.put(UserService.class, UserServiceImpl.class);
		SERVICES.put(BetService.class, BetServiceImpl.class);
		SERVICES.put(CroupierService.class, CroupierServiceImpl.class);
	}

	/**
	 * Transaction factory.
	 */
	private TransactionFactory transactionFactory;

	/**
	 * Constructor.
	 * @param factory factory
	 * @throws PersistentException Thrown if it is impossible.
	 */
	public ServiceFactoryImpl(TransactionFactory factory) throws PersistentException {
		this.transactionFactory = factory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <Type extends Service> Type getService(Class<Type> key) throws PersistentException {
		Class<? extends ServiceImpl> value = SERVICES.get(key);
		if(value != null) {
			try {
				ClassLoader classLoader = value.getClassLoader();
				Class<?>[] interfaces = {key};
				Transaction transaction = transactionFactory.createTransaction();
				ServiceImpl service = value.newInstance();
				service.setTransaction(transaction);
				InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
				return (Type)Proxy.newProxyInstance(classLoader, interfaces, handler);
			} catch(PersistentException e) {
				throw e;
			} catch(InstantiationException | IllegalAccessException e) {
				LOGGER.error("It is impossible to instance service class", e);
				throw new PersistentException(e);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		transactionFactory.close();
	}
}
