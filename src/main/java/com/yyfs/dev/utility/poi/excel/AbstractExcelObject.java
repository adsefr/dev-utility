package com.yyfs.dev.utility.poi.excel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yyfs.dev.utility.io.IOUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
abstract class AbstractExcelObject implements ExcelObject {

	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractExcelObject.class);

	private final static int MIN_ROW_NUMBER = 1;

	private final static int MIN_COLUMN_NUMBER = 1;

	protected Workbook workbook;

	protected SpreadsheetVersion version;

	protected File loadedFile;

	public AbstractExcelObject(Workbook workbook, SpreadsheetVersion version) {
		this.workbook = workbook;
		this.version = version;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMinRowNumber() {

		return MIN_ROW_NUMBER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMinColumnNumer() {

		return MIN_COLUMN_NUMBER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMaxRowNumber() {

		return version.getMaxRows();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMaxColumnNumer() {

		return version.getMaxColumns();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMinRowIndex() {

		return getMinRowNumber() - 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMinColumnIndex() {

		return getMinColumnNumer() - 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMaxRowIndex() {

		return getMaxRowNumber() - 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMaxColumnIndex() {

		return getMaxColumnNumer() - 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isValidRowNumber(int rowNumber) {

		return (getMinRowNumber() <= rowNumber && rowNumber <= getMaxRowNumber());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isValidColumnNumber(int columnNumber) {

		return (getMinColumnNumer() <= columnNumber && columnNumber <= getMaxColumnNumer());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isValidRowIndex(int rowIndex) {

		return isValidRowNumber(rowIndex + 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isValidColumnIndex(int columnIndex) {

		return isValidColumnNumber(columnIndex + 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getCellMaxTextLength() {

		return version.getMaxTextLength();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isValidSheetName(String sheetName) {

		try {
			WorkbookUtil.validateSheetName(sheetName);
			return true;
		} catch (IllegalArgumentException e) {
			LOGGER.debug(e.toString());
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasSheet(String sheetName) {

		if (!isValidSheetName(sheetName)) {
			return false;
		}

		Sheet sheet = workbook.getSheet(sheetName);

		return (sheet != null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasSheet(int sheetNumber) {

		return 0 < sheetNumber && sheetNumber <= getNumberOfSheets();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getNumberOfSheets() {

		return workbook.getNumberOfSheets();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getSheetName(int sheetIndex) {

		return workbook.getSheetName(sheetIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getSheetIndex(String sheetName) {

		return workbook.getSheetIndex(sheetName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getActiveSheetName() {

		int sheetIndex = workbook.getActiveSheetIndex();
		String sheetName = workbook.getSheetName(sheetIndex);
		return sheetName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getActiveSheetIndex() {

		return workbook.getActiveSheetIndex();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setActiveSheet(String sheetName) {

		int sheetIndex = getSheetIndex(sheetName);
		setActiveSheet(sheetIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setActiveSheet(int sheetIndex) {

		for (Iterator<Sheet> iterator = workbook.sheetIterator(); iterator.hasNext();) {
			iterator.next().setSelected(false);
		}

		workbook.getSheetAt(sheetIndex).setSelected(true);
		workbook.setActiveSheet(sheetIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject createSheet() {

		Sheet sheet = workbook.createSheet();

		return createSheetObject(sheet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject createSheet(int sheetIndex) {

		Sheet sheet = workbook.createSheet();
		workbook.setSheetOrder(sheet.getSheetName(), sheetIndex);

		return createSheetObject(sheet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject createSheet(String sheetName) {

		Sheet sheet = workbook.createSheet(sheetName);

		return createSheetObject(sheet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject createSheet(String sheetName, int sheetIndex) {

		Sheet sheet = workbook.createSheet(sheetName);
		workbook.setSheetOrder(sheetName, sheetIndex);

		return createSheetObject(sheet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeSheet(String sheetName) {

		int sheetIndex = workbook.getSheetIndex(sheetName);
		workbook.removeSheetAt(sheetIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeSheet(int sheetIndex) {

		workbook.removeSheetAt(sheetIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public File getLoadedFile() {

		return loadedFile;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void write(OutputStream os) throws IOException {

		try {
			workbook.write(os);
			os.flush();
		} finally {
			IOUtil.close(os);
		}
	}

	@Override
	public void close() {

		IOUtil.close(workbook);
	}

}
