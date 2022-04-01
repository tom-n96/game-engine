package WindowManager;
import java.awt.Color;
import World.Environment;
import java.awt.Graphics;

public class wTestWidget extends Widget{

	public wTestWidget(int id, String desc, int x, int y, Interface interf, Environment e) {
		super(id, desc, x,y,interf, e);
		// TODO Auto-generated constructor stub
	}
	public void Render(Graphics g) {
		g.setColor(Color.RED);
		//g.drawString("THIS IS A TEST WIDGET!! "+desc, x, y);
	}
}
