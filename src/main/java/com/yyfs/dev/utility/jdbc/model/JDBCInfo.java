package com.yyfs.dev.utility.jdbc.model;

import com.yyfs.dev.utility.jdbc.constant.HoldabilityType;
import com.yyfs.dev.utility.jdbc.constant.TransactionType;

/**
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public class JDBCInfo {

	private String userId;

	private String password;

	private String host;

	private String port;

	private String dataBase;

	private boolean autoCommit = false;

	private boolean readOnly = false;

	private TransactionType transactionType = TransactionType.TRANSACTION_READ_COMMITTED;

	private HoldabilityType holdabilityType = HoldabilityType.CLOSE_CURSORS_AT_COMMIT;

	public JDBCInfo() {

	}

	/**
	 * @return host
	 */
	public String getHost() {

		return host;
	}

	/**
	 * @param host
	 *            セットする host
	 */
	public void setHost(String host) {

		this.host = host;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {

		return userId;
	}

	/**
	 * @param userId
	 *            セットする userId
	 */
	public void setUserId(String userId) {

		this.userId = userId;
	}

	/**
	 * @return password
	 */
	public String getPassword() {

		return password;
	}

	/**
	 * @param password
	 *            セットする password
	 */
	public void setPassword(String password) {

		this.password = password;
	}

	/**
	 * @return port
	 */
	public String getPort() {

		return port;
	}

	/**
	 * @param port
	 *            セットする port
	 */
	public void setPort(String port) {

		this.port = port;
	}

	/**
	 * @return dataBase
	 */
	public String getDataBase() {

		return dataBase;
	}

	/**
	 * @param dataBase
	 *            セットする dataBase
	 */
	public void setDataBase(String dataBase) {

		this.dataBase = dataBase;
	}

	/**
	 * @return autoCommit
	 */
	public boolean isAutoCommit() {

		return autoCommit;
	}

	/**
	 * @param autoCommit
	 *            セットする autoCommit
	 */
	public void setAutoCommit(boolean autoCommit) {

		this.autoCommit = autoCommit;
	}

	/**
	 * @return readOnly
	 */
	public boolean isReadOnly() {

		return readOnly;
	}

	/**
	 * @param readOnly
	 *            セットする readOnly
	 */
	public void setReadOnly(boolean readOnly) {

		this.readOnly = readOnly;
	}

	/**
	 * @return transactionType
	 */
	public TransactionType getTransactionType() {

		return transactionType;
	}

	/**
	 * @param transactionType
	 *            セットする transactionType
	 */
	public void setTransactionType(TransactionType transactionType) {

		this.transactionType = transactionType;
	}

	/**
	 * @return holdabilityType
	 */
	public HoldabilityType getHoldabilityType() {

		return holdabilityType;
	}

	/**
	 * @param holdabilityType
	 *            セットする holdabilityType
	 */
	public void setHoldabilityType(HoldabilityType holdabilityType) {

		this.holdabilityType = holdabilityType;
	}
}
