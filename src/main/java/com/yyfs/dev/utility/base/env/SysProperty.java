package com.yyfs.dev.utility.base.env;

public class SysProperty {

	public static String getFileSeparator() {

		return System.getProperty("file.separator");
	}

	public static String getJavaClassPath() {

		return System.getProperty("java.class.path");
	}

	public static String getJavaHome() {

		return System.getProperty("java.home");
	}

	public static String getJavaVendor() {

		return System.getProperty("java.vendor");
	}

	public static String getJavaVendorUrl() {

		return System.getProperty("java.vendor.url");
	}

	public static String getJavaVersion() {

		return System.getProperty("java.version");
	}

	public static String getLineSeparator() {

		return System.getProperty("line.separator");
	}

	public static String getOsArch() {

		return System.getProperty("os.arch");
	}

	public static String getOsName() {

		return System.getProperty("os.name");
	}

	public static String getOsVersion() {

		return System.getProperty("os.version");
	}

	public static String getPathSeparator() {

		return System.getProperty("path.separator");
	}

	public static String getUserDir() {

		return System.getProperty("user.dir");
	}

	public static String getUseHome() {

		return System.getProperty("user.home");
	}

	public static String getUseName() {

		return System.getProperty("user.name");
	}
}
