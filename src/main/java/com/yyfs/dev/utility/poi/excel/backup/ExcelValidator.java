package com.yyfs.dev.utility.poi.excel.backup;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/12/19
 */
public interface ExcelValidator {

	public boolean isExistSheetNames(String... sheetNames);

	public boolean isValidSheetIndex(int sheetIndex);

	public boolean isValidRowIndex(int rowIndex);

	public boolean isValidColumnIndex(int columnIndex);

}
