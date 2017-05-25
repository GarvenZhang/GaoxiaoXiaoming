package com.xiaoming.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SaveFile {

	public static void save(File file, String path) throws Exception {
		FileOutputStream fos = new FileOutputStream(new File(path));
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 2];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
	}
}
