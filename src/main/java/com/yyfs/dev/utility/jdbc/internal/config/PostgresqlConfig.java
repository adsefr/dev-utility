package com.yyfs.dev.utility.jdbc.internal.config;

import com.yyfs.dev.utility.jdbc.constant.JDBCConfigType;
import com.yyfs.dev.utility.jdbc.model.JDBCInfo;

public class PostgresqlConfig extends DefaultJDBCConfig {

	public PostgresqlConfig(JDBCInfo info) {
		super(JDBCConfigType.POSTGRESQL, info);
	}
}
