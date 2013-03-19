package com.jucrus.test;
import com.jucrus.magick.Magick;
import com.jucrus.magick.NativeIM;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;


public class TestIM {

	public static void main(String[] args) {
		NativeIM magick = NativeIM.instance;

		magick.MagickWandGenesis();
		
		Pointer wand = magick.NewMagickWand();
		
		int status = magick.MagickReadImage(wand, "c:/icc.jpg");
		if (Magick.MagickBooleanType.MagickFalse == status) {
			System.out.println("failed");
		} else {
			System.out.println("succeed");
			int w = magick.MagickGetImageWidth(wand);
			int h = magick.MagickGetImageHeight(wand);
			int a = magick.MagickGetImageAlphaChannel(wand);
			
			System.out.println(w);
			System.out.println(h);
			System.out.println("alpha: " + a);
			
			int depth = magick.MagickGetImageDepth(wand);
			System.out.println(depth);
			
			Pointer pixelIterator = magick.NewPixelIterator(wand);
			IntByReference width = new IntByReference();
			Pointer[] pixels = magick.PixelGetNextIteratorRow(pixelIterator, width);
			System.out.println(width.getValue());
			
			for (int i = 0; i < width.getValue(); i++) {
				double r = magick.PixelGetRed(pixels[i]);
				double g = magick.PixelGetGreen(pixels[i]);
				double b = magick.PixelGetBlue(pixels[i]);
				
				System.out.print(r + " ");
				System.out.print(g + " ");
				System.out.print(b + " ");
				System.out.println();
			}
		}
		magick.DestroyMagickWand(wand);
		magick.MagickWandTerminus();
	}
}
