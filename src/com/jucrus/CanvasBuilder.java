package com.jucrus;

import java.awt.image.BufferedImage;

import com.jucrus.magick.Magick;
import com.jucrus.magick.Magick.ChannelType;
import com.jucrus.magick.Magick.MagickFunction;
import com.jucrus.magick.NativeIM;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public class CanvasBuilder {
	static NativeIM magick = NativeIM.instance;
	
	private Pointer wand;
	private int width;
	private int height;
	
	public CanvasBuilder() {
		magick.MagickWandGenesis();
	}
	
	public Canvas build(String path) {
		this.wand = magick.NewMagickWand();
		
		int status = magick.MagickReadImage(wand, path);
		if (Magick.MagickBooleanType.MagickFalse == status) {
			return null;
		} else {
			return this.repaint();
		}
	}
	
	private Canvas repaint() {
		this.width = magick.MagickGetImageWidth(wand);
		this.height = magick.MagickGetImageHeight(wand);
		int alpha = magick.MagickGetImageAlphaChannel(wand);
		
		Canvas canvas = null;
		if (alpha > 0) {
			canvas = new Canvas(width, height, BufferedImage.TYPE_INT_ARGB);
		} else {
			canvas = new Canvas(width, height, BufferedImage.TYPE_INT_RGB);
		}
		
		Pointer pixelIterator = magick.NewPixelIterator(wand);
		IntByReference pWidth = new IntByReference();

		for (int i = 0; i < height; i++) {
			Pointer[] pixels = magick.PixelGetNextIteratorRow(pixelIterator, pWidth);
			int[] rowPixels = getRowPixels(pixels, width, alpha);
			canvas.setRowPixels(i, rowPixels);
		}
		return canvas;
	}
	
	public void destroy() {
		magick.DestroyMagickWand(wand);
		magick.MagickWandTerminus();		
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
	
	public Canvas polynomial() {
		if (this.wand == null) {
			return null;
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
	        return null;
	    }
	    
	    magick.MagickCompositeImage(this.wand, clonedWand, 57, 0, 0);
	    
	    clonedWand = magick.DestroyMagickWand(clonedWand);
	    
	    return this.repaint();
	}
	
	public Canvas drawText(String text) {
		Pointer drawWand = magick.NewDrawingWand();
		Pointer pixelWand = magick.NewPixelWand();
		
		magick.PixelSetColor(pixelWand, "white");
		magick.DrawSetFillColor(drawWand, pixelWand);
		magick.DrawSetFont(drawWand, "SimSun");
		magick.DrawSetFontSize(drawWand, 72);
		magick.DrawSetOpacity(drawWand, 0.4);
		magick.DrawSetTextEncoding(drawWand, "UTF-8");
		System.out.println(magick.DrawGetTextEncoding(drawWand));
		
		magick.DrawAnnotation(drawWand, 25, 65, text);
		
		magick.MagickDrawImage(this.wand, drawWand);
		
		if (drawWand != null) {
			magick.DestroyDrawingWand(drawWand);
		}
		
		if (pixelWand != null) {
			magick.DestroyPixelWand(pixelWand);
		}
		return this.repaint();
	}}
