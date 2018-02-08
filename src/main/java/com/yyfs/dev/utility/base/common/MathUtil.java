package com.yyfs.dev.utility.base.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MathUtil {

	static boolean isNumber(String input) {

		return input.matches("\\d{8}");
	}

	static boolean isNullOrEmpty(String input) {

		return (input == null || input.isEmpty());
	}

	private static LocalDate convertLocalTime(String input) {

		try {
			return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyyMMdd"));
		} catch (Exception e) {
			return null;
		}
	}

	public static int getPeriod(String fromDate, String toDate) {

		int b = 0;

		boolean d = isNullOrEmpty(fromDate);
		if (!d) {

		}
		boolean c = isNumber(fromDate);

		if (!c) {

		}

		LocalDate idFrom = convertLocalTime(fromDate);
		if (idFrom == null) {

		}
		LocalDate idTo = convertLocalTime(toDate);
		if (idTo == null) {

		}
		b = (int) ChronoUnit.DAYS.between(idFrom, idTo);

		return b;
	}
}
