package com.jucrus;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImageCanvas extends JPanel {

	private static final long serialVersionUID = -6809019436082398263L;

	private MagickWand wand;
	private int imageWidth;
	private int imageHeight;
	private BufferedImage image;

	public ImageCanvas(MagickWand wand) {
		super();
		this.wand = wand;
	}

	public void openImage(String path) {
		if (this.wand.readImage(path)) {
			this.updateImage();
		} else {
			// failed message
		}
	}

	public void closeImage() {
		this.wand.destroy();
		this.image = null;
		this.repaint();
	}

	public void drawText(String text) {
		if (this.wand.drawText(text)) {
			this.updateImage();
		} else {
			// failed message
		}
	}
	
	private void updateImage() {
		this.image = this.wand.peerImage();
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int width = this.getWidth();
		int height = this.getHeight();

		if (this.image != null) {
			int iw = this.image.getWidth();
			int ih = this.image.getHeight();
			g2.drawImage(this.image, 0, 0, null);
		}
	}
}
