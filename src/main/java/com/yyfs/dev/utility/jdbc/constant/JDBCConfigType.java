package com.yyfs.dev.utility.jdbc.constant;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public enum JDBCConfigType {
	// urlPattern = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dataBaseName;
	ORACLE("oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s"),

	// urlPattern = "jdbc:mysql://" + host + ":" + port + ":" + dataBaseName;
	MYSQL("com.mysql.jdbc.Driver", "jdbc:mysql://%s:%s:%s"),

	// urlPattern = "jdbc:postgresql://" + host + ":" + port + "/" + dataBaseName;
	POSTGRESQL("org.postgresql.Driver", "jdbc:postgresql://%s:%s:%s");

	private String driver;

	private String urlPattern;

	private JDBCConfigType(String driver, String urlPattern) {

		this.driver = driver;
		this.urlPattern = urlPattern;
	}

	/**
	 * @return driver
	 */
	public String getDriver() {

		return driver;
	}

	public String getURL(String host, String port, String database) {

		return String.format(urlPattern, host, port, database);
	}

}
