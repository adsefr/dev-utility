package com.yyfs.dev.utility.jdbc;

import java.sql.SQLException;

import com.yyfs.dev.utility.jdbc.model.QueryParameter;
import com.yyfs.dev.utility.jdbc.model.UpdateParameter;

public interface JDBCObject extends JDBCCloseable {

	public JDBCConnection getJDBCConnection();

	public JDBCMetaData getJDBCMetaData() throws SQLException;

	public QueryExecutor query(QueryParameter queryParameter) throws SQLException;

	public UpdateExecutor update(UpdateParameter updateParameter) throws SQLException;

}
