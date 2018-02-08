package com.yyfs.dev.utility.jdbc.internal;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yyfs.dev.utility.base.validate.Assertion;
import com.yyfs.dev.utility.jdbc.JDBCConfig;
import com.yyfs.dev.utility.jdbc.JDBCConnection;
import com.yyfs.dev.utility.jdbc.JDBCMetaData;
import com.yyfs.dev.utility.jdbc.JDBCObject;
import com.yyfs.dev.utility.jdbc.QueryExecutor;
import com.yyfs.dev.utility.jdbc.UpdateExecutor;
import com.yyfs.dev.utility.jdbc.model.QueryParameter;
import com.yyfs.dev.utility.jdbc.model.UpdateParameter;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
class DefaultJDBCObject implements JDBCObject {

	private final static Logger logger = LoggerFactory.getLogger(DefaultJDBCObject.class);

	private final DefaultJDBCConnection jdbcConnection;

	public DefaultJDBCObject(JDBCConfig config) throws SQLException {

		Assertion.assertNotNull("config", config);

		this.jdbcConnection = (DefaultJDBCConnection) config.getJDBCConnection();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JDBCMetaData getJDBCMetaData() throws SQLException {

		DatabaseMetaData databaseMetaData = jdbcConnection.getConnection().getMetaData();
		return new DefaultJDBCMetaData(databaseMetaData);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {

		jdbcConnection.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() throws SQLException {

		return jdbcConnection.isClosed();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JDBCConnection getJDBCConnection() {

		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QueryExecutor query(QueryParameter queryParameter) throws SQLException {

		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UpdateExecutor update(UpdateParameter updateParameter) throws SQLException {

		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final QueryExecutor query(QueryParameter queryParameter) throws SQLException {
	//
	// QueryExecutor jdbcQueryExecutor = JDBCFactory.newJDBCQueryExecutor(this, queryParameter);
	//
	// return jdbcQueryExecutor;
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final UpdateExecutor update(UpdateParameter updateParameter) throws SQLException {
	//
	// UpdateExecutor jdbcQueryExecutor = JDBCFactory.newJDBCUpdateExecutor(this, updateParameter);
	//
	// return jdbcQueryExecutor;
	// }

	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final JDBCMetaData getJDBCDataBaseMetaData() throws SQLException {
	//
	// DatabaseMetaData databaseMetaData = getConnection().getMetaData();
	//
	// JDBCMetaData jdbcDataBaseMetaData = JDBCFactory.newJDBCDataBaseMetaData(databaseMetaData);
	//
	// return jdbcDataBaseMetaData;
	// }

}
