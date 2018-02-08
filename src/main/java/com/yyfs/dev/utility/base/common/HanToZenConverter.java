package com.yyfs.dev.utility.base.common;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HanToZenConverter {

	/**
	 *
	 * @param mmdd
	 *            有効な月日
	 * @return
	 */
	public static int convertMMDD2YYYYMMDD(int mmdd) {

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int yyyymmdd = year * 10000 + mmdd;
		// yyyymmddが無効な場合
		// 日付の有効性チェックをここに追加する

		return yyyymmdd;
	}

	/**
	 * 全ての文字が半角文字かチェックする。
	 *
	 * @param content
	 * @return
	 */
	public static boolean isAllHalf(String content) {

		for (int index = 0, length = content.length() - 1; index <= length; index++) {
			char c = content.charAt(index);

			if (c < 128) {
				// ASCIIコードの場合
				continue;
			}

			if (c >= 0xff61 && c <= 0xff9f) {
				// 半角カナの場合
				continue;
			}

			//特別文字があれば、追加する

			// 上記以外は全角文字と見なす
			return false;
		}

		return true;
	}

	public static String convertHalfToFull(String content) {

		if (content == null || content.isEmpty()) {
			return content;
		}

		content = convertHalf2Full4Number(content);
		content = convertHalf2Full4Alphabet(content);
		content = convertHalf2Fll4Kana(content);
		content = convertHalf2Fll4Sign(content);

		return content;
	}

	/**
	 * 半角数字を全角数字に変換する。
	 *
	 * @param number
	 * @return
	 */
	private static String convertHalf2Full4Number(String number) {

		if (number == null || number.isEmpty()) {
			return number;
		}

		StringBuilder converted = new StringBuilder(number);
		for (int i = 0; i < number.length(); i++) {
			char c = number.charAt(i);
			if ('0' <= c && c <= '9') {
				converted.setCharAt(i, (char) (c - '0' + '０'));
			}
		}
		return converted.toString();
	}

	/**
	 * 半角アルファ文字を全角アルファ文字に変換する。
	 *
	 * @param sequnece
	 * @return
	 */
	private static String convertHalf2Full4Alphabet(String sequnece) {

		if (sequnece == null || sequnece.isEmpty()) {
			return sequnece;
		}

		StringBuilder converted = new StringBuilder(sequnece);

		for (int i = 0; i < sequnece.length(); i++) {
			char c = sequnece.charAt(i);
			if (c >= 'a' && c <= 'z') {
				converted.setCharAt(i, (char) (c - 'a' + 'ａ'));
			} else if (c >= 'A' && c <= 'Z') {
				converted.setCharAt(i, (char) (c - 'A' + 'Ａ'));
			}
		}
		return converted.toString();
	}

	/**
	 * 半角カナを全角カナに変換する
	 *
	 * @param param
	 * @return
	 */
	private static String convertHalf2Fll4Kana(String param) {

		// 変換結果を格納する
		StringBuilder converted = new StringBuilder();
		// 最後の文字か表すフラグ
		boolean isLast;
		// 半角カナ文字か表すフラグ
		boolean isHalf;
		// 次の文字が濁点か表すフラグ
		boolean isDaku;
		// 変換結果を格納する
		String temp;

		for (int index = 0, length = param.length() - 1; index <= length; index++) {
			isLast = (index == length);
			isHalf = (param.charAt(index) >= 0xff61 && param.charAt(index) <= 0xff9f);
			isDaku = isLast ? false : (param.charAt(index + 1) == 0xFF9E || param.charAt(index + 1) == 0xFF9F);

			if (!isHalf) {
				// 半角カナ文字でない場合
				temp = param.substring(index, index + 1);

			} else if (isLast) {
				// 最後の一文字の場合
				temp = HALF2FULL_KANA_MAP.get(param.substring(index, index + 1));

			} else if (!isDaku) {
				// 次の文字が濁点/半濁点でない場合
				temp = HALF2FULL_KANA_MAP.get(param.substring(index, index + 1));

			} else {
				// 次の文字が濁点/半濁点の場合
				temp = HALF2FULL_KANA_MAP.get(param.substring(index, index + 2));
				if (temp != null) {
					// マップに存在する場合
					index++;
				} else {
					// マップに存在しない場合
					temp = HALF2FULL_KANA_MAP.get(param.substring(index, index + 1));
				}
			}

			// 変換結果がnullの場合、変換しない
			if (temp == null) {
				temp = param.substring(index, index + 1);
			}

			converted.append(temp);

		}

		return converted.toString();
	}

	/**
	 * 半角記号を全角記号に変換する。
	 *
	 * @param param
	 * @return
	 */
	private static String convertHalf2Fll4Sign(String param) {

		if (param == null || param.isEmpty()) {
			return param;
		}
		StringBuilder converted = new StringBuilder(param.length());
		for (int index = 0, length = param.length() - 1; index <= length; index++) {
			String str = param.substring(index, index + 1);
			String rst = HALF2FULL_SIGN_MAP.get(str);
			if (rst == null) {
				converted.append(str);
			} else {
				converted.append(str);
			}
		}

		return converted.toString();

	}

	private static final Map<String, String> HALF2FULL_SIGN_MAP = new HashMap<String, String>() {

		{
			put("｡", "。");
			put("｢", "「");
			put("｣", "」");
			put("､", "、");
			put("･", "・");
			put("･", "・");
			put("(", "（");
			put(".", "。");
			put(" ", "　");
		}
	};

	private static final Map<String, String> HALF2FULL_KANA_MAP = new HashMap<String, String>() {

		{
			put("ｦ", "ヲ");
			put("ｧ", "ァ");
			put("ｨ", "ィ");
			put("ｩ", "ゥ");
			put("ｪ", "ェ");
			put("ｫ", "ォ");
			put("ｬ", "ャ");
			put("ｭ", "ュ");
			put("ｮ", "ョ");
			put("ｯ", "ッ");
			put("ｰ", "ー");
			put("ｱ", "ア");
			put("ｲ", "イ");
			put("ｳ", "ウ");
			put("ｳﾞ", "ヴ");
			put("ｴ", "エ");
			put("ｵ", "オ");
			put("ｶ", "カ");
			put("ｶﾞ", "ガ");
			put("ｷ", "キ");
			put("ｷﾞ", "ギ");
			put("ｸ", "ク");
			put("ｸﾞ", "グ");
			put("ｹ", "ケ");
			put("ｹﾞ", "ゲ");
			put("ｺ", "コ");
			put("ｺﾞ", "ゴ");
			put("ｻ", "サ");
			put("ｻﾞ", "ザ");
			put("ｼ", "シ");
			put("ｼﾞ", "ジ");
			put("ｽ", "ス");
			put("ｽﾞ", "ズ");
			put("ｾ", "セ");
			put("ｾﾞ", "ゼ");
			put("ｿ", "ソ");
			put("ｿﾞ", "ゾ");
			put("ﾀ", "タ");
			put("ﾀﾞ", "ダ");
			put("ﾁ", "チ");
			put("ﾁﾞ", "ヂ");
			put("ﾂ", "ツ");
			put("ﾂﾞ", "ヅ");
			put("ﾃ", "テ");
			put("ﾃﾞ", "デ");
			put("ﾄ", "ト");
			put("ﾄﾞ", "ド");
			put("ﾅ", "ナ");
			put("ﾆ", "ニ");
			put("ﾇ", "ヌ");
			put("ﾈ", "ネ");
			put("ﾉ", "ノ");
			put("ﾊ", "ハ");
			put("ﾊﾞ", "バ");
			put("ﾊﾟ", "パ");
			put("ﾋ", "ヒ");
			put("ﾋﾞ", "ビ");
			put("ﾋﾟ", "ピ");
			put("ﾌ", "フ");
			put("ﾌﾞ", "ブ");
			put("ﾌﾟ", "プ");
			put("ﾍ", "ヘ");
			put("ﾍﾞ", "ベ");
			put("ﾍﾟ", "ペ");
			put("ﾎ", "ホ");
			put("ﾎﾞ", "ボ");
			put("ﾎﾟ", "ポ");
			put("ﾏ", "マ");
			put("ﾐ", "ミ");
			put("ﾑ", "ム");
			put("ﾒ", "メ");
			put("ﾓ", "モ");
			put("ﾔ", "ヤ");
			put("ﾕ", "ユ");
			put("ﾖ", "ヨ");
			put("ﾗ", "ラ");
			put("ﾘ", "リ");
			put("ﾙ", "ル");
			put("ﾚ", "レ");
			put("ﾛ", "ロ");
			put("ﾜ", "ワ");
			put("ﾝ", "ン");
		}
	};
}
