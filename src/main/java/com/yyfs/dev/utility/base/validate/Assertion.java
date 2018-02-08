package com.yyfs.dev.utility.base.validate;

import com.yyfs.dev.utility.exception.InvalidException;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/07
 */
public class Assertion {

	/**
	 *
	 * @param name
	 *            項目名
	 * @param object
	 *            項目値
	 */
	public static void assertNotNull(String name, Object object) {

		if (object == null) {
			throw new AssertionException(name + " is null.");
		}
	}

	/**
	 *
	 * @param name
	 *            項目名
	 * @param object
	 *            項目値
	 */
	public static void assertNotBlank(String name, String value) {

		if (value == null || value.isEmpty()) {
			throw new AssertionException(name + " is null or empty.");
		}
	}

	/**
	 *
	 * @param name
	 *            項目名
	 * @param number
	 *            項目値
	 */
	public static void assertPositiveNumber(String name, long number) {

		if (number < 0) {
			throw new InvalidException(name + " is negative number.");
		}
	}

	/**
	 *
	 * @param name
	 *            項目名
	 * @param number
	 *            項目値
	 */
	public static void assertNegativeNumber(String name, long number) {

		if (number > 0) {
			throw new InvalidException(name + " is positive number.");
		}
	}

	/**
	 *
	 * @param expected
	 * @param actual
	 */
	public static void assertType(Class<?> expected, Class<?> actual) {

		assertNotNull("expected", expected);
		assertNotNull("actual", actual);

		if (expected != actual) {
			String message = "expected type is [" + expected + "]. actual type is [" + actual + "].";
			throw new AssertionException(message);
		}
	}

	/**
	 * indexが範囲内かチェックする。<br>
	 * {@code minIndex}<={@code index}<={@code maxIndex}以外の場合、
	 * IndexOutOfBoundsExceptionが発生する。<br>
	 *
	 * @param index
	 *            チェック対象
	 * @param minIndex
	 *            最小Index
	 * @param maxIndex
	 *            最大Index
	 *
	 * @exception IndexOutOfBoundsException
	 */
	public static void isIndexOutOfBounds(int index, int minIndex, int maxIndex) {

		if (index < minIndex || index > maxIndex) {
			String message = "index:" + index + " minIndex:" + minIndex + " maxIndex:" + maxIndex;

			throw new IndexOutOfBoundsException(message);
		}
	}
}
