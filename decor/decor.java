package decor;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.Point;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Decor {
	BufferedImage image;
	int w,h;

   public static void main(String[] args) throws IOException {

   		Decor im = new Decor("test.png", 400, 400);

         int[][] result = convertTo2D(im.image);
         System.out.println(result[0].length);

   }

   public Decor(String s, int w, int h) throws IOException
   {
   		image = ImageIO.read(Decor.class.getResource(s));
   		image = getScaledImage(image, w, h);
   		this.w = w;
   		this.h = h;
   }

   private static int[][] convertTo2D(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      int[][] result = new int[height][width];

      for (int row = 0; row < height; row++) {
         for (int col = 0; col < width; col++) {
            result[row][col] = image.getRGB(col, row);
         }
      }

      return result;
   }

   private BufferedImage getScaledImage(BufferedImage srcImg, int w, int h){
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
    Graphics2D g2 = resizedImg.createGraphics();
    // g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();
    return resizedImg;
}

   public ArrayList<Point> toPointArray(int min, int max)
   {
   		int width = image.getWidth(), height = image.getHeight(), x,y;
   		int [][] result = convertTo2D(image);
   		System.out.println(height +" - "+ width);
   		ArrayList<Point> res = new ArrayList<Point>();
   		for(int i=0; i<h; i++)
   		{
   			for(int j=0; j<w; j++)
   			{

   				// x = 10 * ((int) Math.floor(i * ((float) height/600)));
   				// y = 10 * ((int) Math.floor(j * ((float) width/600)));
   				x = i;
   				y = j;
   				int a = result[x][y];
   				Color c = new Color(a);
   				if(c.getRed() < max && c.getRed() > min && a < -2)
   				{
   					if(!res.contains(new Point(10*y,10*x))){res.add(new Point(10*y, 10*x));}
   				}
   			}
   		}
   		return res;
   }
}