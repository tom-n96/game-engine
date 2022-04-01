package World;
import java.awt.Color;
import Engine.SpriteLoader;
import Engine.TextureLoader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

import Engine.Config;

public class Map {
	public int mapWidth = 128;
	public int mapHeight = 64;
	public Tile [][] world = new Tile[mapWidth][mapHeight];
	public Chunk [] [] chunks = new Chunk[world.length/Config.chunkSize][world[0].length/Config.chunkSize];
	public Vector<Tile> collidableTiles = new Vector<Tile>();
	public Vector <Entity> entities=new Vector<Entity>();
	public Vector <Item> itemList=new Vector<Item>();
	public Environment e;
	public Map(Environment e) {
		this.e=e;
		//tiles

		try {
			world=tilesFromImage(ImageIO.read(new File("map.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i =0; i < world.length;i++) {
			for(int j = 0; j< world[i].length;j++) {
				if(world[i][j].collision) {
					collidableTiles.add(world[i][j]);
				}
			}
		}
		//chunks
		for(int i = 0; i < world.length;i+=Config.chunkSize) {
			for(int j = 0; j < world[i].length;j+=Config.chunkSize) {
				Tile [][] toSave=new Tile[Config.chunkSize][Config.chunkSize];
				for(int x = 0; x < Config.chunkSize;x++) {
					for(int z = 0; z < Config.chunkSize;z++) {
						toSave[x][z]=world[i+x][j+z];
					}
				}
				chunks[i/Config.chunkSize][j/Config.chunkSize]=new Chunk(toSave);
			}
		}
			//entities
			//michelle entity
			Entity michelle=new Entity(2247,267,64,128,e,SpriteLoader.michelle);
			entities.add(michelle);
			//end michelle
		
			for(int i = 0; i < 40;i++) {
				Random r = new Random();
				Entity toCreate=new Entity(r.nextInt(mapWidth*Config.tileSize),r.nextInt(mapHeight*Config.tileSize),50,50,e,SpriteLoader.egg);
				entities.add(toCreate);
				
				//create item based on entity and assign to entity
				Item toAdd=toCreate.createItem("Egg",200);
				toCreate.associateItem(toAdd);
				toCreate.canPickUp=true;
				toCreate.giveItem(toAdd);
				if(!itemExists(toAdd.name,toAdd.id)) {
					itemList.add(toAdd);
				}
				//System.out.println(itemList.size());
			}
	}
	public boolean itemExists(String name, int id) {
		for(int i = 0; i < itemList.size();i++) {
			if(itemList.get(i).name==name && itemList.get(i).id==id) {
				return true;
			}
			
		}
		return false;
	}
	public Tile[][] tilesFromImage(BufferedImage imageBase){
		Tile[][] output=new Tile[imageBase.getWidth()][imageBase.getHeight()];
		for(int i =0; i < output.length;i++) {
			for(int j = 0; j < output[0].length;j++) {
				Color tile=new Color(imageBase.getRGB(i, j));
				switch(tile.getRed()) {
				case 100:
					output[i][j]=new Tile(Color.YELLOW,i,j,TextureLoader.grassTexture,100);
					break;
				case 101:
					output[i][j]=new Tile(Color.YELLOW,i,j,TextureLoader.wallTexture,101);
					break;
				case 102:
					output[i][j]=new Tile(Color.YELLOW,i,j,TextureLoader.stoneTexture,102);
					break;
				case 103:
					output[i][j]=new Tile(Color.YELLOW,i,j,TextureLoader.woodFloorTexture,102);
					break;
				default:
					output[i][j]=new Tile(Color.YELLOW,i,j,TextureLoader.grassTexture,100);
					break;
				}
				//System.out.print(tile.getRed()+" ");
					
			}
			System.out.println("");
		}
		
		return output;
	}
}
