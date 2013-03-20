package com.jucrus.magick;

import com.sun.jna.Structure;

public interface Magick {

	public static interface ClassType {
		public static final int UndefinedClass = 0;
		public static final int DirectClass = 1;
		public static final int PseudoClass = 2;
	};

	public static class PixelPacket extends Structure {
		public short red;
		public short green;
		public short blue;
		public short opacity;
	};

	public static class PointInfo extends Structure {
		public double x;
		public double y;
	};

	public static interface CompressionType {
		public static final int UndefinedCompression = 0;
		public static final int NoCompression = 1;
		public static final int BZipCompression = 2;
		public static final int DXT1Compression = 3;
		public static final int DXT3Compression = 4;
		public static final int DXT5Compression = 5;
		public static final int FaxCompression = 6;
		public static final int Group4Compression = 7;
		public static final int JPEGCompression = 8;
		public static final int JPEG2000Compression = 9; /* ISO/IEC std 15444-1 */
		public static final int LosslessJPEGCompression = 10;
		public static final int LZWCompression = 11;
		public static final int RLECompression = 12;
		public static final int ZipCompression = 13;
		public static final int ZipSCompression = 14;
		public static final int PizCompression = 15;
		public static final int Pxr24Compression = 16;
		public static final int B44Compression = 17;
		public static final int B44ACompression = 18;
		public static final int LZMACompression = 19; /*
													 * Lempel-Ziv-Markov chain
													 * algorithm
													 */
		public static final int JBIG1Compression = 20; /*
														 * ISO/IEC std 11544 /
														 * ITU-T rec T.82
														 */
		public static final int JBIG2Compression = 21; /*
														 * ISO/IEC std 14492 /
														 * ITU-T rec T.88
														 */
	};

	public static interface OrientationType {
		public static final int UndefinedOrientation = 0;
		public static final int TopLeftOrientation = 1;
		public static final int TopRightOrientation = 2;
		public static final int BottomRightOrientation = 3;
		public static final int BottomLeftOrientation = 4;
		public static final int LeftTopOrientation = 5;
		public static final int RightTopOrientation = 6;
		public static final int RightBottomOrientation = 7;
		public static final int LeftBottomOrientation = 8;
	};

	public static interface MagickBooleanType {
		public static final int MagickFalse = 0;
		public static final int MagickTrue = 1;
	};

	public static interface InterlaceType {
		public static final int UndefinedInterlace = 0;
		public static final int NoInterlace = 1;
		public static final int LineInterlace = 2;
		public static final int PlaneInterlace = 3;
		public static final int PartitionInterlace = 4;
		public static final int GIFInterlace = 5;
		public static final int JPEGInterlace = 6;
		public static final int PNGInterlace = 7;
	};

	public static interface EndianType {
		public static final int UndefinedEndian = 0;
		public static final int LSBEndian = 1;
		public static final int MSBEndian = 2;
	};

	public static interface ResolutionType {
		public static final int UndefinedResolution = 0;
		public static final int PixelsPerInchResolution = 1;
		public static final int PixelsPerCentimeterResolution = 2;
	};

	public static interface ColorspaceType {
		public static final int UndefinedColorspace = 0;
		public static final int RGBColorspace = 1;
		public static final int GRAYColorspace = 2;
		public static final int TransparentColorspace = 3;
		public static final int OHTAColorspace = 4;
		public static final int LabColorspace = 5;
		public static final int XYZColorspace = 6;
		public static final int YCbCrColorspace = 7;
		public static final int YCCColorspace = 8;
		public static final int YIQColorspace = 9;
		public static final int YPbPrColorspace = 10;
		public static final int YUVColorspace = 11;
		public static final int CMYKColorspace = 12;
		public static final int sRGBColorspace = 13;
		public static final int HSBColorspace = 14;
		public static final int HSLColorspace = 15;
		public static final int HWBColorspace = 16;
		public static final int Rec601LumaColorspace = 17;
		public static final int Rec601YCbCrColorspace = 18;
		public static final int Rec709LumaColorspace = 19;
		public static final int Rec709YCbCrColorspace = 20;
		public static final int LogColorspace = 21;
		public static final int CMYColorspace = 22;
		public static final int LuvColorspace = 23;
		public static final int HCLColorspace = 24;
		public static final int LCHColorspace = 25;
		public static final int LMSColorspace = 26;
	};

