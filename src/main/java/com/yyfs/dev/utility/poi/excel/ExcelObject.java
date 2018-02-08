package com.yyfs.dev.utility.poi.excel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public interface ExcelObject {

	/**
	 * シートで使用可能な最小行数を返す。<br>
	 *
	 * @return 最小行数
	 */
	public int getMinRowNumber();

	/**
	 * シートで使用可能な最小列数を返す。<br>
	 *
	 * @return 最小列数
	 */
	public int getMinColumnNumer();

	/**
	 * シートで使用可能な最大行数を返す。<br>
	 *
	 * @return 最大行数
	 */
	public int getMaxRowNumber();

	/**
	 * シートで使用可能な最大列数を返す。<br>
	 *
	 * @return 最大列数
	 */
	public int getMaxColumnNumer();

	/**
	 * 最小行位置を取得する。<br>
	 *
	 * @return 最小行位置
	 */
	public int getMinRowIndex();

	/**
	 * 最小列位置を取得する。<br>
	 *
	 * @return 最小列位置
	 */
	public int getMinColumnIndex();

	/**
	 * 最大行位置を取得する。<br>
	 *
	 * @return 最大行位置
	 */
	public int getMaxRowIndex();

	/**
	 * 最大列位置を取得する。<br>
	 *
	 * @return 最大列位置
	 */
	public int getMaxColumnIndex();

	/**
	 * 行番号の有効性をチェックする。<br>
	 *
	 * @param rowNumber
	 *            行番号
	 * @return true：有効の場合<br>
	 *         false：無効の場合<br>
	 */
	public boolean isValidRowNumber(int rowNumber);

	/**
	 * 列番号の有効性をチェックする。<br>
	 *
	 * @param columnNumber
	 *            列番号
	 * @return true：有効の場合<br>
	 *         false：無効の場合<br>
	 */
	public boolean isValidColumnNumber(int columnNumber);

	/**
	 * 行位置の有効性をチェックする。<br>
	 *
	 * @param rowIndex
	 *            行位置
	 * @return true：有効の場合<br>
	 *         false：無効の場合<br>
	 */
	public boolean isValidRowIndex(int rowIndex);

	/**
	 * 列位置の有効性をチェックする。<br>
	 *
	 * @param columnIndex
	 *            列位置
	 * @return true：有効の場合<br>
	 *         false：無効の場合<br>
	 */
	public boolean isValidColumnIndex(int columnIndex);

	/**
	 * Cellに書き込む可能な最大文字数を返す。<br>
	 *
	 * @return 最大文字数
	 */
	public int getCellMaxTextLength();

	/**
	 * シート名の有効性をチェックする。<br>
	 *
	 * @param sheetName
	 *            シート名
	 * @return true：有効の場合<br>
	 *         false：無効の場合<br>
	 */
	public boolean isValidSheetName(String sheetName);

	/**
	 * シート名が存在するかチェックする。<br>
	 *
	 *
	 * @param sheetName
	 *            チェック対象シート名
	 * @return シート名が存在する場合はtrueを返す。 <br>
	 *         シート名が存在しない場合はfalseを返す。<br>
	 */
	public boolean hasSheet(String sheetName);

	/**
	 * シート位置が存在するかチェックする。<br>
	 *
	 * @param sheetNumber
	 *            チェック対象シート位置
	 * @return シート位置が存在する場合はtrueを返す。 <br>
	 *         シート位置が存在しない場合はfalseを返す。<br>
	 */
	public boolean hasSheet(int sheetNumber);

	/**
	 * シート数を返す。
	 *
	 * @return シート数
	 */
	public int getNumberOfSheets();

	/**
	 * 指定位置のシート名を取得する。<br>
	 * シート位置が無効の場合、nullを返す。<br>
	 *
	 * @param sheetIndex
	 *            シート位置
	 * @return シート名
	 */
	public String getSheetName(int sheetIndex);

	/**
	 * 指定シート名のシート位置を取得する。<br>
	 * シート名が存在しない場合、-1を返す。<br>
	 *
	 * @param sheetName
	 *            シート名
	 * @return シート位置
	 */
	public int getSheetIndex(String sheetName);

	/**
	 * 選択されているシート名を取得する。<br>
	 *
	 * @return シート名
	 */
	public String getActiveSheetName();

	/**
	 * 選択されているシート位置を取得する。<br>
	 *
	 * @return シート名
	 */
	public int getActiveSheetIndex();

	/**
	 * 指定シートを選択されるシートに設定する。<br>
	 *
	 * @param sheetName
	 *            シート名
	 */
	public void setActiveSheet(String sheetName);

	/**
	 * 選択されるシートを設定する。<br>
	 *
	 * @param sheetIndex
	 *            シート位置
	 */
	public void setActiveSheet(int sheetIndex);

	/**
	 * シートを作成する。<br>
	 *
	 * @return シート作成が成功した場合はtrueを返す。<br>
	 *         シート作成が失敗した場合はfalseを返す。<br>
	 */
	public SheetObject createSheet();

	/**
	 * 指定位置にシートを作成する。<br>
	 *
	 * @param sheetIndex
	 *            作成位置
	 * @return シート作成が成功した場合はtrueを返す。<br>
	 *         シート作成が失敗した場合はfalseを返す。<br>
	 */
	public SheetObject createSheet(int sheetIndex);

	/**
	 * 指定のシート名でシートを作成する。<br>
	 *
	 * @param sheetName
	 *            シート名
	 * @return シート作成が成功した場合はtrueを返す。<br>
	 *         シート作成が失敗した場合はfalseを返す。<br>
	 */
	public SheetObject createSheet(String sheetName);

	/**
	 * 指定位置にシートを作成する。<br>
	 *
	 * @param sheetName
	 *            シート名
	 * @param sheetIndex
	 *            作成位置
	 * @return シート作成が成功した場合はtrueを返す。<br>
	 *         シート作成が失敗した場合はfalseを返す。<br>
	 */
	public SheetObject createSheet(String sheetName, int sheetIndex);

	/**
	 * 指定シート名のシートを削除する。<br>
	 *
	 * @param sheetName
	 *            シート名
	 */
	public void removeSheet(String sheetName);

	/**
	 * 指定位置のシートを削除する。<br>
	 *
	 * @param sheetIndex
	 *            シート位置index
	 */
	public void removeSheet(int sheetIndex);

	/**
	 * SheetObjectを作成する。
	 *
	 * @param sheet
	 *            シート
	 * @return SheetObject
	 */
	public SheetObject createSheetObject(Sheet sheet);

	/**
	 * 指定シート位置のSheetObjectを取得する。
	 *
	 * @param sheetIndex
	 *            シート位置
	 * @return SheetObject
	 */
	public SheetObject getSheetObject(int sheetIndex);

	/**
	 * 指定シート名のSheetObjectを取得する。
	 *
	 * @param sheetName
	 *            シート名
	 * @return SheetObject
	 */
	public SheetObject getSheetObject(String sheetName);

	/**
	 * ロードしたファイルを取得する。<BR>
	 * 新規の場合、nullを返す。<BR>
	 *
	 * @return File
	 */
	public File getLoadedFile();

	/**
	 * Excelファイルを書き込む<br>
	 *
	 * @param outputStream
	 *            書き込む対象
	 * @throws IOException
	 */
	public void write(OutputStream outputStream) throws IOException;

	public void close();
}
