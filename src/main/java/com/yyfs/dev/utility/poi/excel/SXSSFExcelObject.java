package com.yyfs.dev.utility.poi.excel;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
class SXSSFExcelObject extends AbstractExcelObject {

	private SXSSFWorkbook sxssfWorkbook;

	public SXSSFExcelObject(SXSSFWorkbook sxssfWorkbook) {
		super(sxssfWorkbook, SpreadsheetVersion.EXCEL2007);
		this.sxssfWorkbook = sxssfWorkbook;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(int sheetIndex) {

		Sheet sheet = sxssfWorkbook.getSheetAt(sheetIndex);
		SheetObject sheetObject = new SXSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(String sheetName) {

		Sheet sheet = sxssfWorkbook.getSheet(sheetName);
		SheetObject sheetObject = new SXSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}

	@Override
	public XSSFSheetObject createSheetObject(Sheet sheet) {

		return new XSSFSheetObject(sheet);
	}
}
