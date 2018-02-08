package com.yysf.dev.utility.io.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.yyfs.dev.utility.io.JarFileScanner;

public class TestJarScanner {

	@Test
	public void testPrint() {

		Path jarFile = Paths.get("D:/SoftWare/java/jdk1.8.0_92/lib/javafx-mx.jar");
		JarFileScanner jarScanner = new JarFileScanner(jarFile);
		try {
			jarScanner.scan();
		} catch (IOException e) {
			e.printStackTrace();
		}
		jarScanner.print();
	}
}
