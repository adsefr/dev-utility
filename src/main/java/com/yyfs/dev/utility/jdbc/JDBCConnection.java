package com.yyfs.dev.utility.jdbc;

import java.sql.SQLException;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface JDBCConnection extends JDBCCloseable {

	public void connect() throws SQLException;

	public void commit() throws SQLException;

	public void rollback() throws SQLException;

	public JDBCSavePoint savepoint() throws SQLException;

	public void rollback(JDBCSavePoint savePoint) throws SQLException;

	public static void main(String[] args) {

		long l = Long.MAX_VALUE;
		float f = Float.MIN_VALUE;
		f = l + f;
		System.out.println(f);
	}
}
