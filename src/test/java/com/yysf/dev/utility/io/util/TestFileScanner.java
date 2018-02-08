package com.yysf.dev.utility.io.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import com.yyfs.dev.utility.io.FileScanner;

public class TestFileScanner {

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalParams() throws IOException {

		Path path = Paths.get("src/test/java/com/rms/dev/tool/TestDirectoryScanner.java");
		new FileScanner().scan(path);
	}

	@Test
	public void scan() throws IOException {

		Path path = Paths.get(".");
		FileScanner scanner = new FileScanner();
		scanner.scan(path);
		List<Path> list = scanner.getScanResult();
		for (Path file : list) {
			System.out.println(file);
		}
	}

	@Test
	public void scanFile() throws IOException {

		Path path = Paths.get(".");
		FileScanner scanner = new FileScanner();
		scanner.scanFile(path);
		List<Path> list = scanner.getScanResult();
		for (Path file : list) {
			System.out.println(file.toString());
		}
	}

	@Test
	public void scanDirectory() throws IOException {

		Path path = Paths.get(".");
		FileScanner scanner = new FileScanner();
		scanner.scanDirectory(path);
		List<Path> list = scanner.getScanResult();
		for (Path file : list) {
			System.out.println(file);
		}
	}
}
