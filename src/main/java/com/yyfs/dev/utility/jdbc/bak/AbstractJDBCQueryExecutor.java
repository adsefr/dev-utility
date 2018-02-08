package com.yyfs.dev.utility.jdbc.bak;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rms.utility.base.logging.Logger;
import com.rms.utility.base.util.ArrayUtil;
import com.rms.utility.base.validate.Assertion;
import com.yyfs.dev.utility.jdbc.JDBCConnection;
import com.yyfs.dev.utility.jdbc.JDBCFactory;
import com.yyfs.dev.utility.jdbc.QueryExecutor;
import com.yyfs.dev.utility.jdbc.QueryResultMetaData;
import com.yyfs.dev.utility.jdbc.helper.JDBCUtil;
import com.yyfs.dev.utility.jdbc.model.JDBCColumn;
import com.yyfs.dev.utility.jdbc.model.JDBCRow;
import com.yyfs.dev.utility.jdbc.model.JDBCValue;
import com.yyfs.dev.utility.jdbc.model.QueryParameter;
import com.yyfs.dev.utility.jdbc.model.QueryResultColumnMeta;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public abstract class AbstractJDBCQueryExecutor implements QueryExecutor {

	protected final Logger logger = Logger.getLogger(this.getClass());

	protected final List<JDBCRow> jdbcRowCollection = new ArrayList<>();

	private QueryParameter queryParameter;

	private JDBCConnection jdbcConnection;

	private PreparedStatement preparedStatement;

	private ResultSet resultSet;

	private Throwable throwable;

	private String statusCode = "";

	private String errorMessage = "";

	private QueryResultMetaData jdbcQueryResultMetaData;

	public static QueryExecutor newInstance(JDBCConnection jdbcConnection, QueryParameter queryParameter) {

		QueryExecutor jdbcQueryExecutor = null;

		if (queryParameter != null && queryParameter.isLargeMode()) {
			jdbcQueryExecutor = new JDBCLargeQueryExecutor(jdbcConnection, queryParameter);
		} else {
			jdbcQueryExecutor = new DefaultJDBCQueryExecutor(jdbcConnection, queryParameter);
		}

		return jdbcQueryExecutor;
	}

	public static QueryExecutor newInstance(ResultSet resultSet) throws SQLException {

		QueryExecutor jdbcQueryExecutor = new DefaultJDBCQueryExecutor(resultSet);

		return jdbcQueryExecutor;
	}

	protected AbstractJDBCQueryExecutor(JDBCConnection jdbcConnection, QueryParameter queryParameter) {

		this.jdbcConnection = jdbcConnection;
		this.queryParameter = queryParameter;
	}

	protected AbstractJDBCQueryExecutor(ResultSet resultSet) throws SQLException {

		this.resultSet = resultSet;

		convertResultSetToJDBCRowList();
	}

	protected ResultSet getResultSet() throws SQLException {

		if (isClosed()) {
			throw new SQLException("the resultset has been closed!!!");
		}

		return resultSet;
	}

	protected JDBCRow convertCurrentRowToJDBCRow() throws SQLException {

		if (jdbcQueryResultMetaData == null) {
			jdbcQueryResultMetaData = JDBCFactory.newJDBCQueryResultMetaData(getResultSet().getMetaData());
		}

		int columnCount = jdbcQueryResultMetaData.getColumnCount();

		JDBCRow jdbcRow = JDBCFactory.newJDBCRow();

		jdbcRow.setRowNumber(getResultSet().getRow());

		for (int columnNumber = 1; columnNumber <= columnCount; columnNumber++) {
			QueryResultColumnMeta queryResultColumnMeta = jdbcQueryResultMetaData.getColumnMeta(columnNumber);
			Object rawValue = JDBCUtil.getValue(getResultSet(), columnNumber, queryResultColumnMeta.getColumnType());
			JDBCColumn jdbcColumn = JDBCFactory.newJDBCColumn(queryResultColumnMeta, rawValue);
			jdbcRow.addJDBCColumn(jdbcColumn);
		}

		ArrayUtil.add(jdbcRowCollection, jdbcRow.getRowNumber(), jdbcRow);// TODO
																			// largeMode

		return jdbcRow;
	}

	protected void convertResultSetToJDBCRowList() throws SQLException {

		jdbcRowCollection.clear();

		while (getResultSet().next()) {
			convertCurrentRowToJDBCRow();
		}

		close();
	}

	@Override
	public void execute() throws SQLException {

		boolean isLargeMode = queryParameter.isLargeMode();

		try {
			preparedStatement = JDBCUtil.queryStatement(jdbcConnection, queryParameter);

			resultSet = preparedStatement.executeQuery();

			if (!isLargeMode) {
				convertResultSetToJDBCRowList();
			}
		} catch (SQLException e) {
			throwable = e;
			statusCode = e.getSQLState();
			errorMessage = e.getMessage();
		} catch (Exception e) {
			throwable = e;
			errorMessage = e.getMessage();
		} finally {
			if (!isLargeMode || failure()) {
				close();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean success() {

		return (throwable == null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean failure() {

		return (throwable != null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStatusCode() {

		return statusCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getErrorMessage() {

		return errorMessage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Throwable getThrowable() {

		return throwable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final QueryParameter getQueryParameter() {

		return queryParameter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasColumn(int columnNumber) throws SQLException {

		return (columnNumber >= 1 && columnNumber <= jdbcQueryResultMetaData.getColumnCount());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasColumn(String columnName) throws SQLException {

		Assertion.assertNotBlank("columnName", columnName);

		return jdbcQueryResultMetaData.hasColumn(columnName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<Object> getValues() throws SQLException {

		JDBCRow jdbcRow = getRow();

		List<Object> jdbcValues = new ArrayList<>();
		for (int columnNumber = 1; columnNumber <= jdbcRow.getColumnCount(); columnNumber++) {
			jdbcValues.add(jdbcRow.getValue(columnNumber));
		}

		return jdbcValues;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<JDBCValue> getJDBCValues() throws SQLException {

		JDBCRow jdbcRow = getRow();

		List<JDBCValue> jdbcValues = new ArrayList<>();
		for (int columnNumber = 1; columnNumber <= jdbcRow.getColumnCount(); columnNumber++) {
			jdbcValues.add(jdbcRow.getJDBCValue(columnNumber));
		}

		return jdbcValues;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {

		JDBCUtil.close(resultSet);
		JDBCUtil.close(preparedStatement);

		resultSet = null;
		preparedStatement = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() throws SQLException {

		if (!JDBCUtil.isClosed(preparedStatement)) {
			return false;
		}

		if (!JDBCUtil.isClosed(resultSet)) {
			return false;
		}

		return true;
	}
}
