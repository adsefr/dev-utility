package com.yyfs.dev.utility.jdbc.internal.config;

import java.sql.SQLException;

import com.yyfs.dev.utility.jdbc.JDBCConfig;
import com.yyfs.dev.utility.jdbc.JDBCConnection;
import com.yyfs.dev.utility.jdbc.JDBCMetaData;
import com.yyfs.dev.utility.jdbc.constant.JDBCConfigType;
import com.yyfs.dev.utility.jdbc.internal.DefaultJDBCConnection;
import com.yyfs.dev.utility.jdbc.model.JDBCInfo;

public abstract class AbstractJDBCConfig implements JDBCConfig {

	protected JDBCConfigType type;

	protected JDBCInfo info;

	public AbstractJDBCConfig(JDBCConfigType type, JDBCInfo info) {
		this.type = type;
		this.info = info;
	}

	/**
	 * @return type
	 */
	public JDBCConfigType getType() {

		return type;
	}

	/**
	 * @return info
	 */
	public JDBCInfo getInfo() {

		return info;
	}

	@Override
	public JDBCConnection getJDBCConnection() throws SQLException {

		JDBCConnection jdbcConnection = new DefaultJDBCConnection(this);

		return jdbcConnection;
	}

	@Override
	public JDBCMetaData getMetaData() throws SQLException {

		// JDBCMetaData metaData = new
		return null;
	}

	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final void setAutoCommit(boolean autoCommit) throws SQLException {
	//
	// getConnection().setAutoCommit(autoCommit);
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final boolean isAutoCommit() throws SQLException {
	//
	// return getConnection().getAutoCommit();
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final void setReadOnly(boolean readOnly) throws SQLException {
	//
	// getConnection().setReadOnly(readOnly);
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final boolean isReadOnly() throws SQLException {
	//
	// return getConnection().isReadOnly();
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final void setTransactionType(TransactionType transactionType) throws SQLException {
	//
	// getConnection().setTransactionIsolation(transactionType.getType());
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final TransactionType getTransactionType() throws SQLException {
	//
	// return TransactionType.valueOf(getConnection().getTransactionIsolation());
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final void setHoldabilityType(HoldabilityType holdabilityType) throws SQLException {
	//
	// getConnection().setHoldability(holdabilityType.getType());
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final HoldabilityType getHoldabilityType() throws SQLException {
	//
	// return HoldabilityType.valueOf(getConnection().getTransactionIsolation());
	// }
}
