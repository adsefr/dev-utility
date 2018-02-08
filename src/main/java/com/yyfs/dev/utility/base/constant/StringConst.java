package com.yyfs.dev.utility.base.constant;

/**
 * 文字定数を定義するクラス
 *
 * @author ri.meisei
 * @since 2014/01/31
 */
public enum StringConst {

	BLANK(""),

	SPACE_HALF(" "),

	SPACE_FULL("　"),

	UNDERSCORE("_"),

	COMMA(","),

	DOT("."),

	COLON(":"),

	SEMICOLON(";"),

	QUOTATION("\""),

	TAB("\t"),

	SYSTEM_LINE_SEPARATOR(System.getProperty("line.separator")),

	CRLF("\r\n"),

	CR("\r"),

	LF("\n");

	private String value;

	private StringConst(String value) {
		this.value = value;
	}

	public String value() {

		return value;
	}

}
