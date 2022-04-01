package WindowManager;
import java.awt.Color;
import World.Environment;
import java.awt.Graphics;
import java.util.Vector;
import Engine.Config;

public class Interface extends Widget{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2526838431248760290L;
	public Vector <Widget> widgets;
	public String name;
	public Environment e;
	public Interface(String name, int id, int x, int y, Environment e) {
		super(id,name,x,y,e);
		widgets = new Vector<Widget>();
		this.e=e;
	}
	public Interface(String name, int id, int x, int y, Interface interf, Environment e) {
		super(id,name,x,y,interf,e);
		widgets = new Vector<Widget>();
		this.e=e;
	}
	public void Render(Graphics g) {

			
		for(int i =0; i < widgets.size();i++) {
			if(Config.debug) {
				g.setColor(Color.WHITE);
				g.drawString(widgets.get(i).id+": "+widgets.get(i).desc, widgets.get(i).getRelativeX(), widgets.get(i).getRelativeY());
			}
			widgets.get(i).Render(g);
		}
	}
}
