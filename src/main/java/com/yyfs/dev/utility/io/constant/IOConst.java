package com.yyfs.dev.utility.io.constant;

import java.nio.charset.Charset;

import com.yyfs.dev.utility.base.constant.Encodes;
import com.yyfs.dev.utility.base.constant.StringConst;

/**
 * ファイルに関する定数を定義クラス
 *
 * @author ri.meisei
 * @since 2013/11/01
 */
public class IOConst {

	public final static int DEFAULT_BUFFER_SIZE = 8192;

	public final static String DEFAULT_LINE_SEPARATOR = StringConst.SYSTEM_LINE_SEPARATOR.toString();

	public final static Charset DEFAULT_CHARSET = Charset.forName(Encodes.ENCODE_UTF8);
}
