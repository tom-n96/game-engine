package WindowManager;
import java.awt.Color;
import Engine.mainClass;
import World.Environment;
import java.awt.Graphics;

import World.Item;

public class wInventoryBar extends Interface{
	public int width = 496;
	public int height = 64;
	public int slots = 8;
	public Item [] items = new Item[8];
	public wInventoryBar(int id, String desc, int x, int y, Interface interf, Environment e) {
		super(desc,id, x,y,interf, e);
		//init slots

		for(int i = 0; i < slots;i++) {
			super.widgets.add(new wInventorySlot(i,"invSlot"+i,i*(width/slots),0,this,e));
		}
		// TODO Auto-generated constructor stub
	}
	public void Render(Graphics g) {
			//super.x=+100;
		items=super.e.world.player.items;
		super.y=mainClass.g.getJFrame().getHeight()-80;
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(super.getRelativeX(), super.getRelativeY(), width, height);
		Color background=new Color(0,0,0,127);
		g.setColor(background);
		g.fillRect(super.getRelativeX(), super.getRelativeY(), width, height);
		for(int i =0; i < widgets.size();i++) {
			super.widgets.get(i).Render(g);
		}

		
	}
}
