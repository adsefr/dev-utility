package com.yyfs.dev.utility.jdbc;

import com.yyfs.dev.utility.jdbc.model.QueryResultColumnMeta;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface QueryResultMetaData {

	public int getColumnCount();

	public boolean hasColumn(int columnNumber);

	public boolean hasColumn(String columnName);

	public Integer getColumnNumber(String columnName);

	public String getColumnName(int columnNumber);

	public QueryResultColumnMeta getColumnMeta(int columnNumber);

	public QueryResultColumnMeta getColumnMeta(String columnName);
}
