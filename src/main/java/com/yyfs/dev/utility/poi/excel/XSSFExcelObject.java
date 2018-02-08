package com.yyfs.dev.utility.poi.excel;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
class XSSFExcelObject extends AbstractExcelObject {

	private XSSFWorkbook xssfWorkbook;

	public XSSFExcelObject(XSSFWorkbook xssfWorkbook) {
		super(xssfWorkbook, SpreadsheetVersion.EXCEL2007);
		this.xssfWorkbook = xssfWorkbook;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(int sheetIndex) {

		Sheet sheet = xssfWorkbook.getSheetAt(sheetIndex);
		SheetObject sheetObject = new XSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(String sheetName) {

		Sheet sheet = xssfWorkbook.getSheet(sheetName);
		SheetObject sheetObject = new XSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}

	@Override
	public SXSSFSheetObject createSheetObject(Sheet sheet) {

		return new SXSSFSheetObject(sheet);
	}
}
