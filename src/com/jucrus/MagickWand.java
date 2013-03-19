package com.jucrus;

import java.awt.image.BufferedImage;

import com.jucrus.magick.Magick;
import com.jucrus.magick.Magick.ChannelType;
import com.jucrus.magick.Magick.MagickFunction;
import com.jucrus.magick.NativeIM;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public class MagickWand {
	static NativeIM magick = NativeIM.instance;
	
	private Pointer wand;
	private int width;
	private int height;
	private int alpha; 
	private BufferedImage peerImage;
	
	public MagickWand() {
		magick.MagickWandGenesis();
	}
	
	public boolean readImage(String path) {
		this.wand = magick.NewMagickWand();
		int status = magick.MagickReadImage(wand, path);
		if (Magick.MagickBooleanType.MagickFalse == status) {
			return false;
		}
		
		this.width = magick.MagickGetImageWidth(wand);
		this.height = magick.MagickGetImageHeight(wand);
		this.alpha = magick.MagickGetImageAlphaChannel(wand);
		
		if (alpha > 0) {
			this.peerImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		} else {
			this.peerImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		}
		
		Pointer pixelIterator = magick.NewPixelIterator(wand);
		IntByReference pWidth = new IntByReference();

		for (int i = 0; i < height; i++) {
			Pointer[] pixels = magick.PixelGetNextIteratorRow(pixelIterator, pWidth);
			int[] rowPixels = getRowPixels(pixels, width, alpha);
			for (int j = 0; j < width; j++) {
				this.peerImage.setRGB(j, i, rowPixels[j]);				
			}
		}
		return true;
	}
	
	public BufferedImage peerImage() {
		return this.peerImage;
	}
	
	public void destroy() {
		magick.DestroyMagickWand(wand);
		magick.MagickWandTerminus();		
	}
	
	private void update() {
		int w = magick.MagickGetImageWidth(wand);
		int h = magick.MagickGetImageHeight(wand);
		int a = magick.MagickGetImageAlphaChannel(wand);

		if (w != this.width || h != this.height || a != this.alpha) {
			this.width = w;
			this.height = h;
			this.alpha = a;
			if (alpha > 0) {
				this.peerImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			} else {
				this.peerImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			}
		}
		
		Pointer pixelIterator = magick.NewPixelIterator(wand);
		IntByReference pWidth = new IntByReference();
		for (int i = 0; i < height; i++) {
			Pointer[] pixels = magick.PixelGetNextIteratorRow(pixelIterator, pWidth);
			int[] rowPixels = getRowPixels(pixels, width, alpha);
			for (int j = 0; j < width; j++) {
				this.peerImage.setRGB(j, i, rowPixels[j]);				
			}
		}
	}
	
	private int[] getRowPixels(Pointer[] pixels, int w, int alpha) {
		int[] rowPixels = new int[w];
		if (alpha > 0) {
			for (int i = 0; i < w; i++) {
				double r = magick.PixelGetRed(pixels[i]);
				double g = magick.PixelGetGreen(pixels[i]);
				double b = magick.PixelGetBlue(pixels[i]);
				double a = magick.PixelGetAlpha(pixels[i]);
				rowPixels[i] = Pixel.toInt(r, g, b, a);
			}				
		} else {
			for (int i = 0; i < w; i++) {
				double r = magick.PixelGetRed(pixels[i]);
				double g = magick.PixelGetGreen(pixels[i]);
				double b = magick.PixelGetBlue(pixels[i]);
				rowPixels[i] = Pixel.toInt(r, g, b);
			}
		}
		return rowPixels;
	}
	
	public boolean polynomial() {
		if (this.wand == null) {
			return false;
		}
		double arglist[] = { 0, 0,
				// RGB black
				0, 0, 0,
				// The y coordinate is filled in later
				0, -1,
				// RGB white
				1, 1, 1 };
		// arguments for MagickFunctionImage
		double funclist[] = { 8, -8, 1 };
	    arglist[6] = this.height - 1;
	 
	    magick.MagickSigmoidalContrastImage(this.wand, 1, 15, 8 * 30 / 100);
	    Pointer clonedWand = magick.CloneMagickWand(this.wand);
	    magick.MagickSparseColorImage(clonedWand, 
	    		ChannelType.RedChannel | ChannelType.GreenChannel | ChannelType.BlueChannel, 
	    		1, 10, arglist);
	    
	    // Do the polynomial function
	    magick.MagickFunctionImage(clonedWand, MagickFunction.PolynomialFunction, 3, funclist);
	    
	    // -set option:compose:args 15
	    if (magick.MagickSetImageArtifact(clonedWand, "compose:args", "15") == 0) {
	        clonedWand = magick.DestroyMagickWand(clonedWand);
	        return false;
	    }
	    
	    magick.MagickCompositeImage(this.wand, clonedWand, 57, 0, 0);
	    
	    clonedWand = magick.DestroyMagickWand(clonedWand);
	    
	    this.update();
	    return true;
	}
	
	public boolean drawText(String text) {
		if (this.wand == null) {
			return false;
		}
		Pointer drawWand = magick.NewDrawingWand();
		Pointer pixelWand = magick.NewPixelWand();
		
		magick.PixelSetColor(pixelWand, "white");
		magick.DrawSetFillColor(drawWand, pixelWand);
		magick.DrawSetFont(drawWand, "SimSun");
		magick.DrawSetFontSize(drawWand, 72);
		magick.DrawSetOpacity(drawWand, 0.4);
		magick.DrawSetTextEncoding(drawWand, "UTF-8");
		
		magick.DrawAnnotation(drawWand, 25, 65, text);
		
		magick.MagickDrawImage(this.wand, drawWand);
		
		if (drawWand != null) {
			magick.DestroyDrawingWand(drawWand);
		}
		
		if (pixelWand != null) {
			magick.DestroyPixelWand(pixelWand);
		}
		this.update();
		
		return true;
	}}
