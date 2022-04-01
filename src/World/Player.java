package World;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Engine.SpriteLoader;

public class Player extends Entity {
	private int angle=-1;

	public Player(int x, int y, int width, int height, Environment e, BufferedImage[] sprite) {
		super(x, y, width, height, e, sprite);
		super.setHitbox(0, height/3, width/2, height/4);
		// TODO Auto-generated constructor stub
	}

	public void render(Graphics g) {
		float velX=super.velocityX;
		float velY=super.velocityY;
		/*if(super.velocityY>0&&(super.velocityY>super.velocityX&&super.velocityX>0)) {//&&super.velocityY>velocityX
			if(angle!=0) {
				super.sprite=SpriteLoader.playerforward;
				super.spritePos=1;
			}
			angle=0;
		}
		else if(super.velocityY<0&&super.velocityY<super.velocityX) {//&&super.velocityY>velocityX
			if(angle!=1) {
				super.sprite=SpriteLoader.player;
				super.spritePos=1;
			}
			angle=1;
		}
		else if(super.velocityX<0) {
			if(angle!=2) {
				super.sprite=SpriteLoader.playerleft;
				super.spritePos=1;
			}
			angle=2;
		}
		else if(super.velocityY>0) {
			if(angle!=3) {
				super.sprite=SpriteLoader.playerright;
				super.spritePos=1;
			}
			angle=3;
		}
		*/
		if(velY!=0&&velX!=0) {
		//q1
			if(velX>=0&&velY<=0) {
				if(Math.abs(velY)>velX)	{
					if(angle!=1) {
						super.sprite=SpriteLoader.player;
						super.spritePos=1;
					}
					angle=1;
				}
				else {
					if(angle!=3) {
						super.sprite=SpriteLoader.playerright;
						super.spritePos=1;
					}
					angle=3;
				}
			}
			
			//q2
			if(velX>=0&&velY>=0) {
				if(velY>velX)	{
					if(angle!=0) {
						super.sprite=SpriteLoader.playerforward;
						super.spritePos=1;
					}
					angle=0;
				}
				else {
					if(angle!=3) {
						super.sprite=SpriteLoader.playerright;
						super.spritePos=1;
					}
					angle=3;
				}
			}
			
			//q3
			if(velX<=0&&velY>=0) {
				if(velY>Math.abs(velX))	{
					if(angle!=0) {
						super.sprite=SpriteLoader.playerforward;
						super.spritePos=1;
					}
					angle=0;
				}
				else {
					if(angle!=2) {
						super.sprite=SpriteLoader.playerleft;
						super.spritePos=1;
					}
					angle=2;
				}
			}
			//q4
			if(velX<=0&&velY<=0) {
				if(Math.abs(velY)>Math.abs(velX))	{
					if(angle!=1) {
						super.sprite=SpriteLoader.player;
						super.spritePos=1;
					}
					angle=1;
				}
				else {
					if(angle!=2) {
						super.sprite=SpriteLoader.playerleft;
						super.spritePos=1;
					}
					angle=2;
				}
			}

	//	System.out.println("velX: "+velX+" velY: "+velY);
		}
		super.render(g);
	}

}
