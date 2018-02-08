package com.yyfs.dev.utility.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.yyfs.dev.utility.jdbc.model.JDBCRow;
import com.yyfs.dev.utility.jdbc.model.JDBCValue;

public interface QueryResult extends Result {

	public void beforeFirst() throws SQLException;

	public void absolute(int rowNumber) throws SQLException;

	public void afterLast() throws SQLException;

	public boolean hasNext() throws SQLException;

	public boolean hasColumn(int columnNumber) throws SQLException;

	public boolean hasColumn(String columnName) throws SQLException;

	public JDBCRow getRow() throws SQLException;

	public <T> T getValue(int columnNumber) throws SQLException;

	public <T> T getValue(String columnName) throws SQLException;

	public List<Object> getValues() throws SQLException;

	public JDBCValue getJDBCValue(int columnNumber) throws SQLException;

	public JDBCValue getJDBCValue(String columnName) throws SQLException;

	public List<JDBCValue> getJDBCValues() throws SQLException;
}
