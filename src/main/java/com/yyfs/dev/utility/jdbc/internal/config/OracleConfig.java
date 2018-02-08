package com.yyfs.dev.utility.jdbc.internal.config;

import com.yyfs.dev.utility.jdbc.constant.JDBCConfigType;
import com.yyfs.dev.utility.jdbc.model.JDBCInfo;

public class OracleConfig extends DefaultJDBCConfig {

	public OracleConfig(JDBCInfo info) {
		super(JDBCConfigType.ORACLE, info);
	}
}
