package World;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import Engine.Config;
import Engine.mainClass;

public class Entity extends JComponent {
	public float x,y;
	public int width,height;
	public int hbX,hbY,hbWid,hbHei;//hitbox variables
	public Rectangle [] hitboxes;
	public float velocityX,velocityY;
	public BufferedImage[] sprite; 
	public int spritePos = 0;
	public int spriteUpdateCount=0;
	public int spriteUpdateTime=20;
	public boolean spriteVelocityDependant=true;
	public Environment e;
	public Color c;
	public int xOffset=0;
	public int yOffset=0;
	public Item item = null;
	public boolean canPickUp=false;
	public boolean canMove=false;
	public Item [] items = new Item[8]; //inventory
	public Entity(int x, int y,int width, int height, Environment e,BufferedImage [] sprite){
		hitboxes=new Rectangle[4];
		hitboxes[0]=new Rectangle((int)x-(width/2)+xOffset,(int)y-(height/2)+yOffset,width/2,height/2);
		hitboxes[1]=new Rectangle((int)x+xOffset,(int)y-(height/2)+yOffset,width/2,height/2);
		hitboxes[2]=new Rectangle((int)x-(width/2)+xOffset,(int)y+yOffset,width/2,height/2);
		hitboxes[3]=new Rectangle((int)x+xOffset,(int)y+yOffset,width/2,height/2);
		//hitboxes[0]=;
		//hitboxes[0]=;
		this.c=Color.WHITE;
		this.sprite=sprite;
		this.e=e;
		//assign coords
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hbX = x;
		this.hbY=y;
		this.hbWid = width;
		this.hbHei=height;
		
		velocityX = 0; 
		velocityY = 0;
	}
	public void canPickUp(Boolean can) {
		canPickUp=can;
	}
	public void canMove(Boolean can) {
		canMove=can;
	}
	public void associateItem(Item i) {
		item=i;
	}
	public Item createItem(String name,int id) {
		return new Item(name,id,sprite[0]);
	}
	public void giveItem(Item item) {
		if(getNextFreeSlot()!=-1)
			items[getNextFreeSlot()]=item;
	}
	public void giveItems(Item [] itemlist) {
		for(int i = 0 ; i < itemlist.length;i++) {
			if(getNextFreeSlot()!=-1)
				items[getNextFreeSlot()]=itemlist[i];
		}
	}
	private int getNextFreeSlot() {
		for(int i =0; i < items.length;i++) {
			if(items[i]==null) {
				return i;
			}
		}
		return -1;
	}
	public void setHitbox(int xOffsett,int yOffsett,int width,int height) {
		xOffset=xOffsett;
		yOffset=yOffsett;
		hbWid=width;
		hbHei=height;
		hitboxes[0]=new Rectangle((int)x-(width/2)+xOffset,(int)y-(height/2)+yOffset,width/2,height/2);
		hitboxes[1]=new Rectangle((int)x+xOffset,(int)y-(height/2)+yOffset,width/2,height/2);
		hitboxes[2]=new Rectangle((int)x-(width/2)+xOffset,(int)y+yOffset,width/2,height/2);
		hitboxes[3]=new Rectangle((int)x+xOffset,(int)y+yOffset,width/2,height/2);
	}
	public void render(Graphics g){
		if((x>=((-e.cameraOffsetX-(mainClass.g.getJFrame().getWidth())/2.0))) && (x<((-e.cameraOffsetX+(mainClass.g.getJFrame().getWidth()*1.5))))) {
			if((y>=((-e.cameraOffsetY-(mainClass.g.getJFrame().getHeight())/2.0))) && (y<((-e.cameraOffsetY+(mainClass.g.getJFrame().getHeight()*1.5))))) {
				g.setColor(c);
				//g.fillRect((int)((x+e.cameraOffsetX)-(width/2)), (int)((y+e.cameraOffsetY)-(height/2)),width,height);
				if(sprite.length>1) {
					if(velocityX==0&&velocityY==0)
						spritePos=0;
					else {
						int toUpdate;
						long dt=System.currentTimeMillis()-mainClass.g.w.lastStartFrame;
						if(spriteVelocityDependant) {
							if(velocityX>Config.MAX_WALK_SPEED)
								velocityX=Config.MAX_WALK_SPEED;
							if(velocityY>Config.MAX_WALK_SPEED)
								velocityY=Config.MAX_WALK_SPEED;
							if(velocityX<-Config.MAX_WALK_SPEED)
								velocityX=-Config.MAX_WALK_SPEED;
							if(velocityY<-Config.MAX_WALK_SPEED)
								velocityY=-Config.MAX_WALK_SPEED;

							toUpdate=(int) (spriteUpdateTime/(Math.abs(velocityX)+Math.abs(velocityY)));
						}
						else {

							toUpdate=(int) (spriteUpdateTime);
					//	System.out.println(dt);
							}
						if(spriteUpdateCount>toUpdate) {
							spritePos++;
							if(spritePos>=sprite.length)
								spritePos=1;
							spriteUpdateCount=0;
						}
						spriteUpdateCount+=dt;
					}
				}
				g.drawImage(sprite[spritePos],(int)((x+e.cameraOffsetX)-(width/2)), (int)((y+e.cameraOffsetY)-(height/2)),width,height,this);
				if(Config.debug) {
					g.setColor(Color.ORANGE);
					//g.drawRect((int)((x+e.cameraOffsetX)-(width/2)), (int)((y+e.cameraOffsetY)-(height/2)),width,height);
					g.drawRect((int)(hitboxes[0].x+e.cameraOffsetX),(int)(hitboxes[0].y+e.cameraOffsetY),hitboxes[0].width,hitboxes[0].height);
					g.drawString("0",(int)(hitboxes[0].x+e.cameraOffsetX+5),(int)(hitboxes[0].y+e.cameraOffsetY+15));
					g.drawRect((int)(hitboxes[1].x+e.cameraOffsetX),(int)(hitboxes[1].y+e.cameraOffsetY),hitboxes[1].width,hitboxes[1].height);
					g.drawString("1",(int)(hitboxes[1].x+e.cameraOffsetX+5),(int)(hitboxes[1].y+e.cameraOffsetY+15));
					g.drawRect((int)(hitboxes[2].x+e.cameraOffsetX),(int)(hitboxes[2].y+e.cameraOffsetY),hitboxes[2].width,hitboxes[2].height);
					g.drawString("2",(int)(hitboxes[2].x+e.cameraOffsetX+5),(int)(hitboxes[2].y+e.cameraOffsetY+15));
					g.drawRect((int)(hitboxes[3].x+e.cameraOffsetX),(int)(hitboxes[3].y+e.cameraOffsetY),hitboxes[3].width,hitboxes[3].height);
					g.drawString("3",(int)(hitboxes[3].x+e.cameraOffsetX+5),(int)(hitboxes[3].y+e.cameraOffsetY+15));
				}
			}
		}
	}
	public void update(){

		//update hitbox/
		hitboxes[0].x=(int)x-(hbWid/2)+xOffset;
		hitboxes[0].y=(int)y-(hbHei/2)+yOffset;
		hitboxes[1].x=(int)x+xOffset;
		hitboxes[1].y=(int)y-(hbHei/2)+yOffset;
		hitboxes[2].x=(int)x-(hbWid/2)+xOffset;
		hitboxes[2].y=(int)y+yOffset;
		hitboxes[3].x=(int)x+xOffset;
		hitboxes[3].y=(int)y+yOffset;
		//end hitbox update
		
		if(velocityX>Config.MAX_WALK_SPEED)
			velocityX=Config.MAX_WALK_SPEED;
		if(velocityY>Config.MAX_WALK_SPEED)
			velocityY=Config.MAX_WALK_SPEED;
		if(velocityX<-Config.MAX_WALK_SPEED)
			velocityX=-Config.MAX_WALK_SPEED;
		if(velocityY<-Config.MAX_WALK_SPEED)
			velocityY=-Config.MAX_WALK_SPEED;

	/*	if(velocityX+velocityY>Config.MAX_WALK_SPEED) {
			velocityX=(float) (Config.MAX_WALK_SPEED/2.0);
			velocityY=(float) (Config.MAX_WALK_SPEED/2.0);
		}
		else if(velocityY>Config.MAX_WALK_SPEED)
			velocityY=Config.MAX_WALK_SPEED;
		else if(velocityX>Config.MAX_WALK_SPEED)
			velocityX=Config.MAX_WALK_SPEED;

		
		if(velocityY>Config.MAX_WALK_SPEED)
			velocityY=Config.MAX_WALK_SPEED;
		if(velocityX<-Config.MAX_WALK_SPEED)
			velocityX=-Config.MAX_WALK_SPEED;
		if(velocityY<-Config.MAX_WALK_SPEED)
			velocityY=-Config.MAX_WALK_SPEED;
			*/
		//FRICTION

		if(velocityX>0)
			velocityX-=Config.SLOWDOWN_SPEED*e.dt;
		if(velocityY>0)
			velocityY-=Config.SLOWDOWN_SPEED*e.dt;
		if(velocityX<0)
			velocityX+=Config.SLOWDOWN_SPEED*e.dt;
		if(velocityY<0)
			velocityY+=Config.SLOWDOWN_SPEED*e.dt;
		if(!mainClass.g.getJFrame().mousePressed) {
			if(velocityX<(Config.SLOWDOWN_SPEED*e.dt)+((Config.SLOWDOWN_SPEED*e.dt)*.2)&&velocityX>-(Config.SLOWDOWN_SPEED*e.dt)-((Config.SLOWDOWN_SPEED*e.dt)*.2))
				velocityX=0;
			if(velocityY<(Config.SLOWDOWN_SPEED*e.dt)+((Config.SLOWDOWN_SPEED*e.dt)*.2)&&velocityY>-(Config.SLOWDOWN_SPEED*e.dt)-((Config.SLOWDOWN_SPEED*e.dt)*.2))
				velocityY=0;
		}
		if(canMove) {
		//collision 
		
			Boolean [] collideE=checkCollision(e.world.entities);
			Boolean [] collideT=checkTileCollision(e.world.collidables);
			//for picking up items
			if(collideE[0]==true||collideE[1]==true||collideE[2]==true||collideE[3]==true) {
				
			}
		//1 left, 2 right, 3 up, 4 down
		if((collideE[0]==true&&collideE[1]==true)||(collideT[0]==true&&collideT[1]==true)) {
			if(velocityY<0)
				velocityY=0;
		}
		else if(collideE[0]==true||collideT[0]==true) {
			//if(velocityX<=0)
			if(velocityX<0)
				velocityX=0;
		}
		else if(collideE[1]==true||collideT[1]==true) {
			//if(velocityX>=0)
			if(velocityX>0)
				velocityX=0;
		}
		
		if((collideE[2]==true&&collideE[3]==true)||(collideT[2]==true&&collideT[3]==true)) {
			if(velocityY>0)
				velocityY=0;
		}
		else if(collideE[2]==true||collideT[2]==true) {
			//if(velocityX<=0)
			if(velocityX<0)
				velocityX=0;
		}
		else if(collideE[3]==true||collideT[3]==true) {
			//if(velocityX>=0)
			if(velocityX>0)
				velocityX=0;
		}
		/*if(collideE==3||collideT[2]==true) {
			//if(velocityY<=0)
			velocityY=0;
		}
		if(collideE==4||collideT[3]==true) {
			//if(velocityY>=0)
			velocityY=0;
		}*/
		//end friction
			changeXBy(velocityX*e.dt);
			changeYBy(velocityY*e.dt);
		}
		/*if(checkCollision(e)== 4){
			//velocityX=0;
			velocityY=0;
		}*/

			
	}
	public float getVelocityX(){
		return velocityX;
	}
	public float getVelocityY(){
		return velocityY;
	}
	public Rectangle getRectangle(){
		return new Rectangle((int)x-(width/2),(int)y-(height/2),width,height);
	}
	public void changeXBy(float toChange){
		//if(checkCollision(e.e)!=2&&checkCollision(e.e)!=1)
			x+=toChange;
	
	}
	public void changeYBy(float toChange){
		//if(checkCollision(e.e)!=2&&checkCollision(e.e)!=1)
			y+=toChange;
	
	}/*
	public void changeYBy(float toChange){
		Rectangle current = getRectangle();
		current.y+=toChange;
		//if(checkCollision(e.e)==4){
			Rectangle future = checkAmount(e.e,current);
			y+=(toChange-future.height)+1;
		//}
		//else
		//	y+=toChange;
	
	}*/
	public Boolean [] checkTileCollision(Tile [] e){ //1 left, 2 right, 3 up, 4 down
		Boolean [] output = new Boolean [4];
		output[0]=false;
		output[1]=false;
		output[2]=false;
		output[3]=false;
		//output[0]=-1;
		//output[1]=-1;
		for(int i =0; i < e.length;i++){/*
				if(e[i].getRectangle().intersects(this.getRectangle())){
					//Rectangle intersection = e[i].getRectangle().intersection(this.getRectangle());
				/*	int hbcX = this.getHBCenter()[0];
					int hbcY = this.getHBCenter()[1];
					if(intersection.x <= hbcX) // on the left?
						if(intersection.x+intersection.width <= hbcX) // definitely on the left
							output[0]=0;
					if(intersection.x > hbcX) // on the right?
						if(intersection.x+intersection.width > hbcX) // definitely on the right
							output[0]=1;
					if(intersection.y<hbcY)
						if(intersection)
				//	if(intersection)*/
					//Rectangle intersection = e[i].getRectangle().intersection(this.getRectangle());    
					Rectangle r1 = e[i].getRectangle();
					
					if(this.hitboxes[0].intersects(r1)) {
						output[0]=true;
					}
					if(this.hitboxes[1].intersects(r1)) {
						output[1]=true;
					}
					if(this.hitboxes[2].intersects(r1)) {
						output[2]=true;
					}
					if(this.hitboxes[3].intersects(r1)) {
						output[3]=true;
					}
		}
					return output;
					//Intersector.intersectRectangles(r1, r2, intersection);     
					/*if(intersection.x > r1.x)          {

							System.out.println("4-");
						
					    output[3]=true;//return 4  ;                    
					    }     
					if(intersection.y > r1.y)    {       
							System.out.println("3-");
					    output[2]=true;//return 3;                    
					    }        
					if(intersection.x + intersection.width < r1.x + r1.width) {
							System.out.println("1-");
						//System.out.println("1");
					   output[0]=true;//return 1;                            
					   }
					if(intersection.y + intersection.height < r1.y + r1.height){
							System.out.println("2-");
						//System.out.println("4");
						output[1]=true;//  return 2; 
							}
				}
				*/
					//1 left, 2 right, 3 up, 4 down
					/*
				if(intersection.x > r1.x) {

						System.out.println("4-");//down
					
				    output[3]=true;//return 4  ;                    
				    }     
				if(intersection.y > r1.y) {       
						System.out.println("3-");//up
				    output[0]=true;//return 3;                    
				    }        
				if((intersection.x) < r1.x) {
						System.out.println("1-");//left
					//System.out.println("1");
				   output[2]=true;//return 1;                            
				   }
				if((intersection.y) < r1.y) {
						System.out.println("2-");//right
					//System.out.println("4");
					output[1]=true;//  return 2; 
						}
				*/
			}
		
