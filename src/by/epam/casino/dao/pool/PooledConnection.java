package by.epam.casino.dao.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
/**
 * Wrapper for connection at pool.
 *
 */
public class PooledConnection implements Connection, Comparable<PooledConnection> {
	/**
	 * Connection.
	 */
	private Connection connection;

	/**
	 * Constructor.
	 * @param connection
	 */
	public PooledConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Getter for connection.
	 * @return connection.
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return connection.isWrapperFor(arg0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return connection.unwrap(arg0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void abort(Executor arg0) throws SQLException {
		connection.abort(arg0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearWarnings() throws SQLException {
		connection.clearWarnings();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws SQLException {
		ConnectionPool.getInstance().freeConnection(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void commit() throws SQLException {
		connection.commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
		return connection.createArrayOf(arg0, arg1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Blob createBlob() throws SQLException {
		return connection.createBlob();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Clob createClob() throws SQLException {
		return connection.createClob();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NClob createNClob() throws SQLException {
		return connection.createNClob();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SQLXML createSQLXML() throws SQLException {
		return connection.createSQLXML();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Statement createStatement(int arg0, int arg1) throws SQLException {
		return connection.createStatement(arg0, arg1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Statement createStatement(int arg0, int arg1, int arg2) throws SQLException {
		return connection.createStatement(arg0, arg1, arg2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
		return connection.createStruct(arg0, arg1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getAutoCommit() throws SQLException {
		return connection.getAutoCommit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCatalog() throws SQLException {
		return connection.getCatalog();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getClientInfo() throws SQLException {
		return connection.getClientInfo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClientInfo(String arg0) throws SQLException {
		return connection.getClientInfo(arg0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHoldability() throws SQLException {
		return connection.getHoldability();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return connection.getMetaData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNetworkTimeout() throws SQLException {
		return connection.getNetworkTimeout();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSchema() throws SQLException {
		return connection.getSchema();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTransactionIsolation() throws SQLException {
		return connection.getTransactionIsolation();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return connection.getTypeMap();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SQLWarning getWarnings() throws SQLException {
		return connection.getWarnings();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isClosed() throws SQLException {
		return connection.isClosed();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReadOnly() throws SQLException {
		return connection.isReadOnly();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(int arg0) throws SQLException {
		return connection.isValid(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String nativeSQL(String arg0) throws SQLException {
		return connection.nativeSQL(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CallableStatement prepareCall(String arg0) throws SQLException {
		return connection.prepareCall(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException {
		return connection.prepareCall(arg0, arg1, arg2);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
		return connection.prepareCall(arg0, arg1, arg2, arg3);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PreparedStatement prepareStatement(String arg0) throws SQLException {
		return connection.prepareStatement(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
		return connection.prepareStatement(arg0, arg1);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
		return connection.prepareStatement(arg0, arg1);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
		return connection.prepareStatement(arg0, arg1);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2) throws SQLException {
		return connection.prepareStatement(arg0, arg1, arg2);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
		return connection.prepareStatement(arg0, arg1, arg2, arg3);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void releaseSavepoint(Savepoint arg0) throws SQLException {
		connection.releaseSavepoint(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollback() throws SQLException {
		connection.rollback();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollback(Savepoint arg0) throws SQLException {
		connection.rollback(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAutoCommit(boolean arg0) throws SQLException {
		connection.setAutoCommit(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCatalog(String arg0) throws SQLException {
		connection.setCatalog(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setClientInfo(Properties arg0) throws SQLClientInfoException {
		connection.setClientInfo(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setClientInfo(String arg0, String arg1) throws SQLClientInfoException {
		connection.setClientInfo(arg0, arg1);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHoldability(int arg0) throws SQLException {
		connection.setHoldability(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
		connection.setNetworkTimeout(arg0, arg1);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setReadOnly(boolean arg0) throws SQLException {
		connection.setReadOnly(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Savepoint setSavepoint() throws SQLException {
		return connection.setSavepoint();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Savepoint setSavepoint(String arg0) throws SQLException {
		return connection.setSavepoint(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSchema(String arg0) throws SQLException {
		connection.setSchema(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTransactionIsolation(int arg0) throws SQLException {
		connection.setTransactionIsolation(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
		connection.setTypeMap(arg0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(PooledConnection connection) {
		return hashCode() - connection.hashCode();
	}
}
