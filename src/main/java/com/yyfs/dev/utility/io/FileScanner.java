package com.yyfs.dev.utility.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yyfs.dev.utility.base.validate.Assertion;
import com.yyfs.dev.utility.io.constant.ScanType;

/**
 *
 * @author rimeisei
 * @since 2017/12/29
 */
public class FileScanner {

	private List<Path> scanResultList = new ArrayList<>();

	/**
	 * default constructor
	 */
	public FileScanner() {

	}

	/**
	 * @return scanResultList
	 */
	public List<Path> getScanResult() {

		return scanResultList;
	}

	/**
	 *
	 * @param path
	 * @param scanFilters
	 * @throws IOException
	 */
	public void scan(Path path, ScanFilter<Path>... scanFilters) throws IOException {

		scan(path, ScanType.BOTH, scanFilters);
	}

	/**
	 *
	 * @param path
	 * @param scanFilters
	 * @throws IOException
	 */
	public void scanFile(Path path, ScanFilter<Path>... scanFilters) throws IOException {

		scan(path, ScanType.FILE, scanFilters);
	}

	/**
	 *
	 * @param path
	 * @param scanFilters
	 * @throws IOException
	 */
	public void scanDirectory(Path path, ScanFilter<Path>... scanFilters) throws IOException {

		scan(path, ScanType.DIRECTORY, scanFilters);
	}

	/**
	 *
	 * @param path
	 * @param scanType
	 * @param scanFilters
	 * @throws IOException
	 */
	private void scan(Path path, ScanType scanType, ScanFilter<Path>... scanFilters) throws IOException {

		Assertion.assertNotNull("path", path);
		Assertion.assertNotNull("scanType", scanType);

		if (!Files.isDirectory(path)) {
			throw new IllegalArgumentException("The path is not directory!!" + path.toString());
		}

		scanResultList.clear();

		execute(path, scanType, scanFilters);
	}

	private void execute(Path path, ScanType scanType, ScanFilter<Path>... scanFilters) throws IOException {

		DirectoryStream<Path> stream = Files.newDirectoryStream(path);

		for (Iterator<Path> iterator = stream.iterator(); iterator.hasNext();) {

			Path target = iterator.next();

			if (ScanType.DIRECTORY == scanType && !Files.isDirectory(target)) {
				continue;
			}

			if (ScanType.FILE == scanType && Files.isDirectory(target)) {
				execute(target, scanType, scanFilters);
				continue;
			}

			boolean result = true;

			if (scanFilters.length > 0) {
				result = result && filter(target, scanFilters);

			}

			if (result) {
				scanResultList.add(target);
			}
		}
	}

	/**
	 *
	 * @param target
	 * @param scanFilters
	 * @return
	 */
	private boolean filter(Path target, ScanFilter<Path>... scanFilters) {

		boolean result = true;
		for (ScanFilter<Path> scanFilter : scanFilters) {
			result = scanFilter.filter(target);
			if (!result) {
				return result;
			}
		}

		return result;
	}

}
