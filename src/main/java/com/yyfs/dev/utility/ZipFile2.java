package com.yyfs.dev.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile2 {

	public static void main(String[] args) {

		List<File> inputFileList = new ArrayList<>();
		inputFileList.add(new File("D:/TEMP/zip/input/Blank.txt"));
		inputFileList.add(new File("D:/TEMP/zip/input/BufferedReader.txt"));
		inputFileList.add(new File("D:/TEMP/zip/input/SeekableByteChannel.txt"));

		byte[] byteArray = compress(inputFileList);

		Path outputFile = Paths.get("D:/TEMP/zip/output.zip");
		try {
			Files.newOutputStream(outputFile);
			Files.write(outputFile, byteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final static String tempDirectory = "D:/TEMP/zip/";

	public ZipFile2() {
	}

	public static byte[] compress(List<File> inputFileList) {

		// zip一時ファイルを作成する
		Path zipTempFile = null;
		//InputStream is = null;
		try {
			zipTempFile = Files.createTempFile(Paths.get(tempDirectory), "tmp", ".zip");
		} catch (IOException e1) {
			// 一時ファイル作成失敗
			e1.printStackTrace();
		}
		// 一時用のzipファイルを作成する
		try (ZipOutputStream zipos = new ZipOutputStream(Files.newOutputStream(zipTempFile))) {
			byte[] buffer = new byte[1024];
			for (File inputFile : inputFileList) {
				// ファイル名を取得する
				String name = inputFile.getName();
				ZipEntry entry = new ZipEntry(name);

				//is = Files.newInputStream(inputFile.toPath());
				// ファイル圧縮開始
				zipos.putNextEntry(entry);

				try (InputStream is = Files.newInputStream(inputFile.toPath())) {
					// ファイル圧縮開始
					int readCount = -1;
					while ((readCount = is.read(buffer)) != -1) {
						zipos.write(buffer, 0, readCount);
					}
				}
				// ファイル圧縮終了
				zipos.closeEntry();
			}
			zipos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] resultArray = null;
		try {
			resultArray = Files.readAllBytes(zipTempFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultArray;
	}

}
