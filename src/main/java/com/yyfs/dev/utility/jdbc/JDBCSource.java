package com.yyfs.dev.utility.jdbc;

import java.sql.SQLException;

public interface JDBCSource {

	public JDBCConnection getJDBCConnection() throws SQLException;
}
