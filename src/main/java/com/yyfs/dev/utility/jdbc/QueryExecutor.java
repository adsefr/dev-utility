package com.yyfs.dev.utility.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.yyfs.dev.utility.jdbc.model.QueryParameter;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface QueryExecutor extends JDBCCloseable {

	public QueryResult query(String sqlCluase) throws SQLException;

	public QueryResult query(String sqlCluase, Object... parameters) throws SQLException;

	public QueryResult query(QueryParameter queryParameter) throws SQLException;

	public List<QueryResult> query(List<QueryParameter> queryParameterList) throws SQLException;
}
