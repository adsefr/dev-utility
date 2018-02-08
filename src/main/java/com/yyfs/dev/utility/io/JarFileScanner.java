package com.yyfs.dev.utility.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import com.yyfs.dev.utility.base.validate.Assertion;

public class JarFileScanner {

	private Path jarFile = null;

	private List<String> scanResult = null;

	private Manifest manifest = null;

	public JarFileScanner(Path jarFile) {
		Assertion.assertNotNull("jarFile", jarFile);
		this.jarFile = jarFile;
	}

	public void scan() throws IOException {

		scanResult = new ArrayList<>();

		try (JarInputStream jarStream = new JarInputStream(Files.newInputStream(jarFile, StandardOpenOption.READ))) {

			manifest = jarStream.getManifest();

			JarEntry jarEntry = null;
			while ((jarEntry = jarStream.getNextJarEntry()) != null) {
				if (!jarEntry.isDirectory()) {
					String name = jarEntry.getName();
					scanResult.add(name);
				}
			}
		}
	}

	/**
	 * @return jarFile
	 */
	public Path getJarFile() {

		return jarFile;
	}

	/**
	 * @return scanResult
	 */
	public List<String> getScanResult() {

		return scanResult;
	}

	/**
	 * @return manifest
	 */
	public Manifest getManifest() {

		return manifest;
	}

	public void print() {

		if (scanResult == null) {
			return;
		}

		for (String entryPath : scanResult) {
			System.out.println(entryPath);
		}
	}
}