	//	return output;
	
	public Boolean [] checkCollision(Vector<Entity> e){ //-1 none, 1 left, 2 right, 3 up, 4 down
		Boolean [] output = new Boolean [4];
		output[0]=false;
		output[1]=false;
		output[2]=false;
		output[3]=false;
		Vector <Entity> toPickUp=new Vector<Entity>();
		
		for(int i =0; i < e.size();i++){
			if(e.get(i)!=this)
				if(e.get(i).getRectangle().intersects(this.getRectangle())){
					//Rectangle intersection = e[i].getRectangle().intersection(this.getRectangle());
				/*	int hbcX = this.getHBCenter()[0];
					int hbcY = this.getHBCenter()[1];
					if(intersection.x <= hbcX) // on the left?
						if(intersection.x+intersection.width <= hbcX) // definitely on the left
							output[0]=0;
					if(intersection.x > hbcX) // on the right?
						if(intersection.x+intersection.width > hbcX) // definitely on the right
							output[0]=1;
					if(intersection.y<hbcY)
						if(intersection)
				//	if(intersection)*/
					//Rectangle intersection = e.get(i).getRectangle().intersection(this.getRectangle());    
					Rectangle r1 = e.get(i).getRectangle();
					//Rectangle r1 = e[i].getRectangle();
					
					if(this.hitboxes[0].intersects(r1)) {
						output[0]=true;
					}
					if(this.hitboxes[1].intersects(r1)) {
						output[1]=true;
					}
					if(this.hitboxes[2].intersects(r1)) {
						output[2]=true;
					}
					if(this.hitboxes[3].intersects(r1)) {
						output[3]=true;
					}
					if(e.get(i).canPickUp&&(output[0]==true||output[1]==true||output[2]==true||output[3]==true)) {
						toPickUp.add(e.get(i));
					}
				}
		}
				for(int i = 0; i < toPickUp.size();i++) {
					this.e.world.entities.remove(toPickUp.get(i));
					this.giveItems(toPickUp.get(i).items);
				}
					return output;
		

	}
	public Entity checkCollisionPickup(Vector<Entity> e){ //-1 none, 1 left, 2 right, 3 up, 4 down

		for(int i =0; i < e.size();i++){
			if(e.get(i)!=this)
				if(e.get(i).getRectangle().intersects(this.getRectangle())){
				Rectangle r1 = e.get(i).getRectangle();
					//Rectangle r1 = e[i].getRectangle();
					
					if(this.hitboxes[0].intersects(r1)||this.hitboxes[1].intersects(r1)||this.hitboxes[2].intersects(r1)||this.hitboxes[3].intersects(r1)) {
						return e.get(i);
					}
				}
		}
					return null;
		

	}
	public Rectangle checkAmount(Entity [] e){
		for(int i =0; i < e.length;i++){
			if(e[i]!=this)
				if(e[i].getRectangle().intersects(this.getRectangle())){
					return e[i].getRectangle().intersection(this.getRectangle());
				}
		}
		return new Rectangle(0,0,0,0);
	}
	private Rectangle checkAmount(Entity [] e,Rectangle toCheck){
		for(int i =0; i < e.length;i++){
			if(e[i]!=this)
				if(e[i].getRectangle().intersects(toCheck)){
					return e[i].getRectangle().intersection(toCheck);
				}
		}
		return new Rectangle(0,0,0,0);
	}
	public int [] getHBCenter(){
		int [] output = {(int)(x+(width/2)),(int)(x+(height/2))};
		return output;
	}
	
	public void accelerate(double d, double e2){
		//System.out.println("Accelerate x: "+d+" y: "+e2);

		velocityX+=d;
		velocityY+=e2;
		//System.out.println("now x: "+velocityX+" y: "+velocityY);
	}

	public void stop() {
		accelerate(-velocityX,-velocityY);
		
	}
	
}
