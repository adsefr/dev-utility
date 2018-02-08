package com.yyfs.dev.utility.io;

public interface ScanFilter<T> {

	boolean filter(T target);
}
