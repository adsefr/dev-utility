package com.yyfs.dev.utility.base.env;

import java.util.Arrays;
import java.util.List;

public class Env {

	public static List<String> getClassPath() {

		String classPath = SysProperty.getJavaClassPath();
		String pathSeparator = SysProperty.getPathSeparator();
		return Arrays.asList(classPath.split(pathSeparator));
	}
}
