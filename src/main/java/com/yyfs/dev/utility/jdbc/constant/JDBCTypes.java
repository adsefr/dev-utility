package com.yyfs.dev.utility.jdbc.constant;

import java.sql.JDBCType;

import com.yyfs.dev.utility.base.validate.Assertion;
import com.yyfs.dev.utility.jdbc.model.DataBaseMeta;

/**
 *
 * @author ri.meisei
 * @since 2015/11/02
 */
public class JDBCTypes {

	public static JDBCType getJDBCType(int type) {

		return JDBCType.valueOf(type);
	}

	public static JDBCType getJDBCType(DataBaseMeta dataBaseMeta, int type) {

		Assertion.assertNotNull("dataBaseMeta", dataBaseMeta);
		String productName = dataBaseMeta.getDatabaseProductName();

		return getJDBCType(productName, type);
	}

	private static JDBCType getJDBCType(String productName, int type) {

		Assertion.assertNotBlank("productName", productName);

		switch (productName.toUpperCase()) {
			case "ORACLE":
				return OracleType.valueOf(type);
			default:
				return JDBCType.valueOf(type);
		}
	}
}
