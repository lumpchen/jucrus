package com.jucrus;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Canvas {

	private int width;
	private int height;
	private BufferedImage image;

	public Canvas(int width, int height, int type) {
		this.width = width;
		this.height = height;
		this.image = new BufferedImage(width, height, type);
	}

	public void setPixel(int x, int y, int rgb) {
		if (x >= this.width || y >= this.height) {
			throw new IllegalArgumentException();
		}
		this.image.setRGB(x, y, rgb);
	}

	public void setRowPixels(int row, int[] rgb) {
		for (int i = 0; i < this.width; i++) {
			this.image.setRGB(i, row, rgb[i]);
		}
	}

	public void setImpositionTemplate(ImpositionTemplate impositionTemplate) {

	}

	public void tilt_shift() {
		double arglist[] = { 0, 0,
				// RGB black
				0, 0, 0,
				// The y coordinate is filled in later
				0, -1,
				// RGB white
				1, 1, 1 };
		// arguments for MagickFunctionImage
		double funclist[] = { 4, -4, 1 };
	    arglist[6] = this.height - 1;
	    
	    
	}

	public void paint(Graphics2D g2) {
		g2.drawImage(this.image, 0, 0, null);
	}
}
