/*
  JWildfire - an image and animation processor written in Java 
  Copyright (C) 1995-2011 Andreas Maschke

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
package org.jwildfire.create.tina.palette;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jwildfire.base.Tools;
import org.jwildfire.create.tina.animate.AnimAware;
import org.jwildfire.create.tina.edit.Assignable;
import org.jwildfire.image.Pixel;
import org.jwildfire.image.SimpleImage;
import org.jwildfire.transform.BalancingTransformer;
import org.jwildfire.transform.HSLTransformer;
import org.jwildfire.transform.SwapRGBTransformer;
import org.jwildfire.transform.SwapRGBTransformer.Mode;

public class RGBPalette implements Assignable<RGBPalette>, Serializable {
  private static final long serialVersionUID = 1L;
  public static final int PALETTE_SIZE = 256;
  static final RGBColor BLACK = new RGBColor(0, 0, 0);
  private int highestIdx = -1;
  private boolean modified = true;
  @AnimAware
  private int modRed;
  @AnimAware
  private int modGreen;
  @AnimAware
  private int modBlue;
  @AnimAware
  private int modShift;
  @AnimAware
  private int modHue;
  @AnimAware
  private int modContrast;
  @AnimAware
  private int modGamma;
  @AnimAware
  private int modBrightness;
  @AnimAware
  private int modSaturation;
  @AnimAware
  private int modSwapRGB;
  @AnimAware
  private int modFrequency = 1;
  @AnimAware
  private int modBlur;
  private String flam3Number; // imported from flam3, just for display
  private String flam3Name; // imported from flam3, just for display

  private Map<Integer, RGBColor> rawColors = new HashMap<Integer, RGBColor>();
  private Map<Integer, RGBColor> transformedColors = new HashMap<Integer, RGBColor>();

  public void addColor(int pRed, int pGreen, int pBlue) {
    RGBColor color = new RGBColor(pRed, pGreen, pBlue);
    if (highestIdx + 1 == PALETTE_SIZE) {
      throw new IllegalStateException("Palette is already complete");
    }
    else {
      rawColors.put(Integer.valueOf(++highestIdx), color);
    }
    modified = true;
  }

  public void setColor(int pIndex, int pRed, int pGreen, int pBlue) {
    if (pIndex < 0 || pIndex >= PALETTE_SIZE)
      throw new IllegalArgumentException(pIndex + " is no valid index");
    RGBColor color = new RGBColor(pRed, pGreen, pBlue);
    rawColors.put(Integer.valueOf(pIndex), color);
    modified = true;
  }

  public RGBColor getColor(int pIdx) {
    transformColors();
    RGBColor res = transformedColors.get(pIdx);
    return res != null ? res : BLACK;
  }

  public RenderColor[] createRenderPalette(int pWhiteLevel) {
    transformColors();
    RenderColor res[] = new RenderColor[PALETTE_SIZE];
    for (int i = 0; i < res.length; i++) {
      res[i] = new RenderColor();
      RGBColor color = transformedColors.get(i);
      int r, g, b;
      if (color != null) {
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
      }
      else {
        RGBColor leftColor = null, rightColor = null;
        int leftIdx = i, rightIdx = i;
        while (leftIdx-- >= 0) {
          leftColor = transformedColors.get(leftIdx);
          if (leftColor != null) {
            break;
          }
        }
        while (rightIdx++ < PALETTE_SIZE) {
          rightColor = transformedColors.get(rightIdx);
          if (rightColor != null) {
            break;
          }
        }
        if (leftColor != null && rightColor != null) {
          r = leftColor.getRed() + (rightColor.getRed() - leftColor.getRed()) * (leftIdx - i) / (leftIdx - rightIdx);
          g = leftColor.getGreen() + (rightColor.getGreen() - leftColor.getGreen()) * (leftIdx - i) / (leftIdx - rightIdx);
          b = leftColor.getBlue() + (rightColor.getBlue() - leftColor.getBlue()) * (leftIdx - i) / (leftIdx - rightIdx);
        }
        else if (leftColor != null && rightColor == null) {
          r = leftColor.getRed();
          g = leftColor.getGreen();
          b = leftColor.getBlue();
        }
        else if (leftColor == null && rightColor != null) {
          r = rightColor.getRed();
          g = rightColor.getGreen();
          b = rightColor.getBlue();
        }
        else {
          r = BLACK.getRed();
          g = BLACK.getGreen();
          b = BLACK.getBlue();
        }
      }

      res[i].red = (r * pWhiteLevel) / 256;
      res[i].green = (g * pWhiteLevel) / 256;
      res[i].blue = (b * pWhiteLevel) / 256;
    }
    return res;
  }

  public int getSize() {
    return rawColors.size();
  }

  public int getModRed() {
    return modRed;
  }

  public void setModRed(int modRed) {
    this.modRed = modRed;
    modified = true;
  }

  public int getModGreen() {
    return modGreen;
  }

  public void setModGreen(int modGreen) {
    this.modGreen = modGreen;
    modified = true;
  }

  public int getModBlue() {
    return modBlue;
  }

  public void setModBlue(int modBlue) {
    this.modBlue = modBlue;
    modified = true;
  }

  public int getModShift() {
    return modShift;
  }

  public void setModShift(int modShift) {
    this.modShift = modShift;
    modified = true;
  }

  public int getModHue() {
    return modHue;
  }

  public void setModHue(int modHue) {
    this.modHue = modHue;
    modified = true;
  }

  public int getModContrast() {
    return modContrast;
  }

  public void setModContrast(int modContrast) {
    this.modContrast = modContrast;
    modified = true;
  }

  public int getModGamma() {
    return modGamma;
  }

  public void setModGamma(int modGamma) {
    this.modGamma = modGamma;
    modified = true;
  }

  public int getModBrightness() {
    return modBrightness;
  }

  public void setModBrightness(int modBrightness) {
    this.modBrightness = modBrightness;
    modified = true;
  }

  public int getModSwapRGB() {
    return modSwapRGB;
  }

  public void setModSwapRGB(int modSwapRGB) {
    this.modSwapRGB = modSwapRGB;
    modified = true;
  }

  public int getModSaturation() {
    return modSaturation;
  }

  public void setModSaturation(int modSaturation) {
    this.modSaturation = modSaturation;
    modified = true;
  }

  private void transformColors() {
    if (modified) {
      transformedColors.clear();
      for (int i = 0; i < PALETTE_SIZE; i++) {
        RGBColor color = rawColors.get(i);
        int idx = i + modShift;
        if (idx < 0) {
          idx += PALETTE_SIZE;
        }
        else if (idx >= PALETTE_SIZE) {
          idx -= PALETTE_SIZE;
        }
        transformedColors.put(idx, new RGBColor(color.getRed(), color.getGreen(), color.getBlue()));
      }
      if (modFrequency > 1) {
        Map<Integer, RGBColor> newTransformedColors = new HashMap<Integer, RGBColor>();
        for (int i = 0; i < PALETTE_SIZE; i++) {
          newTransformedColors.put(Integer.valueOf(i), transformedColors.get(Integer.valueOf(i)));
        }
        int n = PALETTE_SIZE / modFrequency;
        for (int j = 0; j < modFrequency; j++) {
          for (int i = 0; i < n; i++) {
            int idx = i + j * n;
            if (idx < PALETTE_SIZE) {
              newTransformedColors.put(idx, transformedColors.get(i * modFrequency));
            }
          }
        }
        transformedColors = newTransformedColors;
      }
      if (modBlur > 0) {
        Map<Integer, RGBColor> newTransformedColors = new HashMap<Integer, RGBColor>();
        for (int i = 0; i < PALETTE_SIZE; i++) {
          newTransformedColors.put(Integer.valueOf(i), transformedColors.get(Integer.valueOf(i)));
        }
        for (int i = 0; i < PALETTE_SIZE; i++) {
          int r = 0;
          int g = 0;
          int b = 0;
          int n = -1;
          for (int j = i - modBlur; j <= i + modBlur; j++) {
            n++;
            int k = (PALETTE_SIZE + j) % PALETTE_SIZE;
            if (k != i) {
              RGBColor color = transformedColors.get(Integer.valueOf(k));
              r += color.getRed();
              g += color.getGreen();
              b += color.getBlue();
            }
          }
          if (n != 0) {
            RGBColor color = new RGBColor(Tools.limitColor(r / n), Tools.limitColor(g / n), Tools.limitColor(b / n));
            newTransformedColors.put(Integer.valueOf(i), color);
          }
        }
        transformedColors = newTransformedColors;
      }
      SimpleImage img = new RGBPaletteRenderer().renderHorizPalette(transformedColors, PALETTE_SIZE, 1);
      if (modRed != 0 || modGreen != 0 || modBlue != 0 || modContrast != 0 || modGamma != 0 || modBrightness != 0 || modSaturation != 0) {
        BalancingTransformer bT = new BalancingTransformer();
        bT.setRed(modRed);
        bT.setGreen(modGreen);
        bT.setBlue(modBlue);
        bT.setContrast(modContrast);
        bT.setGamma(modGamma);
        bT.setBrightness(modBrightness);
        bT.setSaturation(modSaturation);
        bT.transformImage(img);
      }
      if (modHue != 0) {
        HSLTransformer hT = new HSLTransformer();
        hT.setHue(modHue);
        hT.transformImage(img);
      }
      if (modSwapRGB != 0) {
        SwapRGBTransformer sT = new SwapRGBTransformer();
        int maxValues = Mode.values().length;
        int idx = (int) ((double) Math.abs(modSwapRGB) / (double) 255.0 * (double) (maxValues - 1));
        sT.setMode(Mode.values()[idx]);
        sT.transformImage(img);
      }
      Pixel pixel = new Pixel();
      for (int i = 0; i < PALETTE_SIZE; i++) {
        RGBColor color = transformedColors.get(i);
        pixel.setARGBValue(img.getARGBValue(i, 0));
        color.setRed(pixel.r);
        color.setGreen(pixel.g);
        color.setBlue(pixel.b);
      }

      modified = false;
    }
  }

  public Map<Integer, RGBColor> getTransformedColors() {
    transformColors();
    return transformedColors;
  }

  @Override
  public RGBPalette makeCopy() {
    RGBPalette res = new RGBPalette();
    res.assign(this);
    return res;
  }

  @Override
  public void assign(RGBPalette pRGBPalette) {
    highestIdx = pRGBPalette.highestIdx;
    modified = pRGBPalette.modified;
    modRed = pRGBPalette.modRed;
    modGreen = pRGBPalette.modGreen;
    modBlue = pRGBPalette.modBlue;
    modShift = pRGBPalette.modShift;
    modHue = pRGBPalette.modHue;
    modContrast = pRGBPalette.modContrast;
    modGamma = pRGBPalette.modGamma;
    modBrightness = pRGBPalette.modBrightness;
    modSaturation = pRGBPalette.modSaturation;
    modSwapRGB = pRGBPalette.modSwapRGB;
    modFrequency = pRGBPalette.modFrequency;
    modBlur = pRGBPalette.modBlur;
    flam3Name = pRGBPalette.flam3Name;
    flam3Number = pRGBPalette.flam3Number;

    rawColors.clear();
    for (Integer key : pRGBPalette.rawColors.keySet()) {
      RGBColor newColor = pRGBPalette.rawColors.get(key).makeCopy();
      rawColors.put(key, newColor);
    }
    transformedColors.clear();
    for (Integer key : pRGBPalette.transformedColors.keySet()) {
      RGBColor newColor = pRGBPalette.transformedColors.get(key).makeCopy();
      transformedColors.put(key, newColor);
    }
  }

  public String getFlam3Number() {
    return flam3Number;
  }

  public void setFlam3Number(String flam3Number) {
    this.flam3Number = flam3Number;
  }

  public String getFlam3Name() {
    return flam3Name;
  }

  public void setFlam3Name(String flam3Name) {
    this.flam3Name = flam3Name;
  }

  @Override
  public String toString() {
    if (flam3Number != null && flam3Number.length() > 0) {
      if (flam3Name != null && flam3Name.length() > 0) {
        return flam3Number + " - " + flam3Name;
      }
      else {
        return flam3Name;
      }
    }
    else if (flam3Name != null && flam3Name.length() > 0) {
      return flam3Name;
    }
    else {
      return super.toString();
    }
  }

  @Override
  public boolean isEqual(RGBPalette pSrc) {
    // flam3Number and flam3Name can not be changed
    if ((modRed != pSrc.modRed) || (modGreen != pSrc.modGreen) || (modBlue != pSrc.modBlue) ||
        (modShift != pSrc.modShift) || (modHue != pSrc.modHue) || (modContrast != pSrc.modContrast) ||
        (modGamma != pSrc.modGamma) || (modBrightness != pSrc.modBrightness) || (modSaturation != pSrc.modSaturation) ||
        (modSwapRGB != pSrc.modSwapRGB) || (modFrequency != pSrc.modFrequency) || (modBlur != pSrc.modBlur) ||
        (rawColors.size() != pSrc.rawColors.size())) {
      return false;
    }
    for (int i = 0; i < PALETTE_SIZE; i++) {
      RGBColor color = rawColors.get(i);
      RGBColor srcColor = pSrc.rawColors.get(i);
      if (!color.isEqual(srcColor)) {
        return false;
      }
    }
    return true;
  }

  public void sort() {
    List<RGBColor> colors = new ArrayList<RGBColor>();
    for (int i = 0; i < PALETTE_SIZE; i++) {
      colors.add(rawColors.get(i));
    }
    Collections.sort(colors);
    rawColors.clear();
    for (int i = 0; i < PALETTE_SIZE; i++) {
      rawColors.put(Integer.valueOf(i), colors.get(i));
    }
    modified = true;
  }

  public void negativeColors() {
    for (int i = 0; i < PALETTE_SIZE; i++) {
      RGBColor color = rawColors.get(Integer.valueOf(i));
      if (color != null) {
        color.setRed(255 - color.getRed());
        color.setGreen(255 - color.getGreen());
        color.setBlue(255 - color.getBlue());
      }
    }
    modified = true;
  }

  public void reverseColors() {
    Map<Integer, RGBColor> newRawColors = new HashMap<Integer, RGBColor>();
    for (int i = 0; i < PALETTE_SIZE; i++) {
      RGBColor color = rawColors.get(Integer.valueOf(PALETTE_SIZE - 1 - i));
      if (color != null) {
        newRawColors.put(Integer.valueOf(i), color);
      }
    }
    rawColors = newRawColors;
    modified = true;
  }

  public int getModFrequency() {
    return modFrequency;
  }

  public void setModFrequency(int modFrequency) {
    this.modFrequency = modFrequency;
    modified = true;
  }

  public int getModBlur() {
    return modBlur;
  }

  public void setModBlur(int modBlur) {
    this.modBlur = modBlur;
    modified = true;
  }

}
