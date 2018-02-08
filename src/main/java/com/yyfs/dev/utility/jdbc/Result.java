package com.yyfs.dev.utility.jdbc;

public interface Result {

	public boolean success();

	public boolean failure();

	public String getStatusCode();

	public String getErrorMessage();

	public Throwable getThrowable();
}
