package com.yyfs.dev.utility.jdbc.internal.config;

import com.yyfs.dev.utility.jdbc.constant.JDBCConfigType;
import com.yyfs.dev.utility.jdbc.model.JDBCInfo;

public class MySQLConfig extends DefaultJDBCConfig {

	public MySQLConfig(JDBCInfo info) {
		super(JDBCConfigType.MYSQL, info);
	}
}
