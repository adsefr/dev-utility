package com.yyfs.dev.utility.jdbc;

import java.sql.SQLException;

public interface JDBCConfig {

	public JDBCConnection getJDBCConnection() throws SQLException;

	public JDBCMetaData getMetaData() throws SQLException;

//	public void setAutoCommit(boolean autoCommit) throws SQLException;
//
//	public boolean isAutoCommit() throws SQLException;
//
//	public void setReadOnly(boolean readOnly) throws SQLException;
//
//	public boolean isReadOnly() throws SQLException;
//
//	public void setTransactionType(TransactionType transactionType) throws SQLException;
//
//	public TransactionType getTransactionType() throws SQLException;
//
//	public void setHoldabilityType(HoldabilityType holdabilityType) throws SQLException;
//
//	public HoldabilityType getHoldabilityType() throws SQLException;
}
