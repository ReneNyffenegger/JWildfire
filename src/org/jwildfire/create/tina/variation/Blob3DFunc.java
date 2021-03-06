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
package org.jwildfire.create.tina.variation;

import static org.jwildfire.base.mathlib.MathLib.atan2;
import static org.jwildfire.base.mathlib.MathLib.cos;
import static org.jwildfire.base.mathlib.MathLib.sin;
import static org.jwildfire.base.mathlib.MathLib.sqrt;
import static org.jwildfire.create.tina.base.Constants.AVAILABILITY_CUDA;
import static org.jwildfire.create.tina.base.Constants.AVAILABILITY_JWILDFIRE;

import org.jwildfire.create.tina.base.XForm;
import org.jwildfire.create.tina.base.XYZPoint;

public class Blob3DFunc extends VariationFunc {
  private static final long serialVersionUID = 1L;

  private static final String PARAM_LOW = "low";
  private static final String PARAM_HIGH = "high";
  private static final String PARAM_WAVES = "waves";
  private static final String[] paramNames = { PARAM_LOW, PARAM_HIGH, PARAM_WAVES };

  private double low = 0.3;
  private double high = 1.2;
  private double waves = 6;

  @Override
  public void transform(FlameTransformationContext pContext, XForm pXForm, XYZPoint pAffineTP, XYZPoint pVarTP, double pAmount) {
    double a = atan2(pAffineTP.x, pAffineTP.y);
    double r = sqrt(pAffineTP.x * pAffineTP.x + pAffineTP.y * pAffineTP.y);
    r = r * (low + (high - low) * (0.5 + 0.5 * sin(waves * a)));
    double nx = sin(a) * r;
    double ny = cos(a) * r;
    double nz = sin(waves * a) * r;

    pVarTP.x += pAmount * nx;
    pVarTP.y += pAmount * ny;
    pVarTP.z += pAmount * nz;
  }

  @Override
  public String[] getParameterNames() {
    return paramNames;
  }

  @Override
  public Object[] getParameterValues() {
    return new Object[] { low, high, waves };
  }

  @Override
  public void setParameter(String pName, double pValue) {
    if (PARAM_LOW.equalsIgnoreCase(pName))
      low = pValue;
    else if (PARAM_HIGH.equalsIgnoreCase(pName))
      high = pValue;
    else if (PARAM_WAVES.equalsIgnoreCase(pName))
      waves = pValue;
    else
      throw new IllegalArgumentException(pName);
  }

  @Override
  public String getName() {
    return "blob3D";
  }

  @Override
  public int getAvailability() {
    return AVAILABILITY_JWILDFIRE | AVAILABILITY_CUDA;
  }

}
