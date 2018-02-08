package com.yyfs.dev.utility.base.common;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Test;

public class StrStringTest {

	@Test
	public void test() {
		for (int i = 0; i < 256; i++) {
			System.out.println(i+" "+ (char)i);

		}

		String string = "1ac32er33d4";
		for (int i = 0; i < string.length(); i++) {
			System.out.println(string.substring(i, i + 1) + " " + (int)string.charAt(i));
		}

		System.out.println("あ".getBytes().length);
		System.out.println("あ".getBytes(Charset.forName("SJIS")).length);

		try {
			String retust = StrString.voidString(null);
			// assertEquals(null, retust);
		} catch (Exception e) {
			fail();
		}

	}

}
