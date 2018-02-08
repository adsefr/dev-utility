package com.yyfs.dev.utility.jdbc.internal;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yyfs.dev.utility.jdbc.JDBCConnection;
import com.yyfs.dev.utility.jdbc.JDBCSavePoint;
import com.yyfs.dev.utility.jdbc.constant.JDBCConfigType;
import com.yyfs.dev.utility.jdbc.helper.JDBCUtil;
import com.yyfs.dev.utility.jdbc.internal.config.AbstractJDBCConfig;
import com.yyfs.dev.utility.jdbc.model.JDBCInfo;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public class DefaultJDBCConnection implements JDBCConnection {

	private final Logger logger = LoggerFactory.getLogger(DefaultJDBCConnection.class);

	private AbstractJDBCConfig config;

	private Connection connection;

	public DefaultJDBCConnection(AbstractJDBCConfig config) {

		this.config = config;
	}

	@Override
	public void connect() throws SQLException {

		if (isClosed()) {
			connection = openConnection();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void commit() throws SQLException {

		getConnection().commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void rollback() throws SQLException {

		getConnection().rollback();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {

		JDBCUtil.close(connection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() throws SQLException {

		return JDBCUtil.isClosed(connection);
	}

	Connection getConnection() throws SQLException {

		if (isClosed()) {
			logger.warn("the connection has been closed!!!");
			throw new SQLException("the connection has been closed!!!");
		}

		return connection;
	}

	private Connection openConnection() throws SQLException {

		JDBCConfigType type = config.getType();
		JDBCInfo info = config.getInfo();
		String driver = type.getDriver();
		String host = info.getHost();
		String port = info.getPort();
		String database = info.getDataBase();
		String url = type.getURL(host, port, database);
		String userId = info.getUserId();
		String password = info.getPassword();

		Connection connection = null;

		try {
			JDBCUtil.forName(driver);

			connection = JDBCUtil.getConnection(url, userId, password);

			connection.setReadOnly(info.isReadOnly());

			connection.setAutoCommit(info.isAutoCommit());

			if (info.getTransactionType() != null) {
				connection.setTransactionIsolation(info.getTransactionType().getType());
			}

			if (info.getHoldabilityType() != null) {
				connection.setHoldability(info.getHoldabilityType().getType());
			}
		} catch (SQLException e) {
			JDBCUtil.close(connection);
			throw e;
		}

		return connection;
	}

	@Override
	public JDBCSavePoint savepoint() throws SQLException {

		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void rollback(JDBCSavePoint savePoint) throws SQLException {

		// TODO 自動生成されたメソッド・スタブ

	}

}