	public static interface ImageType {
		public static final int UndefinedType = 0;
		public static final int BilevelType = 1;
		public static final int GrayscaleType = 2;
		public static final int GrayscaleMatteType = 3;
		public static final int PaletteType = 4;
		public static final int PaletteMatteType = 5;
		public static final int TrueColorType = 6;
		public static final int TrueColorMatteType = 7;
		public static final int ColorSeparationType = 8;
		public static final int ColorSeparationMatteType = 9;
		public static final int PaletteBilevelMatteType = 10;
	};

	public static interface PreviewType {
		public static final int UndefinedPreview = 0;
		public static final int RotatePreview = 1;
		public static final int ShearPreview = 2;
		public static final int RollPreview = 3;
		public static final int HuePreview = 4;
		public static final int SaturationPreview = 5;
		public static final int BrightnessPreview = 6;
		public static final int GammaPreview = 7;
		public static final int SpiffPreview = 8;
		public static final int GrayscalePreview = 9;
		public static final int QuantizePreview = 10;
		public static final int DespecklePreview = 11;
		public static final int ReduceNoisePreview = 12;
		public static final int AddNoisePreview = 13;
		public static final int SharpenPreview = 14;
		public static final int BlurPreview = 15;
		public static final int ThresholdPreview = 16;
		public static final int EdgeDetectPreview = 17;
		public static final int SpreadPreview = 18;
		public static final int SolarizePreview = 19;
		public static final int RaisePreview = 20;
		public static final int SegmentPreview = 21;
		public static final int SwirlPreview = 22;
		public static final int ImplodePreview = 23;
		public static final int WavePreview = 24;
		public static final int OilPaintPreview = 25;
		public static final int CharcoalDrawingPreview = 26;
		public static final int JPEGPreview = 27;
	};

	public static interface ChannelType {
		public static final int UndefinedChannel = 0;
		public static final int RedChannel = 0x0001;
		public static final int GrayChannel = 0x0001;
		public static final int CyanChannel = 0x0001;
		public static final int GreenChannel = 0x0002;
		public static final int MagentaChannel = 0x0002;
		public static final int BlueChannel = 0x0004;
		public static final int YellowChannel = 0x0004;
		public static final int AlphaChannel = 0x0008;
		public static final int OpacityChannel = 0x0008;
		public static final int MatteChannel = 0x0008; /* deprecated */
		public static final int BlackChannel = 0x0020;
		public static final int IndexChannel = 0x0020;
		public static final int CompositeChannels = 0x002F;
		public static final int AllChannels = 0x7ffffff;
		/*
		 * Special purpose channel types.
		 */
		public static final int TrueAlphaChannel = 0x0040; /*
															 * extract actual
															 * alpha channel
															 * from opacity
															 */
		public static final int RGBChannels = 0x0080; /*
													 * set alpha from grayscale
													 * mask in RGB
													 */
		public static final int GrayChannels = 0x0080;
		public static final int SyncChannels = 0x0100; /*
														 * channels should be
														 * modified equally
														 */
		public static final int DefaultChannels = ((AllChannels | SyncChannels) & ~OpacityChannel);
	};

	public static interface MagickEvaluateOperator {
		public static final int UndefinedEvaluateOperator = 0;
		public static final int AddEvaluateOperator = 1;
		public static final int AndEvaluateOperator = 2;
		public static final int DivideEvaluateOperator = 3;
		public static final int LeftShiftEvaluateOperator = 4;
		public static final int MaxEvaluateOperator = 5;
		public static final int MinEvaluateOperator = 6;
		public static final int MultiplyEvaluateOperator = 7;
		public static final int OrEvaluateOperator = 8;
		public static final int RightShiftEvaluateOperator = 9;
		public static final int SetEvaluateOperator = 10;
		public static final int SubtractEvaluateOperator = 11;
		public static final int XorEvaluateOperator = 12;
		public static final int PowEvaluateOperator = 13;
		public static final int LogEvaluateOperator = 14;
		public static final int ThresholdEvaluateOperator = 15;
		public static final int ThresholdBlackEvaluateOperator = 16;
		public static final int ThresholdWhiteEvaluateOperator = 17;
		public static final int GaussianNoiseEvaluateOperator = 18;
		public static final int ImpulseNoiseEvaluateOperator = 19;
		public static final int LaplacianNoiseEvaluateOperator = 20;
		public static final int MultiplicativeNoiseEvaluateOperator = 21;
		public static final int PoissonNoiseEvaluateOperator = 22;
		public static final int UniformNoiseEvaluateOperator = 23;
		public static final int CosineEvaluateOperator = 24;
		public static final int SineEvaluateOperator = 25;
		public static final int AddModulusEvaluateOperator = 26;
		public static final int MeanEvaluateOperator = 27;
		public static final int AbsEvaluateOperator = 28;
		public static final int ExponentialEvaluateOperator = 29;
		public static final int MedianEvaluateOperator = 30;
		public static final int SumEvaluateOperator = 31;
	};

