package com.yyfs.dev.utility.base.common;

import java.util.HashMap;
import java.util.Map;

import com.yyfs.dev.utility.base.validate.Assertion;

public class TextUtil {

	/**
	 *
	 * @param input
	 * @return
	 */
	public static boolean isBlank(String input) {

		return (input == null || input.isEmpty());
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public static boolean isNotBlank(String input) {

		return (input != null && !input.isEmpty());
	}

	public static String trim(String input) {

		if (!isBlank(input)) {
			return input.trim();
		}

		return input;
	}

	public static boolean equals(String str1, String str2) {

		if ((str1 == null) ^ (str2 == null)) {
			return false;
		}

		if (str1 != null) {
			return str1.equals(str2);
		}

		return true;
	}

	/**
	 * 入力データがすべて同じかチェックうする
	 *
	 * @param array
	 * @return
	 */
	public static boolean isEquals(String... array) {

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {

				if (array[i] != null && array[i].equals(array[j])) {
					continue;
				}

				if (array[j] != null && array[j].equals(array[i])) {
					continue;
				}

				if (array[i] == null && array[j] == null) {
					continue;
				}

				return false;
			}
		}

		return true;
	}

	public static boolean isNotEquals(String... array) {

		return !isEquals(array);
	}

	public static String deleteLast(String content, int length) {

		return null;
	}

	public static String deleteFirst(String content, int length) {

		return null;
	}

	/**
	 * 入力データがすべて同じかチェックうする
	 *
	 * @param array
	 * @return
	 */
	public static boolean isEqualsIgnoreCase(String... array) {

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {

				if (array[i] != null && array[i].equalsIgnoreCase(array[j])) {
					continue;
				}

				if (array[j] != null && array[j].equalsIgnoreCase(array[i])) {
					continue;
				}

				if (array[i] == null && array[j] == null) {
					continue;
				}

				return false;
			}
		}

