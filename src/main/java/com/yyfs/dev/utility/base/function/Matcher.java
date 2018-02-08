package com.yyfs.dev.utility.base.function;

@FunctionalInterface
public interface Matcher<T> {

	public boolean matches(T t);
}
