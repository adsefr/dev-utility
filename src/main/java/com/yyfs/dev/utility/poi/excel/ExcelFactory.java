package com.yyfs.dev.utility.poi.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yyfs.dev.utility.base.validate.Assertion;
import com.yyfs.dev.utility.exception.LoadFileFailureException;

/**
 *
 * @author rimeisei
 * @since 2018/02/02
 */
public final class ExcelFactory {

	public static ExcelObject createHSSFExcelObject() {

		return new HSSFExcelObject(new HSSFWorkbook());
	}

	public static ExcelObject createXSSFExcelObject() {

		return new XSSFExcelObject(new XSSFWorkbook());
	}

	public static ExcelObject createSXSSFExcelObject() {

		return new SXSSFExcelObject(new SXSSFWorkbook());
	}

	/**
	 * Excelファイルをロードする。
	 *
	 * @param excelFile
	 * @return
	 * @throws LoadFileFailureException
	 */
	public static ExcelObject loadl(File excelFile) throws LoadFileFailureException {

		Assertion.assertNotNull("excelFile", excelFile);

		Workbook workbook = null;
		try (InputStream is = new FileInputStream(excelFile)) {
			workbook = WorkbookFactory.create(is);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			throw new LoadFileFailureException("excelFile:" + excelFile.getAbsolutePath());
		}

		ExcelObject excelObject = null;
		if (workbook instanceof HSSFWorkbook) {
			excelObject = new HSSFExcelObject((HSSFWorkbook) workbook);
		} else if (workbook instanceof XSSFWorkbook) {
			excelObject = new XSSFExcelObject((XSSFWorkbook) workbook);
		} else if (workbook instanceof SXSSFWorkbook) {
			excelObject = new SXSSFExcelObject((SXSSFWorkbook) workbook);
		}

		return excelObject;
	}

}