		return true;
	}

	/**
	 * nullの場合、空文字に返す。
	 *
	 * @return
	 */
	public static String repaceNull(String input) {

		String result = input;
		if (input == null) {
			result = "";
		}

		return result;
	}

	/**
	 *
	 * @param input
	 * @param length
	 * @return
	 */
	public static String lPad(String input, int length) {

		if (input == null || length <= 0) {
			return input;
		}

		String strResult = input;
		while (length > strResult.length()) {
			strResult = " " + strResult;
		}

		return strResult;
	}

	/**
	 *
	 * @param input
	 * @param length
	 * @return
	 */
	public static String lPad(String input, int length, String padSequence) {

		if (input == null || length <= 0) {
			return input;
		}

		String strResult = input;
		while (length > strResult.length()) {
			strResult = padSequence + strResult;
		}

		return strResult;
	}

	/**
	 *
	 * @param str
	 * @param length
	 * @return
	 */
	public static String rPad(String input, int length) {

		if (input == null || length <= 0) {
			return input;
		}

		String strResult = input;
		while (length > strResult.length()) {
			strResult += " ";
		}

		return strResult;
	}

	/**
	 *
	 * @param input
	 * @param times
	 * @return
	 */
	public static String repeat(String input, int times) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < times; i++) {
			builder.append(input);
		}
		return builder.toString();
	}

	/**
	 *
	 * @param content
	 * @return
	 */
	public static String capital(String content) {

		Assertion.assertNotNull("content", content);

		if (content.isEmpty()) {
			return "";
		}

		StringBuilder sBuilder = new StringBuilder(content);

		String firstChar = sBuilder.deleteCharAt(0).toString().toUpperCase();

		sBuilder.insert(0, firstChar);

		return sBuilder.toString();
	}

	public static String convertHalfToFull(String content) {

		if (isBlank(content)) {
			return content;
		}

		content = NumberUtil.convertHalf2Full4Number(content);
		content = convertHanToZenForKKana(content);
		content = convertHanToZenuForAlphabet(content);

		content = content.replace(" ", "　");
		content = content.replace("(", "（");
		content = content.replace(")", "）");

		return content;
	}

	public static String convertHanToZenuForAlphabet(String s) {

		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z') {
				sb.setCharAt(i, (char) (c - 'a' + 'ａ'));
			} else if (c >= 'A' && c <= 'Z') {
				sb.setCharAt(i, (char) (c - 'A' + 'Ａ'));
			}
		}
		return sb.toString();
	}

	public static String convertZenToHanForAlphabet(String s) {

		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= 'ａ' && c <= 'ｚ') {
				sb.setCharAt(i, (char) (c - 'ａ' + 'a'));
			} else if (c >= 'Ａ' && c <= 'Ｚ') {
				sb.setCharAt(i, (char) (c - 'Ａ' + 'A'));
			}
		}
		return sb.toString();
	}

	private static final String kanaHanZenTbl[][] = {
			// 2文字構成の濁点付き半角カナ必ずテーブルに先頭に置いてサーチ順を優先すること
			{ "ｶﾞ", "ガ" }, { "ｷﾞ", "ギ" }, { "ｸﾞ", "グ" }, { "ｹﾞ", "ゲ" }, { "ｺﾞ", "ゴ" }, { "ｻﾞ", "ザ" }, { "ｼﾞ", "ジ" }, { "ｽﾞ", "ズ" }, { "ｾﾞ", "ゼ" },
			{ "ｿﾞ", "ゾ" }, { "ﾀﾞ", "ダ" }, { "ﾁﾞ", "ヂ" }, { "ﾂﾞ", "ヅ" }, { "ﾃﾞ", "デ" }, { "ﾄﾞ", "ド" }, { "ﾊﾞ", "バ" }, { "ﾋﾞ", "ビ" }, { "ﾌﾞ", "ブ" },
			{ "ﾍﾞ", "ベ" }, { "ﾎﾞ", "ボ" }, { "ﾊﾟ", "パ" }, { "ﾋﾟ", "ピ" }, { "ﾌﾟ", "プ" }, { "ﾍﾟ", "ペ" }, { "ﾎﾟ", "ポ" }, { "ｳﾞ", "ヴ" },
			// 1文字構成の半角カナ
			{ "ｱ", "ア" }, { "ｲ", "イ" }, { "ｳ", "ウ" }, { "ｴ", "エ" }, { "ｵ", "オ" }, { "ｶ", "カ" }, { "ｷ", "キ" }, { "ｸ", "ク" }, { "ｹ", "ケ" },
			{ "ｺ", "コ" }, { "ｻ", "サ" }, { "ｼ", "シ" }, { "ｽ", "ス" }, { "ｾ", "セ" }, { "ｿ", "ソ" }, { "ﾀ", "タ" }, { "ﾁ", "チ" }, { "ﾂ", "ツ" },
			{ "ﾃ", "テ" }, { "ﾄ", "ト" }, { "ﾅ", "ナ" }, { "ﾆ", "ニ" }, { "ﾇ", "ヌ" }, { "ﾈ", "ネ" }, { "ﾉ", "ノ" }, { "ﾊ", "ハ" }, { "ﾋ", "ヒ" },
			{ "ﾌ", "フ" }, { "ﾍ", "ヘ" }, { "ﾎ", "ホ" }, { "ﾏ", "マ" }, { "ﾐ", "ミ" }, { "ﾑ", "ム" }, { "ﾒ", "メ" }, { "ﾓ", "モ" }, { "ﾔ", "ヤ" },
			{ "ﾕ", "ユ" }, { "ﾖ", "ヨ" }, { "ﾗ", "ラ" }, { "ﾘ", "リ" }, { "ﾙ", "ル" }, { "ﾚ", "レ" }, { "ﾛ", "ロ" }, { "ﾜ", "ワ" }, { "ｦ", "ヲ" },
			{ "ﾝ", "ン" }, { "ｧ", "ァ" }, { "ｨ", "ィ" }, { "ｩ", "ゥ" }, { "ｪ", "ェ" }, { "ｫ", "ォ" }, { "ｬ", "ャ" }, { "ｭ", "ュ" }, { "ｮ", "ョ" },
			{ "ｯ", "ッ" }, { "｡", "。" }, { "｢", "「" }, { "｣", "」" }, { "､", "、" }, { "･", "・" }, { "ｰ", "ー" }, { "", "" } };

	private static final Map<String, String> HAN_ZEN_MAP = new HashMap<String, String>() {

		{
			put("｡", "。");
			put("｢", "「");
			put("｣", "」");
			put("､", "、");
			put("･", "・");
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

	public static String convertHanToZenForKKana(String param) {

		// 変換結果を格納する
		StringBuffer converted = new StringBuffer();
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
				temp = HAN_ZEN_MAP.get(param.substring(index, index + 1));

			} else if (!isDaku) {
				// 次の文字が濁点/半濁点でない場合
				temp = HAN_ZEN_MAP.get(param.substring(index, index + 1));

			} else {
				// 次の文字が濁点/半濁点の場合
				temp = HAN_ZEN_MAP.get(param.substring(index, index + 2));
				if (temp != null) {
					// マップに存在する場合
					index++;
				} else {
					// マップに存在しない場合
					temp = HAN_ZEN_MAP.get(param.substring(index, index + 1));
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

	public static String ZenToHanForKKana(String p) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0, j = 0; i < p.length(); i++) {
			Character c = new Character(p.charAt(i));
			// Unicode全角カタカナのコード範囲か?
			if (c.compareTo(new Character((char) 0x30a1)) >= 0 && c.compareTo(new Character((char) 0x30fc)) <= 0) {
				// 半角全角変換テーブルを検索する
				for (j = 0; j < kanaHanZenTbl.length; j++) {
					if (p.substring(i).startsWith(kanaHanZenTbl[j][1])) {
						sb.append(kanaHanZenTbl[j][0]);
						break;
					}
				}
				// 検索できなければ、変換しない
				if (j >= kanaHanZenTbl.length) {
					sb.append(c);
				}
			} else { // 全角カタカナ以外なら変換しない
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
