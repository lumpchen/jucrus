package com.jucrus.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Util {

	public static String getTestFile(String fileName) {
		URL url = Util.class.getResource("/com/jucrus/test/images/" + fileName);
		return url.getPath();
	}
	
	public static void bytes2File(byte[] data, String path) {
		FileOutputStream fos = null;
		ByteArrayInputStream input = new ByteArrayInputStream(data);
		try {
			fos = new FileOutputStream(path);
			
			byte[] buf = new byte[256];
			int count = 0;
			while ((count = input.read(buf)) > 0) {
				fos.write(buf, 0, count);
			}
		} catch (IOException e) {
		} finally {
			try {
				if (input != null) {
					input.close();					
				}
				if (fos != null) {
					fos.close();					
				}
			} catch (IOException e) {
			}
		}
	}
	
	public static byte[] file2Bytes(String path) {
		InputStream input = null;
		ByteArrayOutputStream bos = null;
		try {
			input = new FileInputStream(path);
			bos = new ByteArrayOutputStream();
			byte[] buf = new byte[256];
			int count = 0;
			while ((count = input.read(buf)) > 0) {
				bos.write(buf, 0, count);
			}
			return bos.toByteArray();
		} catch (IOException e) {
		} finally {
			try {
				if (input != null) {
					input.close();					
				}
				if (bos != null) {
					bos.close();					
				}
			} catch (IOException e) {
			}
		}
		return null;
	}
}
