package Engine;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureLoader {
	
	public static BufferedImage grassTexture;
	public static BufferedImage wallTexture;
	public static BufferedImage stoneTexture;
	public static BufferedImage woodFloorTexture;
	public static void init() {
		try {
		    grassTexture = toCompatibleImage(ImageIO.read(new File("grass.jpg")));
		    wallTexture = toCompatibleImage(ImageIO.read(new File("wood.jpg")));
		    stoneTexture = toCompatibleImage(ImageIO.read(new File("stone.jpg")));
		   	woodFloorTexture = toCompatibleImage(ImageIO.read(new File("woodFloor.jpg")));
			//grassTexture=ImageIO.read(new File("grass.jpg"));
		} catch (IOException e) {
		}	
		System.out.println("Textures initialized");
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
}
