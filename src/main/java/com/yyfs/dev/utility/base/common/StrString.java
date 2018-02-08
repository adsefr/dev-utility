package com.yyfs.dev.utility.base.common;

public class StrString {

	public static String voidString(String strNum) {


		 String outNum = "";
		try {
			if (strNum != null) {
				outNum = (strNum + "1");
			} else {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}finally {

		}

		return outNum;

	}
}
