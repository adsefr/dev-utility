package com.yyfs.dev.utility.poi.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
class HSSFExcelObject extends AbstractExcelObject {

	private HSSFWorkbook hssfWorkbook;

	HSSFExcelObject(HSSFWorkbook hssfWorkbook) {
		super(hssfWorkbook, SpreadsheetVersion.EXCEL97);
		this.hssfWorkbook = hssfWorkbook;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(int sheetIndex) {

		Sheet sheet = hssfWorkbook.getSheetAt(sheetIndex);
		SheetObject sheetObject = new HSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(String sheetName) {

		Sheet sheet = hssfWorkbook.getSheet(sheetName);
		SheetObject sheetObject = new HSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}

	@Override
	public HSSFSheetObject createSheetObject(Sheet sheet) {

		return new HSSFSheetObject(sheet);
	}
}
