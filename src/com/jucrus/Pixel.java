package com.jucrus;

public class Pixel {

	public static int toInt(double red, double green, double blue, double alpha) {
		int r = (int) (red * 255 + 0.5);
		int g = (int) (green * 255 + 0.5);
		int b = (int) (blue * 255 + 0.5);
		int a = (int) (alpha * 255 + 0.5);
		return ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF));
	}

	public static int toInt(double red, double green, double blue) {
		return Pixel.toInt(red, green, blue, 0);
	}
}

class RGBPixel extends Pixel {
	public int red;
	public int green;
	public int blue;
}
