package WindowManager;
import java.awt.Graphics;
import World.Environment;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Widget extends JComponent{
	public String desc;
	public int id;
	public Environment e;
	public int x,y;
	public Interface interf;
	public Widget(int id, String desc, int x, int y,Interface interf, Environment e) {
		this.id=id;
		this.desc=desc;
		this.e=e;
		this.x=x;
		this.y=y;
		this.interf=interf;
	}
	public Widget(int id, String desc, int x, int y, Environment e) {
		this.id=id;
		this.desc=desc;
		this.e=e;
		this.x=x;
		this.y=y;
		//this.interf=interf;
	}
	public int getRelativeX() {
		if(interf!=null)
			return x+interf.getRelativeX();
		else 
			return x;
	}
	public int getRelativeY() {
		if(interf!=null)
			return y+interf.getRelativeY();
		else
			return y;
	}
	public void Render(Graphics g) {
		
	}
}
