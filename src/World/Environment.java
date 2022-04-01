package World;
import java.awt.Graphics;
import WindowManager.wInventoryBar;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;

import Engine.Config;
import Engine.mainClass;
import WindowManager.Interface;
import WindowManager.wInventoryBar;

import java.awt.Color;

public class Environment {
	
    private long frameCounter=0;
    private long lastFrame=0;
    private long lastCheck=System.currentTimeMillis();
   private long lastStartFrame=System.currentTimeMillis();
   public long dt=0;
	
	//private WindowHandler j;
	private long count=0;

    private long seed;
    private Random r;
    public double initialQuantity; 
    public double totalQuantity;
    public World world;
    public Interface interf; // base interface
    public double cameraOffsetX, cameraOffsetY;

	public Environment(Long seed) {


		world= new World(this);
		world.initialize();

		this.seed=seed;
		r=new Random(seed);
		interf=new Interface("Base",12,0,0,this);
		interf.widgets.add(new wInventoryBar(420,"InventoryBar",10,65,interf,this));

	}


	//public WindowHandler getJFrame() {
	//	return j;
	//}


	public void updateEnvironment() {
		dt=System.currentTimeMillis()-lastStartFrame;
		//dt=System.currentTimeMillis()-mainClass.g.w.lastStartFrame;
		//System.out.println(dt);

		//if(frameCounter<=60)
			world.update();
		
		if(count%60000==0) {

		}
		totalQuantity=0;
		if(mainClass.g.w.mousePressed) {
			Point mouse = MouseInfo.getPointerInfo().getLocation(); 
			
			inputPlayer(mouse.x- mainClass.g.w.getLocation().x, mouse.y- mainClass.g.w.getLocation().y);
			int xDir,yDir;
			if(world.player.getVelocityX()>0)
				xDir=-1;
			else
				xDir=1;
			if(world.player.getVelocityY()>0)
				yDir=-1;
			else
				yDir=1;
			
			//world.player.accelerate((Config.SLOWDOWN_SPEED*xDir), (Config.SLOWDOWN_SPEED*yDir));
		}

		//for(int i =0; i < ents.length;i++) {
			//ents[i].checkCollision(ents);
			//ents[i].checkTileCollision(world.collidables);
		//}
		//world.player.checkCollision(ents);
		world.player.checkTileCollision(world.collidables);
			frameCounter++;

			if(System.currentTimeMillis()-lastCheck>=1000) {
				//System.out.println(lastFrame);
				lastFrame=frameCounter;
				frameCounter=0;
				lastCheck=System.currentTimeMillis();
		
			}
		count++;

		lastStartFrame=System.currentTimeMillis();
	}
	public void inputPlayer(int mouseX,int mouseY) {
		double movementScalarX=.0005;
		double movementScalarY=.0005;
		//world.player.accelerate(((mouseX-(mainClass.g.getJFrame().getWidth()/2.0))*movementScalarX),(mouseY-(mainClass.g.getJFrame().getHeight()/2.0))*movementScalarY);
		world.player.velocityX=(float) ((mouseX-(mainClass.g.getJFrame().getWidth()/2.0))*movementScalarX);
		world.player.velocityY=(float) ((mouseY-(mainClass.g.getJFrame().getHeight()/2.0))*movementScalarY);
		//System.out.println("no");
	}
	public void renderEnvironment(Graphics g) {
		world.render(g,cameraOffsetX,cameraOffsetY);
		world.renderEntities(g,cameraOffsetX,cameraOffsetY);
		if(Config.debug) {
			g.setColor(Color.BLUE);
			g.drawString("FPS: "+mainClass.g.w.lastFrame+" Updates: " +lastFrame+" offsetX: "+(int)cameraOffsetX+" offsetY: "+(int)cameraOffsetY+" CoordX: "+(int)world.player.x+" CoordY: "+(int)world.player.y, 30, 60);
		}
		g.setColor(Color.WHITE);
		g.fillOval(0, 0, 4, 4);
		if(Config.debug) {
			g.setColor(Color.BLACK);
			g.drawLine((int)(world.player.x+cameraOffsetX), (int)(world.player.y+cameraOffsetY), (int)((world.player.x+cameraOffsetX)+(1000*world.player.getVelocityX())), (int)((world.player.y+cameraOffsetY)+(1000*world.player.getVelocityY())));
		}
		interf.Render(g);
	
	}


	public void stopPlayer() {
		world.player.stop();
		
	}

}
