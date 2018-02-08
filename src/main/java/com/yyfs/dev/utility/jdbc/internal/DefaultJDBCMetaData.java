package com.yyfs.dev.utility.jdbc.internal;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yyfs.dev.utility.jdbc.JDBCMetaData;
import com.yyfs.dev.utility.jdbc.model.CatalogMeta;
import com.yyfs.dev.utility.jdbc.model.ColumnMeta;
import com.yyfs.dev.utility.jdbc.model.SchemaMeta;
import com.yyfs.dev.utility.jdbc.model.TableMeta;

class DefaultJDBCMetaData implements JDBCMetaData {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJDBCMetaData.class);

	private List<CatalogMeta> catalogNameMetas;

	private List<SchemaMeta> schemaMetas;

	private List<String> tableTypes;

	private DatabaseMetaData databaseMetaData;

	/**
	 * {@inheritDoc}
	 *
	 * @param databaseMetaData
	 */
	DefaultJDBCMetaData(DatabaseMetaData databaseMetaData) {
		this.databaseMetaData = databaseMetaData;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CatalogMeta> getCatalogMetas() throws SQLException {

		if (catalogNameMetas == null) {
			catalogNameMetas = initializeCatalogMetas(databaseMetaData);
		}

		return new ArrayList<>(catalogNameMetas);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SchemaMeta> getSchemaMetas() throws SQLException {

		if (schemaMetas == null) {
			schemaMetas = initializeSchemaMetas(databaseMetaData, null, null);
		}

		return new ArrayList<>(schemaMetas);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SchemaMeta> getSchemaMetas(String schemaPattern) throws SQLException {

		return initializeSchemaMetas(databaseMetaData, null, schemaPattern);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SchemaMeta> getSchemaMetas(String catalogName, String schemaPattern) throws SQLException {

		return initializeSchemaMetas(databaseMetaData, catalogName, schemaPattern);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getTableTypes() throws SQLException {

		if (tableTypes == null) {
			tableTypes = initializeTableTypes(databaseMetaData);
		}

		return new ArrayList<>(tableTypes);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TableMeta> getTableMetas(String catalogName, String schemaName, String tablePattern) throws SQLException {

		return initializeTableMetas(databaseMetaData, catalogName, schemaName, tablePattern, new String[] { "TABLE" });
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasTable(String catalogName, String schemaName, String tablePattern) throws SQLException {

		List<TableMeta> tableMetas = initializeTableMetas(databaseMetaData, catalogName, schemaName, tablePattern, new String[] { "TABLE" });

		return !tableMetas.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ColumnMeta> getColumnMetas(String catalogName, String schemaPattern, String tablePattern) throws SQLException {

		return initializeColumnMetas(databaseMetaData, catalogName, schemaPattern, tablePattern, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ColumnMeta> getColumnMetas(String catalogName, String schemaPattern, String tablePattern, String columnPattern) throws SQLException {

		return initializeColumnMetas(databaseMetaData, catalogName, schemaPattern, tablePattern, columnPattern);

	}

	public boolean hasColumnMeta(String catalogName, String schemaPattern, String tablePattern, String columnPattern) throws SQLException {

		List<ColumnMeta> columnMetas = initializeColumnMetas(databaseMetaData, catalogName, schemaPattern, tablePattern, columnPattern);

		return !columnMetas.isEmpty();
	}

	/**
	 *
	 * @param databaseMetaData
	 * @return
	 * @throws SQLException
	 */
	private List<CatalogMeta> initializeCatalogMetas(DatabaseMetaData databaseMetaData) throws SQLException {

		List<CatalogMeta> catalogNameMetas = new ArrayList<>();

		try (ResultSet resultSetCatalogs = databaseMetaData.getCatalogs()) {

			while (resultSetCatalogs.next()) {
				// 1.TABLE_CAT String =>カタログ名
				String tableCatalog = resultSetCatalogs.getString("TABLE_CAT");
				CatalogMeta catalogNameMeta = new CatalogMeta(tableCatalog);
				catalogNameMetas.add(catalogNameMeta);
			}
		}

		return catalogNameMetas;
	}

	/**
	 *
	 * @param databaseMetaData
	 * @param catalogNameMeta
	 * @throws SQLException
	 */
	private List<SchemaMeta> initializeSchemaMetas(DatabaseMetaData databaseMetaData, String catalogName, String schemaPattern) throws SQLException {

		List<SchemaMeta> schemaMetas = new ArrayList<>();

		try (ResultSet resultSetSchemas = databaseMetaData.getSchemas(catalogName, schemaPattern)) {

			while (resultSetSchemas.next()) {

				// 1.TABLE_SCHEM String =>スキーマ名
				String tableSchema = resultSetSchemas.getString("TABLE_SCHEM");

				// 2.TABLE_CATALOG String =>カタログ名(nullの可能性がある)
				String tableCatalog = resultSetSchemas.getString("TABLE_CATALOG");

				SchemaMeta schemaMeta = new SchemaMeta(tableCatalog, tableSchema);

				schemaMetas.add(schemaMeta);
			}
		}

		return schemaMetas;
	}

	private List<String> initializeTableTypes(DatabaseMetaData databaseMetaData) throws SQLException {

		List<String> tableTypes = new ArrayList<>();

		try (ResultSet resultSetTableTypes = databaseMetaData.getTableTypes()) {
			// TABLE_TYPE String =>表のタイプ。
			// 典型的なタイプは、「TABLE」、「VIEW」、「SYSTEM TABLE」、「GLOBAL TEMPORARY」、「LOCAL TEMPORARY」、「ALIAS」、「SYNONYM」である
			String tableType = resultSetTableTypes.getString("TABLE_TYPE");
			tableTypes.add(tableType);
		}

		return tableTypes;
	}

	private List<TableMeta> initializeTableMetas(DatabaseMetaData databaseMetaData, String catalogName, String schemaPattern, String tablePattern,
			String[] types) throws SQLException {

		List<TableMeta> tableMetas = new ArrayList<>();

		try (ResultSet resultSetTables = databaseMetaData.getTables(catalogName, schemaPattern, tablePattern, null)) {

			while (resultSetTables.next()) {
				/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
				String tableCatalog = resultSetTables.getString("TABLE_CAT");

				/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
				String tableSchema = resultSetTables.getString("TABLE_SCHEM");

				/** TABLE_NAME String => テーブル名 */
				String tableName = resultSetTables.getString("TABLE_NAME");

				/**
				 * TABLE_TYPE String => テーブルのタイプ。典型的なタイプは、「TABLE」、「VIEW」、「SYSTEM
				 * TABLE」、「GLOBAL TEMPORARY」、 「LOCAL
				 * TEMPORARY」、「ALIAS」、「SYNONYM」である
				 */
				String tableType = resultSetTables.getString("TABLE_TYPE");

				/** REMARKS String => テーブルに関する説明 */
				String remarks = resultSetTables.getString("REMARKS");

				/** TYPE_CAT String => タイプのカタログ (null の可能性がある) */
				String tableTypeCatalog = resultSetTables.getString("TYPE_CAT");

				/** TYPE_SCHEM String => タイプのスキーマ (null の可能性がある) */
				String tableTypeSchema = resultSetTables.getString("TYPE_SCHEM");

				/** TYPE_NAME String => タイプ名 (null の可能性がある) */
				String typeName = resultSetTables.getString("TYPE_NAME");

				/**
				 * SELF_REFERENCING_COL_NAME String => タイプ指定されたテーブルの指定された「識別子」列の名前
				 * (null の可能性がある)
				 */
				String selfReferencingColumnName = resultSetTables.getString("SELF_REFERENCING_COL_NAME");

				/**
				 * REF_GENERATION String => SELF_REFERENCING_COL_NAMEの値の作成方法を指定する。
				 * 値は、「SYSTEM」、「USER」、「DERIVED」(nullの可能性がある)
				 */
				String referenceGeneration = resultSetTables.getString("REF_GENERATION");

				TableMeta tableMeta = new TableMeta();
				tableMeta.setCatalogName(tableCatalog);
				tableMeta.setSchemaName(tableSchema);
				tableMeta.setTableName(tableName);
				tableMeta.setTableType(tableType);
				tableMeta.setRemarks(remarks);
				tableMeta.setTableTypeCatalog(tableTypeCatalog);
				tableMeta.setTableTypeSchema(tableTypeSchema);
				tableMeta.setTypeName(typeName);
				tableMeta.setSelfReferencingColumnName(selfReferencingColumnName);
				tableMeta.setReferenceGeneration(referenceGeneration);

				tableMetas.add(tableMeta);
			}
		}

		return tableMetas;
	}

	private List<ColumnMeta> initializeColumnMetas(DatabaseMetaData databaseMetaData, String catalogName, String schemaPattern,
			String tableNamePattern, String columnNamePattern) throws SQLException {

		List<ColumnMeta> columnMetas = new ArrayList<>();

		try (ResultSet resultSetColumnMeta = databaseMetaData.getColumns(catalogName, schemaPattern, tableNamePattern, columnNamePattern)) {

			while (resultSetColumnMeta.next()) {

				/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
				String tableCatalog = resultSetColumnMeta.getString("TABLE_CAT");

				/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
				String tableSchema = resultSetColumnMeta.getString("TABLE_SCHEM");

				/** TABLE_NAME String => テーブル名 */
				String tableName = resultSetColumnMeta.getString("TABLE_NAME");

				/** COLUMN_NAME String => 列名 */
				String columnName = resultSetColumnMeta.getString("COLUMN_NAME");

				/** DATA_TYPE int => java.sql.Types からの SQL の型 */
				int dataType = resultSetColumnMeta.getInt("DATA_TYPE");

				/** TYPE_NAME String => データソース依存の型名。UDT の場合、型名は完全指定 */
				String typeName = resultSetColumnMeta.getString("TYPE_NAME");

				/** COLUMN_SIZE int => 列サイズ */
				int columnSize = resultSetColumnMeta.getInt("COLUMN_SIZE");

				/** BUFFER_LENGTH - 未使用。 */

				/**
				 * DECIMAL_DIGITS int => 小数点以下の桁数。DECIMAL_DIGITS
				 * が適用できないデータ型の場合は、Null が返される。
				 */
				int decimalDits = resultSetColumnMeta.getInt("DECIMAL_DIGITS");

				/** NUM_PREC_RADIX int => 基数 (通常は、10 または 2 のどちらか) */
				int numPrecRadix = resultSetColumnMeta.getInt("NUM_PREC_RADIX");

				/**
				 * NULLABLE int => NULL は許されるか。<br>
				 * columnNoNulls - NULL 値を許さない可能性がある<br>
				 * columnNullable - 必ず NULL 値を許す<br>
				 * columnNullableUnknown - NULL 値を許すかどうかは不明<br>
				 */
				int nullable = resultSetColumnMeta.getInt("NULLABLE");

				/** REMARKS String => 列を記述するコメント (null の可能性がある) */
				String remarks = resultSetColumnMeta.getString("REMARKS");

				/**
				 * COLUMN_DEF String => 列のデフォルト値。単一引用符で囲まれた値は、文字列として解釈されるべき (null
				 * の可能性がある)
				 */
				String columnDefaultValue = resultSetColumnMeta.getString("COLUMN_DEF");

				/** SQL_DATA_TYPE int => 未使用 */

				/** SQL_DATETIME_SUB int => 未使用 */

				/** CHAR_OCTET_LENGTH int => char の型については列の最大バイト数 */
				int charOctetLength = resultSetColumnMeta.getInt("CHAR_OCTET_LENGTH");

				/** ORDINAL_POSITION int => テーブル中の列のインデックス (1 から始まる) */
				int ordinalPosition = resultSetColumnMeta.getInt("ORDINAL_POSITION");

				/**
				 * IS_NULLABLE String => 列で NULL 値を許可するかどうかの判断に ISO 規則が使用される。<br>
				 * YES --- 列が NULL を許可する場合<br>
				 * NO --- 列が NULL を許可しない場合<br>
				 * 空の文字列 --- 列が NULL 値を許可するかどうか不明である場合<br>
				 */
				String isNullable = resultSetColumnMeta.getString("IS_NULLABLE");

				/**
				 * SCOPE_CATALOG String => 参照属性のスコープであるテーブルのカタログ (DATA_TYPE が REF
				 * でない場合は null)
				 */
				String scopeCatalog = resultSetColumnMeta.getString("SCOPE_CATALOG");

				/**
				 * SCOPE_SCHEMA String => 参照属性のスコープであるテーブルのスキーマ (DATA_TYPE が REF
				 * でない場合は null)
				 */
				String scopeSchema = resultSetColumnMeta.getString("SCOPE_SCHEMA");

				/**
				 * SCOPE_TABLE String => 参照属性のスコープであるテーブル名 (DATA_TYPE が REF でない場合は
				 * null)
				 */
				String scopeTable = resultSetColumnMeta.getString("SCOPE_TABLE");

				/**
				 * SOURCE_DATA_TYPE short => 個別の型またはユーザー生成 Ref
				 * 型のソースの型、java.sql.Types の SQL 型 (DATA_TYPE が DISTINCT
				 * またはユーザー生成 REF でない場合は null)
				 */
				String sourceDataType = resultSetColumnMeta.getString("SOURCE_DATA_TYPE");

				/**
				 * IS_AUTOINCREMENT String => この列が自動インクリメントされるかどうかを示す<br>
				 * YES --- 列が自動インクリメントされる場合<br>
				 * NO --- 列が自動インクリメントされない場合<br>
				 * 空の文字列 --- 列が自動インクリメントされるかどうかが判断できない場合<br>
				 */
				String isAutoIncrement = resultSetColumnMeta.getString("IS_AUTOINCREMENT");

				/**
				 * IS_GENERATEDCOLUMN String => これが生成された列かどうかを示す<br>
				 * YES --- これが生成された列である場合<br>
				 * NO --- これが生成された列でない場合<br>
				 * 空の文字列 --- これが生成された列かどうかが判断できない場合<br>
				 */
				String isGeneratedColumn = resultSetColumnMeta.getString("IS_GENERATEDCOLUMN");

				ColumnMeta columnMeta = new ColumnMeta();
				columnMeta.setCatalogName(tableCatalog);
				columnMeta.setSchemaName(tableSchema);
				columnMeta.setTableName(tableName);
				columnMeta.setColumnName(columnName);
				columnMeta.setDataType(dataType);
				columnMeta.setTypeName(typeName);
				columnMeta.setColumnSize(columnSize);
				columnMeta.setDecimalDits(decimalDits);
				columnMeta.setNumPrecRadix(numPrecRadix);
				columnMeta.setNullable(nullable);
				columnMeta.setRemarks(remarks);
				columnMeta.setColumnDefaultValue(columnDefaultValue);
				columnMeta.setCharOctetLength(charOctetLength);
				columnMeta.setOrdinalPosition(ordinalPosition);
				columnMeta.setIsNullable(isNullable);
				columnMeta.setScopeCatalog(scopeCatalog);
				columnMeta.setScopeSchema(scopeSchema);
				columnMeta.setScopeTable(scopeTable);
				columnMeta.setSourceDataType(sourceDataType);
				columnMeta.setIsAutoIncrement(isAutoIncrement);
				columnMeta.setIsGeneratedColumn(isGeneratedColumn);

				columnMetas.add(columnMeta);
			}
		}

		return columnMetas;
	}

	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////

	// private void inititalizeDatabaseMeta(DatabaseMetaData databaseMetaData) throws SQLException {
	//
	// dataBaseMeta.setCatalogSeparator(databaseMetaData.getCatalogSeparator());
	// dataBaseMeta.setDatabaseMajorVersion(databaseMetaData.getDatabaseMajorVersion());
	// dataBaseMeta.setDatabaseMinorVersion(databaseMetaData.getDatabaseMajorVersion());
	// dataBaseMeta.setDatabaseProductName(databaseMetaData.getDatabaseProductName());
	// dataBaseMeta.setDatabaseProductVersion(databaseMetaData.getDatabaseProductVersion());
	// dataBaseMeta.setDriverMajorVersion(databaseMetaData.getDriverMajorVersion());
	// dataBaseMeta.setDriverMinorVersion(databaseMetaData.getDriverMinorVersion());
	// dataBaseMeta.setDriverName(databaseMetaData.getDriverName());
	// dataBaseMeta.setDriverVersion(databaseMetaData.getDriverVersion());
	// dataBaseMeta.setJdbcMajorVersion(databaseMetaData.getJDBCMajorVersion());
	// dataBaseMeta.setJdbcMinorVersion(databaseMetaData.getJDBCMinorVersion());
	// dataBaseMeta.setUrl(databaseMetaData.getURL());
	// dataBaseMeta.setSearchStringEscape(databaseMetaData.getSearchStringEscape());
	// }
	//
	// private void initializePrimaryKeys(DatabaseMetaData databaseMetaData, TableMeta tableMeta) throws SQLException {
	//
	// String catalogName = tableMeta.getCatalogName();
	// String schemaName = tableMeta.getSchemaName();
	// String tableName = tableMeta.getTableName();
	//
	// ResultSet resultSetPrimaryKeys = databaseMetaData.getPrimaryKeys(catalogName, schemaName, tableName);
	//
	// QueryExecutor primaryKeysQueryExecutor = JDBCFactory.newJDBCQueryExecutor(resultSetPrimaryKeys);
	//
	// while (primaryKeysQueryExecutor.hasNext()) {
	// /** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
	// // JDBCValue tableCatalog =
	// // columnQueryExecutor.getJDBCValue("TABLE_CAT");
	//
	// /** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
	// // JDBCValue tableSchema =
	// // columnQueryExecutor.getJDBCValue("TABLE_SCHEM");
	//
	// /** TABLE_NAME String => テーブル名 */
	// // JDBCValue table = columnQueryExecutor.getJDBCValue("TABLE_NAME");
	//
	// /** 4.COLUMN_NAME String =>列名 */
	// String columnName = primaryKeysQueryExecutor.getValue("COLUMN_NAME");
	//
	// /** 5.KEY_SEQ short =>主キー内の連番(値1は主キーの最初の列、値2は主キーの2番目の列を表す)。 */
	// JDBCValue keySequence = primaryKeysQueryExecutor.getJDBCValue("KEY_SEQ");
	//
	// /** 6.PK_NAME String =>主キー名(nullの可能性がある) */
	// JDBCValue primaryKeyName = primaryKeysQueryExecutor.getJDBCValue("PK_NAME");
	//
	// tableMeta.setPrimaryKeyName(primaryKeyName);
	//
	// ColumnMeta columnMeta = tableMeta.getColumnMeta(columnName);
	// if (columnMeta != null) {
	// columnMeta.setPrimaryKey(JDBCValue.newJDBCValue(true));
	// columnMeta.setKeySequence(keySequence);
	// }
	// }
	// }

}
