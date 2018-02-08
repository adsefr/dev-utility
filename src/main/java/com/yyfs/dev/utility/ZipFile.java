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

public class ZipFile {

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

	public ZipFile() {
	}

	public static byte[] compress(List<String> inputFileList) {

		// 初期値は1024で、不足の場合（Integer.MAX_VALUE - 8）まで自動拡張
		//ByteArrayOutputStream stream = new ByteArrayOutputStream(1024);

		try (ZipOutputStream zipos = new ZipOutputStream(stream)) {
			byte[] buffer = new byte[1024];
			for (String input : inputFileList) {
				File inputFile = new File(input);
				// ファイル名を取得する
				String name = inputFile.getName();
				ZipEntry entry = new ZipEntry(name);
				zipos.putNextEntry(entry);

				// 方法１：ファイルのサイズが（Integer.MAX_VALUE - 8）未満の場合、利用可
				byte[] inputArray = Files.readAllBytes(inputFile.toPath());
				zipos.write(inputArray);

				// 方法２：ファイルサイズが未定か（Integer.MAX_VALUE - 8）以上の場合、利用可
				// 自動クローズするため、try句を利用する
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
		} catch (IOException e) {
			// 全てのIOExceptionを処理
			e.printStackTrace();
		} catch (Exception e) {
			// 予期せぬ例外の処理
			e.printStackTrace();
		}

		return stream.toByteArray();
	}

}
