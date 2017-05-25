package com.xiaoming.util.qrcode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

@SuppressWarnings("unchecked")
public class QRCodeUtils {
	
	public static InputStream createQRCodeIntoInputStream(String contents) throws WriterException, IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix matrix = multiFormatWriter.encode(contents, BarcodeFormat.QR_CODE, 400, 400, hints);
		MatrixToImageWriter.writeToStream(matrix, "jpg", out);
		return new ByteArrayInputStream(out.toByteArray());
	}

	public static void createQRCodeIntoFile(String contents,File file) throws WriterException, IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix matrix = multiFormatWriter.encode(contents, BarcodeFormat.QR_CODE, 400, 400, hints);
		MatrixToImageWriter.writeToFile(matrix, "jpg", file);
	}
	
	public static void createQRCodeIntoFile(String contents, String path) throws WriterException, IOException{
		createQRCodeIntoFile(contents, new File(path));
	}
}