	public static interface MagickFunction {
		public static final int UndefinedFunction = 0;
		public static final int PolynomialFunction = 1;
		public static final int SinusoidFunction = 2;
		public static final int ArcsinFunction = 3;
		public static final int ArctanFunction = 4;
	};

	public static interface CompositeOperator {
		public static final int UndefinedCompositeOp = 0;
		public static final int NoCompositeOp = 1;
		public static final int ModulusAddCompositeOp = 2;
		public static final int AtopCompositeOp = 3;
		public static final int BlendCompositeOp = 4;
		public static final int BumpmapCompositeOp = 5;
		public static final int ChangeMaskCompositeOp = 6;
		public static final int ClearCompositeOp = 7;
		public static final int ColorBurnCompositeOp = 8;
		public static final int ColorDodgeCompositeOp = 9;
		public static final int ColorizeCompositeOp = 10;
		public static final int CopyBlackCompositeOp = 11;
		public static final int CopyBlueCompositeOp = 12;
		public static final int CopyCompositeOp = 13;
		public static final int CopyCyanCompositeOp = 14;
		public static final int CopyGreenCompositeOp = 15;
		public static final int CopyMagentaCompositeOp = 16;
		public static final int CopyOpacityCompositeOp = 17;
		public static final int CopyRedCompositeOp = 18;
		public static final int CopyYellowCompositeOp = 19;
		public static final int DarkenCompositeOp = 20;
		public static final int DstAtopCompositeOp = 21;
		public static final int DstCompositeOp = 22;
		public static final int DstInCompositeOp = 23;
		public static final int DstOutCompositeOp = 24;
		public static final int DstOverCompositeOp = 25;
		public static final int DifferenceCompositeOp = 26;
		public static final int DisplaceCompositeOp = 27;
		public static final int DissolveCompositeOp = 28;
		public static final int ExclusionCompositeOp = 29;
		public static final int HardLightCompositeOp = 30;
		public static final int HueCompositeOp = 31;
		public static final int InCompositeOp = 32;
		public static final int LightenCompositeOp = 33;
		public static final int LinearLightCompositeOp = 34;
		public static final int LuminizeCompositeOp = 35;
		public static final int MinusDstCompositeOp = 36;
		public static final int ModulateCompositeOp = 37;
		public static final int MultiplyCompositeOp = 38;
		public static final int OutCompositeOp = 39;
		public static final int OverCompositeOp = 40;
		public static final int OverlayCompositeOp = 41;
		public static final int PlusCompositeOp = 42;
		public static final int ReplaceCompositeOp = 43;
		public static final int SaturateCompositeOp = 44;
		public static final int ScreenCompositeOp = 45;
		public static final int SoftLightCompositeOp = 46;
		public static final int SrcAtopCompositeOp = 47;
		public static final int SrcCompositeOp = 48;
		public static final int SrcInCompositeOp = 49;
		public static final int SrcOutCompositeOp = 50;
		public static final int SrcOverCompositeOp = 51;
		public static final int ModulusSubtractCompositeOp = 52;
		public static final int ThresholdCompositeOp = 53;
		public static final int XorCompositeOp = 54;
		public static final int DivideDstCompositeOp = 55;
		public static final int DistortCompositeOp = 56;
		public static final int BlurCompositeOp = 57;
		public static final int PegtopLightCompositeOp = 58;
		public static final int VividLightCompositeOp = 59;
		public static final int PinLightCompositeOp = 60;
		public static final int LinearDodgeCompositeOp = 61;
		public static final int LinearBurnCompositeOp = 62;
		public static final int MathematicsCompositeOp = 63;
		public static final int DivideSrcCompositeOp = 64;
		public static final int MinusSrcCompositeOp = 65;
		public static final int DarkenIntensityCompositeOp = 66;
		public static final int LightenIntensityCompositeOp = 67;
	};
	
	public static interface LineCap {
		public static final int UndefinedCap = 0;
		public static final int ButtCap = 1;
		public static final int RoundCap = 2;
		public static final int SquareCap = 3;
	};
	
	public static interface LineJoin {
		public static final int UndefinedJoin = 0;
		public static final int MiterJoin = 1;
		public static final int RoundJoin = 2;
		public static final int BevelJoin = 3;
	};
	
	public static interface FillRule {
		public static final int UndefinedRule = 0;
		public static final int EvenOddRule = 1;
		public static final int NonZeroRule = 2;
	};
}
