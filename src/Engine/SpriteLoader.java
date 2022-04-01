package Engine;
import java.awt.Graphics2D;
import SpriteGenerator.*;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteLoader {
	
	public static BufferedImage player[]=new BufferedImage[7];
	public static BufferedImage playerforward[]=new BufferedImage[7];
	public static BufferedImage playerright[]=new BufferedImage[9];
	public static BufferedImage playerleft[]=new BufferedImage[9];
	public static BufferedImage egg[]=new BufferedImage[1];
	public static BufferedImage michelle[]=new BufferedImage[1];
	public static void initialize() {
		try {
			BufferedImage playerImport[][] = SpriteGenerator.CreateNPC(0,0,0,0);
			//order is base,baseforward,baseright,baseleft
			player=playerImport[0];
			playerforward=playerImport[1];
			playerright=playerImport[2];
			playerleft=playerImport[3];
			for(int i =0; i < egg.length;i++) {
				egg[i]=ImageIO.read(new File("anims/egg/"+i+".png"));
			}
			for(int i =0; i < michelle.length;i++) {

				michelle[i]=toCompatibleImage(ImageIO.read(new File("anims/michelle/"+i+".png")));

			}
			} catch (IOException e) {
				System.out.println("Broke");
		}	
		System.out.println("Sprites Loaded init");
	}
	private static final GraphicsConfiguration GFX_CONFIG = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

	public static BufferedImage toCompatibleImage(final BufferedImage image) {
	    /*
	     * if image is already compatible and optimized for current system settings, simply return it
	     */
	    if (image.getColorModel().equals(GFX_CONFIG.getColorModel())) {
	        return image;
	    }

	    // image is not optimized, so create a new image that is
	    final BufferedImage new_image = GFX_CONFIG.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());

	    // get the graphics context of the new image to draw the old image on
	    final Graphics2D g2d = (Graphics2D) new_image.getGraphics();

	    // actually draw the image and dispose of context no longer needed
	    g2d.drawImage(image, 0, 0, null);
	    g2d.dispose();

	    // return the new optimized image
	    return new_image;
	}
	   public static BufferedImage flip(BufferedImage sprite){
	        BufferedImage img = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
	        for(int xx = sprite.getWidth()-1;xx>0;xx--){
	            for(int yy = 0;yy < sprite.getHeight();yy++){
	                img.setRGB(sprite.getWidth()-xx, yy, sprite.getRGB(xx, yy));
	            }
	        }
	    return img;
	}
}
