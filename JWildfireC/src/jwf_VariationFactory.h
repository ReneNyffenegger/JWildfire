/*
 JWildfireC - an external C-based fractal-flame-renderer for JWildfire
 Copyright (C) 2012 Andreas Maschke

 This is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser
 General Public License as published by the Free Software Foundation; either version 2.1 of the
 License, or (at your option) any later version.

 This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License along with this software;
 if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

#ifndef __JWF_VARIATION_FACTORY_H__
#define __JWF_VARIATION_FACTORY_H__

#include "stdio.h"
#include "string.h"
#include "jwf_XYZPoint.h"
#include "jwf_FlameTransformationContext.h"

#include "jwfvar_Auger.h"
#include "jwfvar_Arch.h"
#include "jwfvar_BCollide.h"
#include "jwfvar_Bent.h"
#include "jwfvar_Bent2.h"
#include "jwfvar_BiLinear.h"
#include "jwfvar_Bipolar.h"
#include "jwfvar_Blade.h"
#include "jwfvar_Blade3D.h"
#include "jwfvar_Blob.h"
#include "jwfvar_Blob3D.h"
#include "jwfvar_Blur.h"
#include "jwfvar_Blur3D.h"
#include "jwfvar_BMod.h"
#include "jwfvar_Boarders.h"
#include "jwfvar_Boarders2.h"
#include "jwfvar_BSwirl.h"
#include "jwfvar_BTransform.h"
#include "jwfvar_Bubble.h"
#include "jwfvar_BubbleWF.h"
#include "jwfvar_Butterfly.h"
#include "jwfvar_Butterfly3D.h"
#include "jwfvar_BWraps7.h"
#include "jwfvar_CannabisCurveWF.h"
#include "jwfvar_Cell.h"
#include "jwfvar_Checks.h"
#include "jwfvar_CircleCrop.h"
#include "jwfvar_Circlize.h"
#include "jwfvar_CloverLeafWF.h"
#include "jwfvar_Collideoscope.h"
#include "jwfvar_ColorScaleWF.h"
#include "jwfvar_Conic.h"
#include "jwfvar_Cos.h"
#include "jwfvar_Cosh.h"
#include "jwfvar_Cosine.h"
#include "jwfvar_Cot.h"
#include "jwfvar_Coth.h"
#include "jwfvar_CPow.h"
#include "jwfvar_Crackle.h"
#include "jwfvar_Crop.h"
#include "jwfvar_Cross.h"
#include "jwfvar_Cross2.h"
#include "jwfvar_Csc.h"
#include "jwfvar_Csch.h"
#include "jwfvar_Cubic3D.h"
#include "jwfvar_CubicLattice3D.h"
#include "jwfvar_Curl.h"
#include "jwfvar_Curl3D.h"
#include "jwfvar_Curve.h"
#include "jwfvar_Cylinder.h"
#include "jwfvar_CylinderApo.h"
#include "jwfvar_DCCube.h"
#include "jwfvar_DCPerlin.h"
#include "jwfvar_DCTransl.h"
#include "jwfvar_Diamond.h"
#include "jwfvar_Disc.h"
#include "jwfvar_Disc2.h"
#include "jwfvar_Disc_3D.h"
#include "jwfvar_Eclipse.h"
#include "jwfvar_ECollide.h"
#include "jwfvar_EDisc.h"
#include "jwfvar_EJulia.h"
#include "jwfvar_Elliptic.h"
#include "jwfvar_Epispiral.h"
#include "jwfvar_EpispiralWF.h"
#include "jwfvar_EMod.h"
#include "jwfvar_EMotion.h"
#include "jwfvar_EPush.h"
#include "jwfvar_ERotate.h"
#include "jwfvar_EScale.h"
#include "jwfvar_Escher.h"
#include "jwfvar_ESwirl.h"
#include "jwfvar_Ex.h"
#include "jwfvar_Exp.h"
#include "jwfvar_Exponential.h"
#include "jwfvar_Eyefish.h"
#include "jwfvar_Falloff2.h"
#include "jwfvar_Fan.h"
#include "jwfvar_Fan2.h"
#include "jwfvar_FarBlur.h"
#include "jwfvar_Fisheye.h"
#include "jwfvar_FlipCircle.h"
#include "jwfvar_FlipY.h"
#include "jwfvar_Flower.h"
#include "jwfvar_Flux.h"
#include "jwfvar_Foci.h"
#include "jwfvar_Foci3D.h"
#include "jwfvar_FractDragonWF.h"
#include "jwfvar_FractJuliaWF.h"
#include "jwfvar_FractMandelbrotWF.h"
#include "jwfvar_FractMeteorsWF.h"
#include "jwfvar_FractPearlsWF.h"
#include "jwfvar_FractSalamanderWF.h"
#include "jwfvar_GaussianBlur.h"
#include "jwfvar_Glynnia.h"
#include "jwfvar_GlynnSim1.h"
#include "jwfvar_GlynnSim2.h"
#include "jwfvar_GlynnSim3.h"
#include "jwfvar_Handkerchief.h"
#include "jwfvar_Heart.h"
#include "jwfvar_HeartWF.h"
#include "jwfvar_Horseshoe.h"
#include "jwfvar_Hyperbolic.h"
#include "jwfvar_InflateZ_1.h"
#include "jwfvar_InflateZ_2.h"
#include "jwfvar_InflateZ_3.h"
#include "jwfvar_InflateZ_4.h"
#include "jwfvar_InflateZ_5.h"
#include "jwfvar_InflateZ_6.h"
#include "jwfvar_Hemisphere.h"
#include "jwfvar_Hexes.h"
#include "jwfvar_Hexaplay3D.h"
#include "jwfvar_Hexnix3D.h"
#include "jwfvar_Hypertile.h"
#include "jwfvar_Hypertile1.h"
#include "jwfvar_Hypertile2.h"
#include "jwfvar_Julia.h"
#include "jwfvar_Julia3D.h"
#include "jwfvar_Julia3DZ.h"
#include "jwfvar_JuliaN.h"
#include "jwfvar_JuliaN2.h"
#include "jwfvar_Juliascope.h"
#include "jwfvar_Kaleidoscope.h"
#include "jwfvar_LazySusan.h"
#include "jwfvar_LayeredSpiral.h"
#include "jwfvar_Linear.h"
#include "jwfvar_Linear3D.h"
#include "jwfvar_LinearT.h"
#include "jwfvar_LinearT3D.h"
#include "jwfvar_Lissajous.h"
#include "jwfvar_Log.h"
#include "jwfvar_LogApo.h"
#include "jwfvar_Loonie.h"
#include "jwfvar_Loonie3D.h"
#include "jwfvar_Mandelbrot.h"
#include "jwfvar_Mobius.h"
#include "jwfvar_Modulus.h"
#include "jwfvar_NBlur.h"
#include "jwfvar_Ngon.h"
#include "jwfvar_Noise.h"
#include "jwfvar_NPolar.h"
#include "jwfvar_Oscilloscope.h"
#include "jwfvar_Parabola.h"
#include "jwfvar_PDJ.h"
#include "jwfvar_Perspective.h"
#include "jwfvar_PhoenixJulia.h"
#include "jwfvar_Pie.h"
#include "jwfvar_Pie3D.h"
#include "jwfvar_Polar.h"
#include "jwfvar_Polar2.h"
#include "jwfvar_Popcorn.h"
#include "jwfvar_Popcorn2.h"
#include "jwfvar_Popcorn2_3D.h"
#include "jwfvar_PostBWraps2.h"
#include "jwfvar_PostCircleCrop.h"
#include "jwfvar_PostColorScaleWF.h"
#include "jwfvar_PostCrop.h"
#include "jwfvar_PostCurl.h"
#include "jwfvar_PostCurl3D.h"
#include "jwfvar_PostDCTransl.h"
#include "jwfvar_PostFalloff2.h"
#include "jwfvar_PostMirrorWF.h"
#include "jwfvar_PostRotateX.h"
#include "jwfvar_PostRotateY.h"
#include "jwfvar_PostSpinZ.h"
#include "jwfvar_PostZScaleWF.h"
#include "jwfvar_PostZTranslateWF.h"
#include "jwfvar_Power.h"
#include "jwfvar_PreBlur.h"
#include "jwfvar_PreBoarders2.h"
#include "jwfvar_PreBWraps2.h"
#include "jwfvar_PreCircleCrop.h"
#include "jwfvar_PreCrop.h"
#include "jwfvar_PreDCTransl.h"
#include "jwfvar_PreRotateX.h"
#include "jwfvar_PreRotateY.h"
#include "jwfvar_PreSpinZ.h"
#include "jwfvar_PreSubFlameWF.h"
#include "jwfvar_PreWave3DWF.h"
#include "jwfvar_PreZScale.h"
#include "jwfvar_PreZTranslate.h"
#include "jwfvar_RadialBlur.h"
#include "jwfvar_Rays.h"
#include "jwfvar_Rectangles.h"
#include "jwfvar_Rings.h"
#include "jwfvar_Rings2.h"
#include "jwfvar_Ripple.h"
#include "jwfvar_RoseWF.h"
#include "jwfvar_RoundSpher3D.h"
#include "jwfvar_Scry.h"
#include "jwfvar_Scry3D.h"
#include "jwfvar_Sec.h"
#include "jwfvar_Sech.h"
#include "jwfvar_Secant2.h"
#include "jwfvar_Separation.h"
#include "jwfvar_Sin.h"
#include "jwfvar_Sinh.h"
#include "jwfvar_Sinusoidal.h"
#include "jwfvar_Spherical.h"
#include "jwfvar_Spherical3D.h"
#include "jwfvar_Spherical3DWF.h"
#include "jwfvar_SphericalN.h"
#include "jwfvar_Spiral.h"
#include "jwfvar_Spirograph.h"
#include "jwfvar_Split.h"
#include "jwfvar_Splits.h"
#include "jwfvar_Square.h"
#include "jwfvar_Square3D.h"
//#include "jwfvar_SubFlameWF.h" // already included with jwfvar_PreSubFlameWF.h
#include "jwfvar_Stripes.h"
#include "jwfvar_SuperShape.h"
#include "jwfvar_Swirl.h"
#include "jwfvar_Tan.h"
#include "jwfvar_Tanh.h"
#include "jwfvar_Tangent.h"
#include "jwfvar_Tangent3D.h"
#include "jwfvar_Truchet.h"
#include "jwfvar_Twintrian.h"
#include "jwfvar_Unpolar.h"
#include "jwfvar_Waffle.h"
#include "jwfvar_Waves.h"
#include "jwfvar_Waves2.h"
#include "jwfvar_Waves2_3D.h"
#include "jwfvar_Waves2WF.h"
#include "jwfvar_Waves3WF.h"
#include "jwfvar_Waves4WF.h"
#include "jwfvar_Wedge.h"
#include "jwfvar_WedgeJulia.h"
#include "jwfvar_WedgeSph.h"
#include "jwfvar_Whorl.h"
#include "jwfvar_XHeart.h"
#include "jwfvar_ZBlur.h"
#include "jwfvar_ZCone.h"
#include "jwfvar_ZScale.h"
#include "jwfvar_ZTranslate.h"

#define VARIATION_POOL_SIZE 1000

class VariationFactory {
public:
	VariationFactory() {
		variationCount = 0;
		variations = (Variation**) malloc(VARIATION_POOL_SIZE * sizeof(Variation*));
		initVariations();
	}

	~VariationFactory() {
		if (variations != NULL) {
			for (int i = 0; i < variationCount; i++) {
				delete (variations[i]);
			}
			free(variations);
			variations = NULL;
			variationCount = 0;
		}
	}

	int getVariationCount() {
		return variationCount;
	}

	Variation **getVariations() {
		return variations;
	}

	Variation* newInstance(char *varName) {
		Variation* srcVar = findVariation(varName);
		if (srcVar == NULL) {
			printf("Variation %s unknown\n", varName);
			exit(-1);
		}
		return srcVar->makeCopy();
	}

	Variation* findVariation(char *varName) {
		for (int i = 0; i < variationCount; i++) {
			if (strcmp(varName, variations[i]->getName()) == 0) {
				return variations[i];
			}
		}
		return NULL;
	}

private:
	int variationCount;
	Variation **variations;

	void initVariations() {
		addVariation(new AugerFunc());
		addVariation(new ArchFunc());
		addVariation(new BCollideFunc());
		addVariation(new BentFunc());
		addVariation(new Bent2Func());
		addVariation(new BiLinearFunc());
		addVariation(new BipolarFunc());
		addVariation(new BladeFunc());
		addVariation(new Blade3DFunc());
		addVariation(new BlobFunc());
		addVariation(new Blob3DFunc());
		addVariation(new BlurFunc());
		addVariation(new Blur3DFunc());
		addVariation(new BModFunc());
		addVariation(new BoardersFunc());
		addVariation(new Boarders2Func());
		addVariation(new BSwirlFunc());
		addVariation(new BTransformFunc());
		addVariation(new BubbleFunc());
		addVariation(new BubbleWFFunc());
		addVariation(new ButterflyFunc());
		addVariation(new Butterfly3DFunc());
		addVariation(new BWraps7Func());
		addVariation(new CannabisCurveWFFunc());
		addVariation(new CellFunc());
		addVariation(new ChecksFunc());
		addVariation(new CircleCropFunc());
		addVariation(new CirclizeFunc());
		addVariation(new CloverLeafWFFunc());
		addVariation(new CollideoscopeFunc());
		addVariation(new ColorScaleWFFunc());
		addVariation(new ConicFunc());
		addVariation(new CosFunc());
		addVariation(new CoshFunc());
		addVariation(new CosineFunc());
		addVariation(new CotFunc());
		addVariation(new CothFunc());
		addVariation(new CPowFunc());
		addVariation(new CrackleFunc());
		addVariation(new CropFunc());
		addVariation(new CrossFunc());
		addVariation(new Cross2Func());
		addVariation(new CscFunc());
		addVariation(new CschFunc());
		addVariation(new Cubic3DFunc());
		addVariation(new CubicLattice3DFunc());
		addVariation(new CurlFunc());
		addVariation(new Curl3DFunc());
		addVariation(new CurveFunc());
		addVariation(new CylinderFunc());
		addVariation(new CylinderApoFunc());
		addVariation(new DCCubeFunc());
		addVariation(new DCPerlinFunc());
		addVariation(new DCTranslFunc());
		addVariation(new DiamondFunc());
		addVariation(new DiscFunc());
		addVariation(new Disc2Func());
		addVariation(new Disc_3DFunc());
		addVariation(new EclipseFunc());
		addVariation(new ECollideFunc());
		addVariation(new EDiscFunc());
		addVariation(new EJuliaFunc());
		addVariation(new EllipticFunc());
		addVariation(new EpispiralFunc());
		addVariation(new EpispiralWFFunc());
		addVariation(new EModFunc());
		addVariation(new EMotionFunc());
		addVariation(new EPushFunc());
		addVariation(new ERotateFunc());
		addVariation(new EScaleFunc());
		addVariation(new EscherFunc());
		addVariation(new ESwirlFunc());
		addVariation(new ExFunc());
		addVariation(new ExpFunc());
		addVariation(new ExponentialFunc());
		addVariation(new EyefishFunc());
		addVariation(new Falloff2Func());
		addVariation(new FanFunc());
		addVariation(new Fan2Func());
		addVariation(new FarBlurFunc());
		addVariation(new FisheyeFunc());
		addVariation(new FlipCircleFunc());
		addVariation(new FlipYFunc());
		addVariation(new FlowerFunc());
		addVariation(new FluxFunc());
		addVariation(new FociFunc());
		addVariation(new Foci3DFunc());
		addVariation(new FractDragonWFFunc());
		addVariation(new FractJuliaWFFunc());
		addVariation(new FractMandelbrotWFFunc());
		addVariation(new FractMeteorsWFFunc());
		addVariation(new FractPearlsWFFunc());
		addVariation(new FractSalamanderWFFunc());
		addVariation(new GaussianBlurFunc());
		addVariation(new GlynniaFunc());
		addVariation(new GlynnSim1Func());
		addVariation(new GlynnSim2Func());
		addVariation(new GlynnSim3Func());
		addVariation(new HandkerchiefFunc());
		addVariation(new HeartFunc());
		addVariation(new HeartWFFunc());
		addVariation(new Hexaplay3DFunc());
		addVariation(new Hexnix3DFunc());
		addVariation(new HyperbolicFunc());
		addVariation(new HorseshoeFunc());
		addVariation(new HemisphereFunc());
		addVariation(new HexesFunc());
		addVariation(new HypertileFunc());
		addVariation(new Hypertile1Func());
		addVariation(new Hypertile2Func());
		addVariation(new InflateZ_1Func());
		addVariation(new InflateZ_2Func());
		addVariation(new InflateZ_3Func());
		addVariation(new InflateZ_4Func());
		addVariation(new InflateZ_5Func());
		addVariation(new InflateZ_6Func());
		addVariation(new JuliaFunc());
		addVariation(new Julia3DFunc());
		addVariation(new Julia3DZFunc());
		addVariation(new JuliaNFunc());
		addVariation(new JuliaN2Func());
		addVariation(new JuliascopeFunc());
		addVariation(new KaleidoscopeFunc());
		addVariation(new LayeredSpiralFunc());
		addVariation(new LazySusanFunc());
		addVariation(new LinearFunc());
		addVariation(new Linear3DFunc());
		addVariation(new LinearTFunc());
		addVariation(new LinearT3DFunc());
		addVariation(new LissajousFunc());
		addVariation(new LogFunc());
		addVariation(new LogApoFunc());
		addVariation(new LoonieFunc());
		addVariation(new Loonie3DFunc());
		addVariation(new MandelbrotFunc());
		addVariation(new MobiusFunc());
		addVariation(new ModulusFunc());
		addVariation(new NBlurFunc());
		addVariation(new NgonFunc());
		addVariation(new NoiseFunc());
		addVariation(new NPolarFunc());
		addVariation(new OscilloscopeFunc());
		addVariation(new ParabolaFunc());
		addVariation(new PDJFunc());
		addVariation(new PerspectiveFunc());
		addVariation(new PhoenixJuliaFunc());
		addVariation(new PieFunc());
		addVariation(new Pie3DFunc());
		addVariation(new PolarFunc());
		addVariation(new Polar2Func());
		addVariation(new PopcornFunc());
		addVariation(new Popcorn2Func());
		addVariation(new Popcorn2_3DFunc());
		addVariation(new PostBWraps2Func());
		addVariation(new PostCircleCropFunc());
		addVariation(new PostColorScaleWFFunc());
		addVariation(new PostCropFunc());
		addVariation(new PostCurlFunc());
		addVariation(new PostCurl3DFunc());
		addVariation(new PostDCTranslFunc());
		addVariation(new PostFalloff2Func());
		addVariation(new PostMirrorWFFunc());
		addVariation(new PostRotateXFunc());
		addVariation(new PostRotateYFunc());
		addVariation(new PostSpinZFunc());
		addVariation(new PostZScaleWFFunc());
		addVariation(new PostZTranslateWFFunc());
		addVariation(new PowerFunc());
		addVariation(new PreBlurFunc());
		addVariation(new PreBoarders2Func());
		addVariation(new PreBWraps2Func());
		addVariation(new PreCircleCropFunc());
		addVariation(new PreCropFunc());
		addVariation(new PreDCTranslFunc());
		addVariation(new PreRotateXFunc());
		addVariation(new PreRotateYFunc());
		addVariation(new PreSpinZFunc());
		addVariation(new PreSubFlameWFFunc());
		addVariation(new PreWave3DWFFunc());
		addVariation(new PreZScaleFunc());
		addVariation(new PreZTranslateFunc());
		addVariation(new RadialBlurFunc());
		addVariation(new RaysFunc());
		addVariation(new RectanglesFunc());
		addVariation(new RingsFunc());
		addVariation(new Rings2Func());
		addVariation(new RippleFunc());
		addVariation(new RoseWFFunc());
		addVariation(new RoundSpher3DFunc());
		addVariation(new ScryFunc());
		addVariation(new Scry3DFunc());
		addVariation(new SecFunc());
		addVariation(new SechFunc());
		addVariation(new Secant2Func());
		addVariation(new SeparationFunc());
		addVariation(new SinFunc());
		addVariation(new SinhFunc());
		addVariation(new SinusoidalFunc());
		addVariation(new SphericalFunc());
		addVariation(new Spherical3DFunc());
		addVariation(new Spherical3DWFFunc());
		addVariation(new SphericalNFunc());
		addVariation(new SpiralFunc());
		addVariation(new SpirographFunc());
		addVariation(new SplitFunc());
		addVariation(new SplitsFunc());
		addVariation(new SquareFunc());
		addVariation(new Square3DFunc());
		addVariation(new StripesFunc());
		addVariation(new SubFlameWFFunc());
		addVariation(new SuperShapeFunc());
		addVariation(new SwirlFunc());
		addVariation(new TanFunc());
		addVariation(new TanhFunc());
		addVariation(new TangentFunc());
		addVariation(new Tangent3DFunc());
		addVariation(new TruchetFunc());
		addVariation(new TwintrianFunc());
		addVariation(new UnpolarFunc());
		addVariation(new WaffleFunc());
		addVariation(new WavesFunc());
		addVariation(new Waves2Func());
		addVariation(new Waves2_3DFunc());
		addVariation(new Waves2WFFunc());
		addVariation(new Waves3WFFunc());
		addVariation(new Waves4WFFunc());
		addVariation(new WedgeFunc());
		addVariation(new WedgeJuliaFunc());
		addVariation(new WedgeSphFunc());
		addVariation(new WhorlFunc());
		addVariation(new XHeartFunc());
		addVariation(new ZBlurFunc());
		addVariation(new ZConeFunc());
		addVariation(new ZScaleFunc());
		addVariation(new ZTranslateFunc());
	}

	void addVariation(Variation *var) {
		if (variationCount >= VARIATION_POOL_SIZE) {
			printf("Variation pool size %d exceeded\n", VARIATION_POOL_SIZE);
			exit(-1);
		}
		variations[variationCount++] = var;
	}

};

#undef VARIATION_POOL_SIZE

#endif // __JWF_VARIATION_FACTORY_H__
