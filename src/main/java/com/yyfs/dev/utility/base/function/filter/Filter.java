package com.yyfs.dev.utility.base.function.filter;

@FunctionalInterface
public interface Filter<T> {

	public boolean accept(T t);
}
