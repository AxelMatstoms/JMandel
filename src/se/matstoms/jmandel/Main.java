package se.matstoms.jmandel;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        //BufferedImage buf = Mandel.makeMandel(new Complex(-0.725, -0.5), new Complex(-0.625, -0.4), 4096, 1024); //seahorse
        //BufferedImage buf = Mandel.makeMandel(new Complex(-2, -2), new Complex(2, 2), 4096, 1024); //normal
        //BufferedImage buf = Mandel.makeMandel(new Complex(-0.15314526367187495, 1.0394859483506944),
        //        new Complex(-0.15271362304687494, 1.039917588975889756944), 4096, 1024); //minimandel
        //BufferedImage buf = Mandel.makeMandel(new Complex(-0.7410794857313867, -0.15787222531884906),
        //        new Complex(-0.7410794853356111, -0.15787222511024226), 4096, 8192); //island
        //BufferedImage buf = Mandel.makeMandel(new Complex(-0.06369594779375298, -0.681598131482348),
        //        new Complex(-0.03686424669341982, -0.6665052996134107), 4096, 8192); //curly
	BufferedImage buf = Mandel.makeMandel(new Complex(-2, -2), new Complex(2, 2), 8192, 2048); //normal
        try {
            File output = new File("output2.png");
            ImageIO.write(buf, "png", output);
            System.out.println("done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
    }
}