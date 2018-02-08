package com.yyfs.dev.utility.poi.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import com.yyfs.dev.utility.poi.excel.ExcelFactory;
import com.yyfs.dev.utility.poi.excel.ExcelObject;
import com.yyfs.dev.utility.poi.excel.SheetObject;

public class TestExcelObject {

	private static final String TEST_TPATH = "D:/TEMP/excel/";

	@Test
	public void createHSSFExcelObject() throws IOException {

		ExcelObject excelObject = ExcelFactory.createHSSFExcelObject();
		excelObject.createSheet();
		excelObject.createSheet();
		excelObject.createSheet();
		SheetObject sheetObject = excelObject.createSheet("Test Sheet", 1);

		String fileName = TEST_TPATH + "createHSSFExcelObject.xls";
		OutputStream outputStream = Files.newOutputStream(Paths.get(fileName));
		excelObject.write(outputStream);
		excelObject.close();
	}

	@Test
	public void createXSSFExcelObject() throws IOException {

		ExcelObject excelObject = ExcelFactory.createXSSFExcelObject();
		excelObject.createSheet();
		excelObject.createSheet();
		excelObject.createSheet();
		SheetObject sheetObject = excelObject.createSheet("Test Sheet", 1);

		String fileName = TEST_TPATH + "createXSSFExcelObject.xlsx";
		OutputStream outputStream = Files.newOutputStream(Paths.get(fileName));
		excelObject.write(outputStream);
		excelObject.close();
	}

	@Test
	public void createSXSSFExcelObject() throws IOException {

		ExcelObject excelObject = ExcelFactory.createSXSSFExcelObject();
		excelObject.createSheet();
		excelObject.createSheet();
		excelObject.createSheet();
		SheetObject sheetObject = excelObject.createSheet("Test Sheet", 1);

		String fileName = TEST_TPATH + "createSXSSFExcelObject.xlsx";
		OutputStream outputStream = Files.newOutputStream(Paths.get(fileName));
		excelObject.write(outputStream);
		excelObject.close();
	}
}
