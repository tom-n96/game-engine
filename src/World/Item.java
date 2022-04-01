package World;
import java.awt.image.BufferedImage;

public class Item {
	public String name;
	public int id;
	public BufferedImage sprite;
	public Item(String name, int id, BufferedImage sprite) {
		this.name=name;
		this.id=id;
		this.sprite=sprite;
	}
}
