package com.yyfs.dev.utility.jdbc;

import java.sql.SQLException;

public interface JDBCTransaction {

	public JDBCConnection getJDBCConnection() throws SQLException;

	public void begin() throws SQLException;

	public void commit() throws SQLException;

	public void rollback() throws SQLException;
}
