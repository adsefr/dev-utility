package com.yyfs.dev.utility.poi.excel.backup;

import java.awt.Font;

import org.apache.poi.ss.usermodel.CellStyle;

import com.yyfs.dev.utility.poi.excel.model.CellStyleModel;
import com.yyfs.dev.utility.poi.excel.model.FontModel;

/**
 * Excelファイルを操作するインタフェース
 *
 * @author ri.meisei
 * @since 2013/12/19
 */
interface ExcelOperatorInternal {

	Font getFont(FontModel fontModel);

	CellStyle getCellStyle(CellStyleModel cellStyleModel);

}
