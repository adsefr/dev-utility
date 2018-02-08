package com.yyfs.dev.utility.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.yyfs.dev.utility.jdbc.bak.AbstractJDBCQueryExecutor;
import com.yyfs.dev.utility.jdbc.bak.DefaultJDBCRow;
import com.yyfs.dev.utility.jdbc.bak.DefaultJDBCUpdateExecutor;
import com.yyfs.dev.utility.jdbc.bak.DefaultQueryResultColumnMeta;
import com.yyfs.dev.utility.jdbc.model.JDBCColumn;
import com.yyfs.dev.utility.jdbc.model.JDBCRow;
import com.yyfs.dev.utility.jdbc.model.QueryParameter;
import com.yyfs.dev.utility.jdbc.model.QueryResultColumnMeta;
import com.yyfs.dev.utility.jdbc.model.UpdateParameter;

public class JDBCFactory {



	public static QueryResultMetaData newJDBCQueryResultMetaData(ResultSetMetaData resultSetMetaData) throws SQLException {

		DefaultJDBCQueryResultMetaData defaultJDBCQueryResultMetaData = new DefaultJDBCQueryResultMetaData(resultSetMetaData);

		return defaultJDBCQueryResultMetaData;
	}

	public static QueryExecutor newJDBCQueryExecutor(JDBCConnection jdbcConnection, QueryParameter queryParameter) {

		QueryExecutor jdbcQueryExecutor = AbstractJDBCQueryExecutor.newInstance(jdbcConnection, queryParameter);

		return jdbcQueryExecutor;
	}

	public static QueryExecutor newJDBCQueryExecutor(ResultSet resultSet) throws SQLException {

		QueryExecutor jdbcQueryExecutor = AbstractJDBCQueryExecutor.newInstance(resultSet);
		resultSet.getStatement().close();
		return jdbcQueryExecutor;
	}

	public static UpdateExecutor newJDBCUpdateExecutor(JDBCConnection jdbcConnection, UpdateParameter updateParameter) {

		UpdateExecutor jdbcUpdateExecutor = new DefaultJDBCUpdateExecutor(jdbcConnection, updateParameter);

		return jdbcUpdateExecutor;
	}

	public static JDBCRow newJDBCRow() {

		DefaultJDBCRow defaultJDBCRow = new DefaultJDBCRow();

		return defaultJDBCRow;
	}

	public static JDBCColumn newJDBCColumn(QueryResultColumnMeta QueryExecutorColumnMeta, Object rawValue) {

		DefaultJDBCColumn defaultJDBCColumn = new DefaultJDBCColumn(QueryExecutorColumnMeta, rawValue);

		return defaultJDBCColumn;
	}

	public static QueryResultColumnMeta newQueryResultColumnMeta() {

		return new DefaultQueryResultColumnMeta();
	}
}
