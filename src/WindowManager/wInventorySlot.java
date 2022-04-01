package WindowManager;
import java.awt.Color;
import World.Environment;
import java.awt.Graphics;

import javax.swing.JComponent;

public class wInventorySlot extends Widget{

	public wInventoryBar invBar;
	public wInventorySlot(int id, String desc, int x, int y, wInventoryBar interf, Environment e) {
		super(id, desc, x,y,interf, e);
		this.invBar=interf;
		// TODO Auto-generated constructor stub
	}
	public void Render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(super.getRelativeX(), super.getRelativeY(), (int)(invBar.width/invBar.slots), invBar.height);
		
		if(invBar.items[super.id] !=null)
			g.drawImage(invBar.items[super.id].sprite,super.getRelativeX(),super.getRelativeY(),(int)(invBar.width/invBar.slots), invBar.height,(JComponent)this);
		//g.drawString("THIS IS A TEST WIDGET!! "+desc, x, y);
	}
}
