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

	public Pointer CloneMagickWand(Pointer wand);

	public int MagickReadImage(Pointer wand, String path);

	public int MagickGetImageWidth(Pointer wand);

	public int MagickGetImageHeight(Pointer wand);

	public int MagickGetImageDepth(Pointer wand);

	public int MagickGetImageAlphaChannel(Pointer wand);

	public Pointer NewPixelIterator(Pointer wand);

	public Pointer[] PixelGetNextIteratorRow(Pointer PixelIterator,
			IntByReference w);

	public double PixelGetRed(Pointer pixelWand);

	public double PixelGetGreen(Pointer pixelWand);

	public double PixelGetBlue(Pointer pixelWand);

	public double PixelGetAlpha(Pointer pixelWand);

	public int MagickSigmoidalContrastImage(Pointer wand, int sharpen,
			double alpha, double beta);

	public int MagickSparseColorImage(Pointer wand, int channel, int method,
			int number_arguments, double[] arguments);

	public int MagickSetImageArtifact(Pointer wand, String artifact,
			String value);

	public int MagickFunctionImage(Pointer wand, int function,
			int number_arguments, double[] arguments);

	public int MagickCompositeImage(Pointer wand, Pointer source_wand,
			int compositeOperator, int x, int y);

	/****************** Module Magick-Image Methods ******************************/
	public int MagickDrawImage(Pointer wand, Pointer drawWand);

	public int MagickNewImage(Pointer wand, int columns, int rows,
			Pointer pixelWand);

	public Pointer DestroyMagickWand(Pointer wand);

	public void MagickWandTerminus();

	/****************** Module Drawing-Wand Methods ******************************/
	public Pointer NewDrawingWand();

	public Pointer DestroyDrawingWand(Pointer drawWand);

	public void DrawSetFillColor(Pointer drawWand, Pointer fillPixelWand);

	public int DrawSetFont(Pointer drawWand, String fontName);

	public void DrawSetFontSize(Pointer drawWand, double pointsize);

	public void DrawAnnotation(Pointer drawWand, double x, double y, String text);

	public void DrawSetOpacity(Pointer drawWand, double opacity);

	public String DrawGetTextEncoding(Pointer drawWand);

	public void DrawSetTextEncoding(Pointer drawWand, String encoding);

	public void DrawRoundRectangle(Pointer drawWand, double x1, double y1,
			double x2, double y2, double rx, double ry);

	public void DrawSetStrokeAntialias(Pointer drawWand, int stroke_antialias);

	public void DrawSetStrokeWidth(Pointer drawWand, double stroke_width);

	public void DrawSetStrokeLineCap(Pointer drawWand, int linecap);

	public void DrawSetStrokeLineJoin(Pointer drawWand, int linejoin);

	public void DrawSetStrokeColor(Pointer drawWand, Pointer pixel_stroke_wand);

	public void DrawSetFillRule(Pointer drawWand, int fill_rule);

	public void DrawPolygon(Pointer drawWand, int number_coordinates,
			Magick.PointInfo[] coordinates);

	public int DrawSetStrokeDashArray(Pointer drawWand, int number_elements,
			double[] dash_array);

	public void DrawBezier(Pointer drawWand, int number_coordinates,
			Magick.PointInfo[] coordinates);

	/****************** Module Pixel-Wand Methods ******************************/
	public Pointer NewPixelWand();

	public Pointer DestroyPixelWand(Pointer pixelWand);

	public int PixelSetColor(Pointer pixelWand, String color);

}
