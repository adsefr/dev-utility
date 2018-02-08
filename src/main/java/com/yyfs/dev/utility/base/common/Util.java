package com.yyfs.dev.utility.base.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;

public class Util {

	private final static String KUBUN_1 = "1";

	private final static String KUBUN_2 = "2";

	private final static String KUBUN_3 = "3";

	private final static String FORMAT_YYYYMMDD = "yyyyMMdd";

	public static void main(String[] args) {

		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
		// .withChronology(IsoChronology.INSTANCE)
		// .withResolverStyle(ResolverStyle.STRICT);

		try {

			DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyyMMdd").toFormatter()
					.withResolverStyle(ResolverStyle.STRICT);
			LocalDate localDate = LocalDate.parse("20170227", formatter);
			LocalDate localDate2 = LocalDate.parse("20170228", formatter);

			int a = (int) ChronoUnit.DAYS.between(localDate, localDate2);

			System.out.println(a);
		} catch (Exception e) {
//		throw new RuntimeException("無効なパラメータ");
			StackTraceElement st =	Thread.currentThread().getStackTrace()[1];
		throw new RuntimeException(e.getMessage());
		}
	}


	// @Test
	// public void test() {
	//
	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd").withLocale(Locale.JAPAN).withResolverStyle(ResolverStyle.SMART);
	// // TemporalAccessor accessor = formatter.parse("2017/12/12");
	// LocalDate localDate = LocalDate.parse("2017/02/23", formatter);
	// System.out.println(localDate);
	// }j

	/**
	 *
	 * @param mmdd
	 *            基準月日
	 * @param kubun
	 *            処理区分
	 * @param yyyymmdd
	 *            処理年月日
	 * @return
	 */
	public static String validate(String mmdd, String kubun, String yyyymmdd) {

		/** 基準年月のチェック */
		if (!mmdd.matches("\\d{4}")) {
			// 全部数字でない場合、エラーメッセージ設定
		} else if (Integer.parseInt(mmdd) == 0) {
			// 基準年月が０の場合、、エラーメッセージ設定
		}

		/** 処理年月日のチェック */
		if (!yyyymmdd.matches("\\d+")) {
			// 全部数字でない場合、エラーメッセージ設定
		} else if (Integer.parseInt(yyyymmdd) == 0) {
			// 処理年月日が０の場合、、エラーメッセージ設定
		} else if (!isValid(yyyymmdd)) {
			// 処理年月日が無効な日付の場合、エラーメッセージ設定
		}

		/** 処理区分のチェック */
		if (!(KUBUN_1.equals(kubun) || KUBUN_2.equals(kubun) || KUBUN_3.equals(kubun))) {
			// 処理区分が１、２、３以外の場合、エラーメッセージ設定
		}

		int month = Integer.parseInt(yyyymmdd.substring(4, 2));
		int month2 = Integer.parseInt(mmdd.substring(0, 2));

		if (KUBUN_1.equals(kubun) && (month > month2)) {
			// 範囲外
		}
		if (KUBUN_2.equals(kubun) && (month > month2)) {
			// 範囲外

		}
		if (KUBUN_3.equals(kubun) && (Math.abs(month - month2) > 5)) {
			// 範囲外
		}

		// 範囲内の場合
		return new StringBuilder(yyyymmdd).replace(4, 7, mmdd).toString();
	}

	/**
	 * 有効な年月日かチェックする
	 *
	 * @param yyyymmdd
	 * @return
	 */
	private static boolean isValid(String yyyymmdd) {

		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDD);
		String temp = "";
		try {
			temp = sdf.format(sdf.parse(yyyymmdd));
			return yyyymmdd.equals(temp);
		} catch (ParseException e) {
			return false;
		}
	}

}
