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
package org.jwildfire.envelope;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import org.jwildfire.base.Tools;

public class EnvelopePanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private static final Color BG_COLOR = new Color(0, 0, 0);
  private static final Color GRID_COLOR = new Color(55, 59, 65);
  private static final Color LABEL_COLOR = new Color(174, 179, 186);
  private static final Color ENVELOPE_COLOR = new Color(217, 219, 223);

  private static final String LBL_FONT_NAME = "Arial";
  private static final int LBL_FONT_SIZE = 10;

  private Envelope envelope;

  public EnvelopePanel() {
    super();
  }

  @Override
  public void paintComponent(Graphics g) {
    EnvelopeView envelopeView = new EnvelopeView(this);
    g.setColor(BG_COLOR);
    g.fillRect(0, 0, envelopeView.getWidth(), envelopeView.getHeight());
    if (envelope != null) {
      drawGrid(g, envelopeView);
      drawLines(g, envelopeView);
      drawPoints(g, envelopeView);
    }
  }

  private void drawGrid(Graphics g, EnvelopeView pEnvelopeView) {
    g.setColor(GRID_COLOR);
    int xnum = 20, ynum = 7;
    int fl = 0;

    /* lines */
    {
      double dx = envelope.getViewXMax() - envelope.getViewXMin();
      double step = Tools.FTOI(dx / (double) xnum);
      if (step < 1.0)
        step = 1.0;
      int div = Tools.FTOI((double) (envelope.getViewXMin() / step));
      double x = (double) div * step;
      do {
        double fx = pEnvelopeView.getEnvelopeXScale() * x - pEnvelopeView.getEnvelopeXTrans();
        int dxl = Tools.FTOI(fx);
        if ((dxl >= pEnvelopeView.getEnvelopeLeft()) && (dxl <= pEnvelopeView.getEnvelopeRight())) {
          g.drawLine(dxl, pEnvelopeView.getEnvelopeTop(), dxl, pEnvelopeView.getEnvelopeBottom());
        }
        x += step;
      }
      while (x < envelope.getViewXMax());
    }

    {
      double dy = envelope.getViewYMax() - envelope.getViewYMin();
      double step = Tools.FTOI(dy / (double) ynum);
      if (step < 1.0) {
        step = dy / (double) ynum;
        if (step >= 0.5) {
          step = 0.5;
          fl = 1;
        }
        else {
          step = 0.25;
          fl = 2;
        }
      }
      int div = Tools.FTOI((double) (envelope.getViewYMin() / step));
      double y = (double) div * step;

      do {
        double fy = pEnvelopeView.getEnvelopeYScale() * y - pEnvelopeView.getEnvelopeYTrans();
        int dyl = Tools.FTOI(fy);
        if ((dyl >= pEnvelopeView.getEnvelopeTop()) && (dyl <= pEnvelopeView.getEnvelopeBottom())) {
          g.drawLine(pEnvelopeView.getEnvelopeLeft(), dyl, pEnvelopeView.getEnvelopeRight(), dyl);
        }
        y += step;
      }
      while (y < envelope.getViewYMax());
    }
    /* tick-labels */
    g.setColor(LABEL_COLOR);
    Font font = new Font(LBL_FONT_NAME, Font.PLAIN, LBL_FONT_SIZE);
    g.setFont(font);
    FontMetrics fm = g.getFontMetrics();
    int yFontOffset = fm.getMaxAscent();
    FontRenderContext frc = fm.getFontRenderContext();
    {
      double dx = envelope.getViewXMax() - envelope.getViewXMin();
      double step = Tools.FTOI(dx / (double) xnum);
      if (step < 1.0)
        step = 1.0;
      int div = Tools.FTOI((double) (envelope.getViewXMin() / step));
      double x = (double) div * step;
      int cnt = 0;
      do {
        double fx = pEnvelopeView.getEnvelopeXScale() * x - pEnvelopeView.getEnvelopeXTrans();
        int dxl = Tools.FTOI(fx);
        cnt++;
        if (cnt % 2 == 0) {
          String hs = String.valueOf(Tools.FTOI(x));
          Rectangle2D rect = font.getStringBounds(hs, frc);
          int tw = (int) (rect.getWidth() + 0.5);
          int leftEdge = dxl - tw / 2;
          if ((leftEdge >= pEnvelopeView.getEnvelopeLeft())
              && ((leftEdge + tw) <= pEnvelopeView.getEnvelopeRight()))
          {
            int topEdge = pEnvelopeView.getEnvelopeBottom() - yFontOffset;
            g.drawString(hs, leftEdge, topEdge);
          }
        }
        x += step;
      }
      while (x < envelope.getViewXMax());
    }
    {
      double dx = envelope.getViewYMax() - envelope.getViewYMin();
      double step = Tools.FTOI(dx / (double) ynum);
      if (step < 1.0) {
        step = dx / (double) ynum;
        if (step >= 0.5) {
          step = 0.5;
          fl = 1;
        }
        else {
          step = 0.25;
          fl = 2;
        }
      }
      int div = Tools.FTOI((double) (envelope.getViewYMin() / step));
      double x = (double) div * step;
      int cnt = 0;
      do {
        double fx = pEnvelopeView.getEnvelopeYScale() * x - pEnvelopeView.getEnvelopeYTrans();
        int dxl = Tools.FTOI(fx);
        cnt++;
        if (cnt % 2 == 0) {
          String hs;
          if (fl == 0)
            hs = String.valueOf(Tools.FTOI(x));
          else
            hs = String.valueOf(x);
          int topEdge = dxl - (yFontOffset / 2) - 1;
          if (topEdge >= pEnvelopeView.getEnvelopeTop()) {
            int leftEdge = pEnvelopeView.getEnvelopeLeft() + 3;
            g.drawString(hs, leftEdge, topEdge);
          }
        }
        x += step;
      }
      while (x < envelope.getViewYMax());
    }
  }

  private void drawLines(Graphics g, EnvelopeView pEnvelopeView) {
    if (envelope.size() < 2)
      return;
    g.setColor(ENVELOPE_COLOR);

    int subdiv = Interpolation.calcSubDivPRV(envelope.getX(), envelope.size());
    Interpolation interpolationX, interpolationY;
    if (envelope.size() > 2) {
      switch (envelope.getInterpolation()) {
        case SPLINE:
          interpolationX = new SplineInterpolation();
          interpolationY = new SplineInterpolation();
          break;
        case BEZIER:
          interpolationX = new BezierInterpolation();
          interpolationY = new BezierInterpolation();
          break;
        default:
          interpolationX = new LinearInterpolation();
          interpolationY = new LinearInterpolation();
          break;
      }
    }
    else {
      interpolationX = new LinearInterpolation();
      interpolationY = new LinearInterpolation();
    }
    interpolationX.setSrc(envelope.getX());
    interpolationX.setSnum(envelope.size());
    interpolationX.setSubdiv(subdiv);
    interpolationX.interpolate();
    interpolationY.setSrc(envelope.getY());
    interpolationY.setSnum(envelope.size());
    interpolationY.setSubdiv(subdiv);
    interpolationY.interpolate();

    if (interpolationX.getDnum() != interpolationY.getDnum())
      throw new IllegalStateException();

    double[] sx = interpolationX.getDest();
    double[] sy = interpolationY.getDest();
    int sCount = interpolationY.getDnum();
    double x = sx[0];
    double y = sy[0];
    double fx = pEnvelopeView.getEnvelopeXScale() * x - pEnvelopeView.getEnvelopeXTrans();
    double fy = pEnvelopeView.getEnvelopeYScale() * y - pEnvelopeView.getEnvelopeYTrans();
    int dxl = Tools.FTOI(fx);
    int dyl = Tools.FTOI(fy);

    for (int i = 1; i < sCount; i++) {
      x = (double) sx[i];
      y = (double) sy[i];
      fx = pEnvelopeView.getEnvelopeXScale() * x - pEnvelopeView.getEnvelopeXTrans();
      fy = pEnvelopeView.getEnvelopeYScale() * y - pEnvelopeView.getEnvelopeYTrans();
      int dx = Tools.FTOI(fx);
      int dy = Tools.FTOI(fy);
      if ((dx >= pEnvelopeView.getEnvelopeLeft()) && (dx <= pEnvelopeView.getEnvelopeRight())
          && (dxl >= pEnvelopeView.getEnvelopeLeft()) && (dxl <= pEnvelopeView.getEnvelopeRight())
          && (dy >= pEnvelopeView.getEnvelopeTop()) && (dy <= pEnvelopeView.getEnvelopeBottom())
          && (dyl >= pEnvelopeView.getEnvelopeTop()) && (dyl <= pEnvelopeView.getEnvelopeBottom()))
      {
        g.drawLine(dxl, dyl, dx, dy);
        //if(i>1) (void)WritePixel(Envelope_RastPort,dxl,dyl);
      }
      dxl = dx;
      dyl = dy;
    }
  }

  private void drawPoints(Graphics g, EnvelopeView pEnvelopeView) {
    g.setColor(ENVELOPE_COLOR);

    final int envelopeRadius = 3;

    for (int i = 0; i < envelope.size(); i++) {
      double x = envelope.getX()[i];
      double y = envelope.getY()[i];
      double fx = pEnvelopeView.getEnvelopeXScale() * x - pEnvelopeView.getEnvelopeXTrans();
      double fy = pEnvelopeView.getEnvelopeYScale() * y - pEnvelopeView.getEnvelopeYTrans();
      int dx = Tools.FTOI(fx);
      int dy = Tools.FTOI(fy);
      if ((dx >= pEnvelopeView.getEnvelopeLeft()) && (dx <= pEnvelopeView.getEnvelopeRight())
          && (dy >= pEnvelopeView.getEnvelopeTop()) && (dy <= pEnvelopeView.getEnvelopeBottom()))
      {
        int left = dx - envelopeRadius;
        if (left < pEnvelopeView.getEnvelopeLeft())
          left = pEnvelopeView.getEnvelopeLeft();
        int right = dx + envelopeRadius;
        if (right > pEnvelopeView.getEnvelopeRight())
          right = pEnvelopeView.getEnvelopeRight();
        int top = dy - envelopeRadius;
        if (top < pEnvelopeView.getEnvelopeTop())
          top = pEnvelopeView.getEnvelopeTop();
        int bottom = dy + envelopeRadius;
        if (bottom > pEnvelopeView.getEnvelopeBottom())
          bottom = pEnvelopeView.getEnvelopeBottom();
        g.fillRect(left, top, right - left + 1, bottom - top + 1);
        if (i == envelope.getSelectedIdx()) {
          left = dx - envelopeRadius - 2;
          if (left < pEnvelopeView.getEnvelopeLeft())
            left = pEnvelopeView.getEnvelopeLeft();
          right = dx + envelopeRadius + 2;
          if (right > pEnvelopeView.getEnvelopeRight())
            right = pEnvelopeView.getEnvelopeRight();
          top = dy - envelopeRadius - 2;
          if (top < pEnvelopeView.getEnvelopeTop())
            top = pEnvelopeView.getEnvelopeTop();
          bottom = dy + envelopeRadius + 2;
          if (bottom > pEnvelopeView.getEnvelopeBottom())
            bottom = pEnvelopeView.getEnvelopeBottom();
          g.drawRect(left, top, right - left, bottom - top);
        }
      }
    }
  }

  public void setEnvelope(Envelope envelope) {
    this.envelope = envelope;
  }

  protected Envelope getEnvelope() {
    return envelope;
  }

}
