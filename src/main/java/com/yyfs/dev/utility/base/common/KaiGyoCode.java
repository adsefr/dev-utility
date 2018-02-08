package com.yyfs.dev.utility.base.common;


public class KaiGyoCode {

	public static String A(String inPutString) {

		String STRING = "産額";

		String outString = "";
		if (inPutString == null || inPutString.isEmpty()) {
			return inPutString;
		}else {
			outString = inPutString.replaceAll("\r\n", STRING).replaceAll("(\r|\n)", STRING);
		}

		return outString;

	}
}
