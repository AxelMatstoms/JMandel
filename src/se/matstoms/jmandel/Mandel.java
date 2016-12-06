package se.matstoms.jmandel;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Mandel {
    
    public static BufferedImage makeMandel(Complex min, Complex max, int pxlWidth, int iter) {
        double realWidth = max.real() - min.real();
        double realHeight = max.imag() - min.imag();
        int pxlHeight = (int) ((realHeight / realWidth) * (double) pxlWidth);

        BufferedImage buf = new BufferedImage(pxlWidth, pxlHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d =  buf.createGraphics();

        double log2 = Math.log((double) 2.0);
        int bigNumber = 1 << 24;
        for (int x = 0; x < pxlWidth; x++) {
            for (int y = 0; y < pxlHeight; y++) {
                Complex c = new Complex(((double) x / (double) pxlWidth) * realWidth,
                        ((double) y / (double) pxlHeight) * realHeight);
                c = c.add(min);
                Complex z = new Complex(0.0, 0.0);
                int i;
                if (!Mandel.inApprox(c.real(), c.imag())) {
                    for (i = 0; (i < iter && z.abs2() < bigNumber); i++) {
                        z = z.square().add(c);
                    }
                } else {
                    i = iter;
                }
                
                if (i == iter) {
                    g2d.setColor(Color.BLACK);
                } else {
                    double logZn = Math.log(z.abs2()) / 2f;
                    double nu = Math.log(logZn / log2) / log2;
                    double newI = i + 1 - nu;
                    newI = Math.log(newI) / log2;
                    
                    int r = 0,
                        g = 0,
                        b = 0;
                    
                    if (newI < 64) {
                        b = (int) (128 + 2 * newI);
                        g = (int) (4 * newI);
                        b = (int) (4 * newI);
                    } else if (newI < 128) {
                        newI -= 64;
                        
                    }
                    
                    
                    /*float hue = (float) ((newI % 6f) / 6f);
                    g2d.setColor(Color.getHSBColor(hue, 1, 1));*/
                }


                g2d.fillRect(x, y, 1, 1);
            }
        }

        return buf;
    }

    private static boolean inApprox(double x, double y) {
        /*Shape[] approxMandel = new Shape[]{
                new Ellipse2D.Double(-1.250f, -0.250f, 0.250f, 0.250f),
                new Rectangle2D.Double(-0.500f, -0.515f, 0.725f, 1.030f)
        };*/
        //boolean ret = (Mandel.approxMandel[0].contains(x,y) || Mandel.approxMandel[1].contains(x, y));
        double q = (x - 0.25) * (x - 0.25) + y * y;
        
        boolean bulb = ((x + 1) * (x + 1) + y * y < 0.0625);
        
        boolean cardioid = (q * (q + (x - 0.25)) < 0.25 * y * y);
        
        return !(!bulb && !cardioid);
    }
}