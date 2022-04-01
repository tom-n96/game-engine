package World;
import java.awt.Graphics;

import Engine.Config;

public class Chunk {
	public Tile tiles [] [];
	public int centerX;
	public int centerY;
	public Chunk(Tile[][] tiles) {
		this.tiles=tiles;
		centerX=(this.tiles[0][0].indexX*Config.tileSize)+(Config.chunkSize*Config.tileSize);
		centerY=(this.tiles[0][0].indexY*Config.tileSize)+(Config.chunkSize*Config.tileSize);
	}
	public void render(Graphics g,double offX,double offY) {
		for(int i = 0; i < tiles.length;i++) {
			for(int j = 0; j < tiles[i].length;j++) {
				
				tiles[i][j].render(g,offX,offY);

			}
		}
	}
}
