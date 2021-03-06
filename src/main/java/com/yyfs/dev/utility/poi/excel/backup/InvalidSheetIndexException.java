package com.yyfs.dev.utility.poi.excel.backup;

import com.yyfs.dev.utility.exception.InvalidException;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/03
 */
public class InvalidSheetIndexException extends InvalidException {

	private static final long serialVersionUID = 3238847704242520820L;

	public InvalidSheetIndexException(int sheetIndex) {

		super("invalid sheet index:" + sheetIndex);
	}
}
