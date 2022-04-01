package SpriteGenerator;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteGenerator {
	private static final GraphicsConfiguration GFX_CONFIG = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	public static int forwardFrames = 7;
	public static int backwardFrames=7;
	public static int sideFrames=9;
	public static BufferedImage[][] CreateNPC(int haircode,int shirtcode, int pantscode,int footcode) {
		BufferedImage base[]=new BufferedImage[backwardFrames];
		BufferedImage baseforward[]=new BufferedImage[forwardFrames];
		BufferedImage baseright[]=new BufferedImage[sideFrames];
		//init
		try {
			   // grassTexture = toCompatibleImage(ImageIO.read(new File("grass.jpg")));
				for(int i =0; i < base.length;i++) {

					base[i]=toCompatibleImage(ImageIO.read(new File("anims/player/"+i+".png")));

				}
				for(int i =0; i < baseforward.length;i++) {

					baseforward[i]=toCompatibleImage(ImageIO.read(new File("anims/playerforward/"+i+".png")));

				}
				for(int i =0; i < baseright.length;i++) {

					baseright[i]=toCompatibleImage(ImageIO.read(new File("anims/playerright/"+i+".png")));
					//System.out.println("Test1"+i);

				}
		} catch (IOException e) {
			System.out.println("Broke");
	}	
	System.out.println("Base Sprites Loaded in sprite generator");
	
	
	
	//hair

	BufferedImage hair[]=new BufferedImage[backwardFrames];
	BufferedImage hairforward[]=new BufferedImage[forwardFrames];
	BufferedImage hairright[]=new BufferedImage[sideFrames];
	
	try {
		   // grassTexture = toCompatibleImage(ImageIO.read(new File("grass.jpg")));
			for(int i =0; i < base.length;i++) {

				hair[i]=toCompatibleImage(ImageIO.read(new File("anims/hair/"+haircode+"/back/"+i+".png")));

			}
			for(int i =0; i < baseforward.length;i++) {

				hairforward[i]=toCompatibleImage(ImageIO.read(new File("anims/hair/"+haircode+"/forward/"+i+".png")));

			}
			for(int i =0; i < baseright.length;i++) {

				hairright[i]=toCompatibleImage(ImageIO.read(new File("anims/hair/"+haircode+"/right/"+i+".png")));
				//System.out.println("testoo "+i);

			}
	} catch (IOException e) {
		System.out.println("Broke");
}	
	System.out.println("Hair Sprites loaded in sprite generator");
	if(hair[0]!=null) {
	
		for(int i =0; i < hair.length;i++) {
			base[i]=overlayImages(base[i],hair[i]);
		}
		for(int i =0; i < hairforward.length;i++) {
			baseforward[i]=overlayImages(baseforward[i],hairforward[i]);
		}
		for(int i =0; i < hairright.length;i++) {
			baseright[i]=overlayImages(baseright[i],hairright[i]);
			System.out.println("test"+i);
		}
	}
	//return
	BufferedImage baseleft[]=new BufferedImage[sideFrames];
	for(int i = 0; i < sideFrames;i++) {
		baseleft[i]=flip(baseright[i]);
	}
	BufferedImage toReturn [][] = {base,baseforward,baseright,baseleft};
	return toReturn;
	}
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
	public static BufferedImage overlayImages(BufferedImage imgA,BufferedImage imgB) {

		    if (imgA.getWidth() == imgB.getWidth() && imgA.getHeight() == imgB.getHeight()) 
		    {
		        float alpha = 1f;
		        int compositeRule = AlphaComposite.SRC_OVER;
		        AlphaComposite ac;
		        int imgW = imgA.getWidth();
		        int imgH = imgA.getHeight();
		        BufferedImage overlay = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_ARGB);
		        Graphics2D g = overlay.createGraphics();
		        ac = AlphaComposite.getInstance(compositeRule, alpha);
		        g.drawImage(imgA,0,0,null);
		        g.setComposite(ac);
		        g.drawImage(imgB,0,0,null);
		        g.setComposite(ac);
		        return overlay;
		        
		    }
		    else
		    {
		        System.err.println("dimension mismatch ");
		        return null;
		    }
		}

	}

