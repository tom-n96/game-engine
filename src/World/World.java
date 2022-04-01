package World;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Vector;

import Engine.Config;
import Engine.SpriteLoader;



public class World {
	//public Tile [][] world = new Tile[32][32];
	public Chunk [] [] chunks;// = new Chunk[world.length/Config.chunkSize][world[0].length/Config.chunkSize];
	public Vector <Entity> entities;//=new Vector<Entity>();
	//public Vector <Item> itemList;
	public Tile [] collidables;
	public Player player;
	Environment e;

	public World(Environment e) {
		this.e=e;
		//initialize();
	}
	public void initialize() {


		player=new Player(722, 222,64, 128, e,SpriteLoader.player);
		player.canMove(true);
		Map testMap=new Map(e);
		collidables=new Tile[testMap.collidableTiles.size()];
		for(int i =0; i < collidables.length;i++) {
			collidables[i]=testMap.collidableTiles.get(i);
		}
		chunks=testMap.chunks;
		entities=testMap.entities;
	//	itemList=new Vector<Item>();
		
		//itemList.add(entities.get(0).createItem("Egg"));
		//player.giveItem(itemList.get(0));
		System.out.println("World Loaded");
		//init chunks
		
		
		//System.out.println("Chunks Initialized");
		//end chunks
			

	}
	public void update() {
		player.update(); 
		for(int i = 0; i < entities.size();i++) {
			entities.get(i).update();
	}
	}

	public void renderEntities(Graphics g,double offX,double offY) {

		for(int i = 0; i < entities.size();i++) {
				entities.get(i).render(g);
		}
		player.render(g);
	}

	public void render(Graphics g,double offX,double offY) {
		int chunkX = ((int) (player.x-(player.x%(Config.chunkSize*Config.tileSize)))/(Config.tileSize*Config.chunkSize));
		int chunkY = ((int) (player.y-(player.y%(Config.chunkSize*Config.tileSize)))/(Config.tileSize*Config.chunkSize));
		//System.out.println(chunkX);
		if(chunkX>=0&&chunkY>=0&&chunkX<chunks.length&&chunkY<chunks[0].length) {
			chunks[chunkX][chunkY].render(g, offX, offY);
		if(chunkX+1<chunks.length) {
			chunks[chunkX+1][chunkY].render(g, offX, offY);
			if(chunkY+1<chunks[0].length) {
				chunks[chunkX+1][chunkY+1].render(g, offX, offY);
			}
			if(chunkY-1>=0)
				chunks[chunkX+1][chunkY-1].render(g, offX, offY);
		}
		if(chunkY+1<chunks[0].length) {
			chunks[chunkX][chunkY+1].render(g, offX, offY);
			if(chunkX-1>=0)
			chunks[chunkX-1][chunkY+1].render(g, offX, offY);
		}
		if(chunkY-1>=0) {
			chunks[chunkX][chunkY-1].render(g, offX, offY);

			if(chunkX-1>=0) {
			chunks[chunkX-1][chunkY-1].render(g, offX, offY);

		}
		}
			if(chunkX-1>=0) {
			chunks[chunkX-1][chunkY].render(g, offX, offY);
			
			}
		}
		//this was commented out for some reason??
		for(int i = 0; i < chunks.length;i++) {
			for(int j = 0; j < chunks[i].length;j++) {
				if(Math.hypot(chunks[i][j].centerX-player.x, chunks[i][j].centerY-player.y)<Config.renderDistance*(Config.chunkSize*Config.tileSize))
				chunks[i][j].render(g,offX,offY);

			}
		}
	}

}
