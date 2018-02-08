package com.yyfs.dev.utility.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.yyfs.dev.utility.jdbc.model.CatalogMeta;
import com.yyfs.dev.utility.jdbc.model.ColumnMeta;
import com.yyfs.dev.utility.jdbc.model.SchemaMeta;
import com.yyfs.dev.utility.jdbc.model.TableMeta;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface JDBCMetaData {

	/**
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<CatalogMeta> getCatalogMetas() throws SQLException;

	/**
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<SchemaMeta> getSchemaMetas() throws SQLException;

	/**
	 *
	 * @param schemaPattern
	 * @return
	 * @throws SQLException
	 */
	public List<SchemaMeta> getSchemaMetas(String schemaPattern) throws SQLException;

	/**
	 *
	 * @param catalogName
	 * @param schemaPattern
	 * @return
	 * @throws SQLException
	 */
	public List<SchemaMeta> getSchemaMetas(String catalogName, String schemaPattern) throws SQLException;

	/**
	 *
	 * @return
	 */
	public List<String> getTableTypes() throws SQLException;

	/**
	 *
	 * @param catalogName
	 * @param schemaPattern
	 * @param tablePattern
	 * @return
	 * @throws SQLException
	 */
	public List<TableMeta> getTableMetas(String catalogName, String schemaPattern, String tablePattern) throws SQLException;

	/**
	 *
	 * @param catalogName
	 * @param schemaPattern
	 * @param tablePattern
	 * @return
	 * @throws SQLException
	 */
	public boolean hasTable(String catalogName, String schemaPattern, String tablePattern) throws SQLException;

	/**
	 *
	 * @param catalogName
	 * @param schemaPattern
	 * @param tablePattern
	 * @return
	 */
	public List<ColumnMeta> getColumnMetas(String catalogName, String schemaPattern, String tablePattern) throws SQLException;

	/**
	 *
	 * @param catalogName
	 * @param schemaPattern
	 * @param tablePattern
	 * @return
	 */
	public List<ColumnMeta> getColumnMetas(String catalogName, String schemaPattern, String tablePattern, String columnPattern) throws SQLException;

}
