package com.yyfs.dev.utility.jdbc.model;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public interface JDBCColumn {

	public Integer getColumnNumber();

	public String getColumnName();

	public boolean isNull();

	public JDBCValue getJDBCValue();

	public <T> T getRawValue();

	public Object getObjectValue();

	public String getStringValue();

	public BigDecimal getNumberValue();

	public Calendar getCalendarValue();

}
