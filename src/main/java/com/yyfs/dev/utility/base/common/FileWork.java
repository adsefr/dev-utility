package com.yyfs.dev.utility.base.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;

public class FileWork {

	public static void main(String[] args) throws IOException {

		String str = "1234_5678_90_1234.txt";
		StringBuilder sBuilder = new StringBuilder(str);
		System.out.println(sBuilder.delete(5, 9).toString());
		// ホスト名取得
		String hostName = new Properties().getProperty(HOST_NAME_KEY);
		System.out.println(hostName);
		hostName = System.getenv(HOST_NAME_KEY);
		System.out.println(hostName);
		hostName = System.getProperty(HOST_NAME_KEY);
		System.out.println(hostName);

		try {
			String hostName2 = InetAddress.getLocalHost().getHostName();
			System.out.println(hostName2);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		List<byte[]> inputArrayList = new ArrayList<>();
		for (int i = 48; i <= 58; i++) {
			byte[] byteArray = new byte[] { (byte) i };
			inputArrayList.add(byteArray);
		}
		List<String> resultList = createTmpFile(inputArrayList);
		// 1. inputArrayList.size() == resultList.size()
		// 2.
		for (int index = 0, size = inputArrayList.size(); index < size; index++) {
			byte[] readArray = new byte[inputArrayList.get(index).length];
			try {
				InputStream is = new FileInputStream(resultList.get(index));
				is.read(readArray, 0, readArray.length);
				int read = is.read();
				//ファイルの最後まで読み込んだかをチェックする
				Assert.assertEquals(-1, read);
				if (read == -1) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < readArray.length; i++) {
				byte a = inputArrayList.get(index)[i];
				byte b = readArray[i];
				Assert.assertEquals(a, b);
			}
		}
	}

	private final static String PATH_STR = "f:/test";

	private final static String PREFIX = "tmp_";

	private final static String SUBFFIX = ".dat";

	private final static String HOST_NAME_KEY = "HOST_NAME";

	public static List<String> createTmpFile(List<byte[]> intputArrayList) throws IOException {

		List<String> resultList = new ArrayList<>();
		if (intputArrayList == null || intputArrayList.isEmpty()) {
			return resultList;

		}
		// ディレクトリを取得
		String directory = PATH_STR;

		// ディレクトリを作成
		File path = new File(directory);
		if (!path.exists()) {
			path.mkdirs();
		}
		for (int i = 0; i < intputArrayList.size(); i++) {

			// ファイル名を作成
			File outputFile = null;
				outputFile = File.createTempFile((PREFIX + System.currentTimeMillis()), SUBFFIX, path);

			try (OutputStream os = new FileOutputStream(outputFile)) {
				byte[] byteArray = intputArrayList.get(i);
				os.write(byteArray, 0, byteArray.length);
				os.flush();
				resultList.add(outputFile.getAbsolutePath());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return resultList;
	}

}
