package World;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import Engine.Config;
import Engine.mainClass;

public class Tile extends JComponent{
	public Color c;
	public int indexX,indexY;
	public int typeId;
	//public World w;
	public BufferedImage texture;
	public boolean collision=false;
	//public VolatileImage text;
	public Tile(Color c, int index, int indey,BufferedImage texture,int typeId) {
		//this.w = w;
		this.c= c;
		this.indexX = index;
		this.indexY=indey;
		this.texture=texture;
		this.typeId=typeId;
		//collideable?
		switch(typeId) {
		case 101://wall
			collision=true;
			break;
		}
	}
	public Rectangle getRectangle(){
		return new Rectangle((int)indexX*Config.tileSize,(int)indexY*Config.tileSize,Config.tileSize,Config.tileSize);
	}
	public void render(Graphics g, double offX, double offY) {
	if((indexX*Config.tileSize>=((-offX-(mainClass.g.getJFrame().getWidth())/2.0))) && (indexX*Config.tileSize<((-offX+(mainClass.g.getJFrame().getWidth()*1.5))))) {
			if((indexY*Config.tileSize>=((-offY-(mainClass.g.getJFrame().getHeight())/2.0))) && (indexY*Config.tileSize<((-offY+(mainClass.g.getJFrame().getHeight()*1.5))))) {
			g.setColor(this.c);
			//g.fillRect((int)((indexX*Config.tileSize)+offX), (int)((indexY*Config.tileSize)+offY), Config.tileSize, Config.tileSize);
			g.drawImage(texture,(int)((indexX*Config.tileSize)+offX), (int)((indexY*Config.tileSize)+offY), Config.tileSize, Config.tileSize,this);

			g.setColor(Color.BLACK);
			//g.drawRect((int)((indexX*Config.tileSize)+offX), (int)((indexY*Config.tileSize)+offY), Config.tileSize, Config.tileSize);
			}
		}
	}
}
