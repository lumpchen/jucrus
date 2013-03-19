package com.jucrus.magick;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public interface NativeIM extends Library {
	public static NativeIM instance = (NativeIM) Native.loadLibrary(
			"CORE_RL_wand_", NativeIM.class);

	public void MagickWandGenesis();

	public Pointer NewMagickWand();

	/**
	 * CloneMagickWand() makes an exact copy of the specified wand.
	 * */
	public Pointer CloneMagickWand(Pointer wand);

	public int MagickReadImage(Pointer wand, String path);

	/**
	 * Pointer point to MagickWand.
	 * */
	public int MagickGetImageWidth(Pointer wand);

	public int MagickGetImageHeight(Pointer wand);

	public int MagickGetImageDepth(Pointer wand);

	public int MagickGetImageAlphaChannel(Pointer wand);

	/**
	 * @return PixelIterator *.
	 * */
	public Pointer NewPixelIterator(Pointer wand);

	/**
	 * @return PixelWand **.
	 * */
	public Pointer[] PixelGetNextIteratorRow(Pointer PixelIterator,
			IntByReference w);

	/**
	 * Module Pixel-Wand Methods
	 */
	public double PixelGetRed(Pointer pixelWand);

	public double PixelGetGreen(Pointer pixelWand);

	public double PixelGetBlue(Pointer pixelWand);

	public double PixelGetAlpha(Pointer pixelWand);

	/**
	 * MagickSigmoidalContrastImage() adjusts the contrast of an image with a
	 * non-linear sigmoidal contrast algorithm. Increase the contrast of the
	 * image using a sigmoidal transfer function without saturating highlights
	 * or shadows. Contrast indicates how much to increase the contrast (0 is
	 * none; 3 is typical; 20 is pushing it); mid-point indicates where midtones
	 * fall in the resultant image (0 is white; 50 is middle-gray; 100 is
	 * black). Set sharpen to MagickTrue to increase the image contrast
	 * otherwise the contrast is reduced.
	 * 
	 * wand: the magick wand. channel: Identify which channel to level:
	 * RedChannel, GreenChannel, sharpen: Increase or decrease image contrast.
	 * alpha: strength of the contrast, the larger the number the more
	 * 'threshold-like' it becomes. beta: midpoint of the function as a color
	 * value 0 to QuantumRange.
	 * */
	public int MagickSigmoidalContrastImage(Pointer wand, int sharpen,
			double alpha, double beta);

	/**
	 * MagickSparseColorImage(), given a set of coordinates, interpolates the
	 * colors found at those coordinates, across the whole image, using various
	 * methods. image: the image to be sparseed. method: the method of image
	 * sparseion.
	 */
	public int MagickSparseColorImage(Pointer wand, int channel, int method,
			int number_arguments, double[] arguments);

	/**
	 * MagickSetImageArtifact() associates a artifact with an image.
	 * */
	public int MagickSetImageArtifact(Pointer wand, String artifact,
			String value);

	public int MagickFunctionImage(Pointer wand, int function,
			int number_arguments, double[] arguments);

	public int MagickCompositeImage(Pointer wand, Pointer source_wand,
			int compositeOperator, int x, int y);

	/****************** Module Magick-Image Methods ******************************/
	public int MagickDrawImage(Pointer wand, Pointer drawWand);

	/***************************************************************************/

	/****************** Module Drawing-Wand Methods ******************************/
	/**
	 * NewDrawingWand() returns a drawing wand required for all other methods in
	 * the API.
	 */
	public Pointer NewDrawingWand();

	/**
	 * DestroyDrawingWand() frees all resources associated with the drawing
	 * wand. Once the drawing wand has been freed, it should not be used and
	 * further unless it re-allocated.
	 */
	public Pointer DestroyDrawingWand(Pointer drawWand);

	public void DrawSetFillColor(Pointer drawWand, Pointer fillPixelWand);

	public int DrawSetFont(Pointer drawWand, String fontName);

	public void DrawSetFontSize(Pointer drawWand, double pointsize);

	public void DrawAnnotation(Pointer drawWand, double x, double y, String text);

	public void DrawSetOpacity(Pointer drawWand, double opacity);

	public String DrawGetTextEncoding(Pointer drawWand);

	public void DrawSetTextEncoding(Pointer drawWand, String encoding);

	/***************************************************************************/

	/****************** Module Pixel-Wand Methods ******************************/
	/**
	 * NewPixelWand() returns a new pixel wand.
	 */
	public Pointer NewPixelWand();

	/**
	 * DestroyPixelWand() deallocates resources associated with a PixelWand.
	 */
	public Pointer DestroyPixelWand(Pointer pixelWand);

	/**
	 * PixelSetColor() sets the color of the pixel wand with a string (e.g.
	 * "blue", "#0000ff", "rgb(0,0,255)", "cmyk(100,100,100,10)", etc.).
	 */
	public int PixelSetColor(Pointer pixelWand, String color);

	/***************************************************************************/

	public Pointer DestroyMagickWand(Pointer wand);

	public void MagickWandTerminus();
}
