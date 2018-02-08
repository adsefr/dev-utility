package com.yyfs.dev.utility.base.common;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public class NumberUtil {

	public static String convertFull2Half4Number(String number) {

		if (number == null) {
			throw new IllegalArgumentException();
		}
		StringBuffer sb = new StringBuffer(number);
		for (int i = 0; i < number.length(); i++) {
			char c = number.charAt(i);
			if ('０' <= c && c <= '９') {
				sb.setCharAt(i, (char) (c - '０' + '0'));
			}
		}
		return sb.toString();
	}

	public static String convertHalf2Full4Number(String number) {

		if (number == null) {
			throw new IllegalArgumentException();
		}
		StringBuffer sb = new StringBuffer(number);
		for (int i = 0; i < number.length(); i++) {
			char c = number.charAt(i);
			if ('0' <= c && c <= '9') {
				sb.setCharAt(i, (char) (c - '0' + '０'));
			}
		}
		return sb.toString();
	}

	public static String convertHalf2Full4Number(int number) {

		return convertHalf2Full4Number(String.valueOf(number));
	}
}
