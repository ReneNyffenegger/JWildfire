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
import static org.jwildfire.create.tina.base.Constants.AVAILABILITY_JWILDFIRE;

import org.jwildfire.create.tina.base.XForm;
import org.jwildfire.create.tina.base.XYZPoint;

public class CircusFunc extends VariationFunc {
  private static final long serialVersionUID = 1L;

  private static final String PARAM_SCALE = "scale";
  private static final String[] paramNames = { PARAM_SCALE };

  private double scale = 1.0;

  @Override
  public void transform(FlameTransformationContext pContext, XForm pXForm, XYZPoint pAffineTP, XYZPoint pVarTP, double pAmount) {
    /* circus from Michael Faber, http://michaelfaber.deviantart.com/art/The-Lost-Variations-258913970 */
    double r = sqrt(pAffineTP.x * pAffineTP.x + pAffineTP.y * pAffineTP.y);
    double a = atan2(pAffineTP.y, pAffineTP.x);
    double s = sin(a);
    double c = cos(a);

    if (r <= 1.0)
      r *= scale;
    else
      r *= scale_1;

    pVarTP.x += pAmount * r * c;
    pVarTP.y += pAmount * r * s;

    if (pContext.isPreserveZCoordinate()) {
      pVarTP.z += pAmount * pAffineTP.z;
    }
  }

  @Override
  public String[] getParameterNames() {
    return paramNames;
  }

  @Override
  public Object[] getParameterValues() {
    return new Object[] { scale };
  }

  @Override
  public void setParameter(String pName, double pValue) {
    if (PARAM_SCALE.equalsIgnoreCase(pName))
      scale = pValue;
    else
      throw new IllegalArgumentException(pName);
  }

  @Override
  public String getName() {
    return "circus";
  }

  @Override
  public int getAvailability() {
    return AVAILABILITY_JWILDFIRE;
  }

  private double scale_1;

  @Override
  public void init(FlameTransformationContext pContext, XForm pXForm, double pAmount) {
    scale_1 = 1.0 / scale;
  }

}
