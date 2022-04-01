package Engine;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import World.Environment;


public class DrawPanel extends JPanel{
	private Environment e;
	public DrawPanel(Environment e){
	//	setIgnoreRepaint(true);
		setDoubleBuffered(true);
		this.e=e;
	}
	public void paintComponents(Graphics g){
		super.paintComponents(g);
       // g.clearRect(0, 0, this.getWidth(), this.getHeight());
       // if(e!=null)
		//	e.renderEnvironment(g);
	}
	public void update(Graphics g){
	/*super.paintComponents(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
		//e.renderEnvironment(g);
        paintComponents(g);*/
	    Graphics offgc;
	    Image offscreen = null;
	    @SuppressWarnings("deprecation")
		Dimension d = size();

	    // create the offscreen buffer and associated Graphics
	    offscreen = createImage(d.width+30, d.height+30);
	    offgc = offscreen.getGraphics();
	    // clear the exposed area
	    offgc.setColor(getBackground());
	    offgc.fillRect(0, 0, d.width+30, d.height+30);
	    offgc.setColor(getForeground());
	    e.renderEnvironment(offgc);
	    // do normal redraw
	    paintComponents(offgc);
	    // transfer offscreen to window 
	    g.drawImage(offscreen, 0, 0, this);
	}

}
