package com.yyfs.dev.utility.jdbc.bak;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rms.utility.base.validate.Assertion;
import com.yyfs.dev.utility.jdbc.JDBCConnection;
import com.yyfs.dev.utility.jdbc.model.JDBCRow;
import com.yyfs.dev.utility.jdbc.model.JDBCValue;
import com.yyfs.dev.utility.jdbc.model.QueryParameter;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public class JDBCLargeQueryExecutor extends AbstractJDBCQueryExecutor {

	public JDBCLargeQueryExecutor(JDBCConnection jdbcConnection, QueryParameter queryParameter) {

		super(jdbcConnection, queryParameter);
	}

	public JDBCLargeQueryExecutor(ResultSet resultSet) throws SQLException {

		super(resultSet);
	}

	@Override
	public final boolean hasNext() throws SQLException {

		return getResultSet().next();
	}

	@Override
	public final void beforeFirst() throws SQLException {

		getResultSet().beforeFirst();
	}

	@Override
	public final void absolute(int rowNumber) throws SQLException {

		getResultSet().absolute(rowNumber);
	}

	@Override
	public final void afterLast() throws SQLException {

		getResultSet().afterLast();
	}

	@Override
	public final JDBCRow getRow() throws SQLException {

		JDBCRow jdbcRow = super.convertCurrentRowToJDBCRow();

		return jdbcRow;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(int columnNumber) throws SQLException {

		Assertion.assertNotNull("columnNumber", columnNumber);

		if (!hasColumn(columnNumber)) {
			throw new SQLException("the columnNumber[" + columnNumber + "] is not found!!!");
		}

		Object object = jdbcRowCollection.get(columnNumber).getValue(columnNumber);

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(String columnName) throws SQLException {

		Assertion.assertNotNull("columnName", columnName);

		if (!hasColumn(columnName)) {
			throw new SQLException("the columnName[" + columnName + "] is not found!!!");
		}

		Object object = getResultSet().getObject(columnName);

		return (T) object;
	}

	@Override
	public final JDBCValue getJDBCValue(int columnNumber) throws SQLException {

		Assertion.assertNotNull("columnNumber", columnNumber);

		if (!hasColumn(columnNumber)) {
			throw new SQLException("the columnNumber[" + columnNumber + "] is not found!!!");
		}

		Object object = getResultSet().getObject(columnNumber);

		JDBCValue jdbcValue = JDBCValue.newJDBCValue(object);

		return jdbcValue;
	}

	@Override
	public final JDBCValue getJDBCValue(String columnName) throws SQLException {

		Assertion.assertNotNull("columnName", columnName);

		if (!hasColumn(columnName)) {
			throw new SQLException("the columnName[" + columnName + "] is not found!!!");
		}

		Object object = getResultSet().getObject(columnName);

		JDBCValue jdbcValue = JDBCValue.newJDBCValue(object);

		return jdbcValue;
	}
}
