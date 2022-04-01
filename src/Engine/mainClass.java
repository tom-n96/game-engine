package Engine;
import java.awt.Graphics;
import World.Environment;
import java.awt.Graphics2D;

import World.EnvironmentThread;

public class mainClass {
	public static GraphicsThread g;
	public static EnvironmentThread e;
	public static void main(String [] args) {
		SpriteLoader.initialize();
		TextureLoader.init();
		Environment env=new Environment(420l);
		//System.setProperty("sun.java2d.opengl","True");
		
		g = new GraphicsThread("Graphics",env);
		g.start();
		while(true) {
			env.updateEnvironment();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//e = new EnvironmentThread("Env",env);

	//	e.start();


	}
}
  