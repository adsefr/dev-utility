package com.yyfs.dev.utility.jdbc;

import java.sql.SQLException;

public interface JDBCCloseable extends AutoCloseable {

	@Override
	public void close() throws SQLException;

	public boolean isClosed() throws SQLException;
}
