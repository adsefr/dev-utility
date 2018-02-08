package com.yyfs.dev.utility.jdbc;

import java.sql.SQLException;

/**
 *
 * @author ri.meisei
 * @since 2015/08/24
 */
public interface UpdateExecutor extends JDBCCloseable {

	public void execute() throws SQLException;

	public void commit() throws SQLException;

	public void rollback() throws SQLException;
}
