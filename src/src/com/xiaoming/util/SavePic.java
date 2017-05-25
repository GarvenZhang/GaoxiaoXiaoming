/**
 * 锟斤拷锟节憋拷锟斤拷锟侥硷拷锟斤拷锟斤拷
 */
package com.xiaoming.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SavePic {
	/**
	 * 锟斤拷锟节憋拷锟斤拷锟侥硷拷锟侥凤拷锟斤拷
	 * @param path   锟斤拷锟斤拷锟铰凤拷锟�
	 * @param file   锟斤拷锟斤拷锟斤拷募锟�
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static boolean saveFile(String path, File file)throws Exception {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024 * 2];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
}
