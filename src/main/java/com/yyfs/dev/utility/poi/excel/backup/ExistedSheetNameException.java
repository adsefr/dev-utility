package com.yyfs.dev.utility.poi.excel.backup;

import com.yyfs.dev.utility.exception.ExistedDataException;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/03
 */
public class ExistedSheetNameException extends ExistedDataException {

	private static final long serialVersionUID = 4046022639942781618L;

	public ExistedSheetNameException(String sheetName) {

		super("invalid sheet name:" + sheetName);
	}
}
