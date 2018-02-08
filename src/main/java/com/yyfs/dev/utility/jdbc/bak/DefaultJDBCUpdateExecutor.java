package com.yyfs.dev.utility.jdbc.bak;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.rms.utility.base.logging.Logger;
import com.yyfs.dev.utility.jdbc.JDBCConnection;
import com.yyfs.dev.utility.jdbc.UpdateExecutor;
import com.yyfs.dev.utility.jdbc.helper.JDBCUtil;
import com.yyfs.dev.utility.jdbc.model.UpdateParameter;

/**
 *
 * @author ri.meisei
 * @since 2015/08/24
 */
public class DefaultJDBCUpdateExecutor implements UpdateExecutor {

	protected final Logger logger = Logger.getLogger(UpdateExecutor.class);

	private final UpdateParameter updateParameter;

	private final JDBCConnection jdbcConnection;

	private PreparedStatement preparedStatement;

	private Savepoint savepoint;

	private Throwable throwable;

	private String statusCode = "";

	private String errorMessage = "";

	private int updateCount = -1;

	DefaultJDBCUpdateExecutor(JDBCConnection jdbcConnection, UpdateParameter updateParameter) {

		this.jdbcConnection = jdbcConnection;

		this.updateParameter = updateParameter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void execute() throws SQLException {

		try {
			preparedStatement = JDBCUtil.updateStatement(jdbcConnection, updateParameter);

			updateCount = preparedStatement.executeUpdate();

			savepoint = jdbcConnection.getConnection().setSavepoint();

		} catch (SQLException e) {
			throwable = e;
			statusCode = e.getSQLState();
			errorMessage = e.getMessage();
		} catch (Exception e) {
			throwable = e;
			errorMessage = e.getMessage();
		} finally {
			if (failure()) {
				rollback();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean success() {

		return (throwable == null && errorMessage.isEmpty());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean failure() {

		return (throwable != null || !errorMessage.isEmpty());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getStatusCode() {

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
	public final Throwable getThrowable() {

		return throwable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getUpdateCount() {

		return updateCount;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void rollback() throws SQLException {

		if (savepoint != null) {
			jdbcConnection.getConnection().rollback(savepoint);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {

		JDBCUtil.close(preparedStatement);

		preparedStatement = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() throws SQLException {

		return JDBCUtil.isClosed(preparedStatement);
	}

}
