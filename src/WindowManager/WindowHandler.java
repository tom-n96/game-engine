package WindowManager;
import java.awt.Graphics;
import World.Environment;
import Engine.DrawPanel;
import Engine.Config;
import Engine.mainClass;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowHandler extends JFrame implements KeyListener,MouseListener{
	private Environment e;
	private JPanel panel;
	private boolean draw;
	public boolean mousePressed = false;
    private long frameCounter=0;
    public long lastFrame=0;
    private long lastCheck=System.currentTimeMillis();
   	public long lastStartFrame=System.currentTimeMillis();
    public long dt=0;
	public WindowHandler(Environment e) {

		draw=true;
		//load world assets
		setIgnoreRepaint(true);
		this.e=e;
		panel = new DrawPanel(e);
		add(panel);
		//initialize window
		setTitle("Game");
		setSize(Config.windowX,Config.windowY);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
        addMouseListener(this);
	}
	public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='d') {
        	if(draw)
        		draw=false;
        	else
        		draw=true;
        }
    }
	public void paint(Graphics g) {


	}
	public void update(Graphics g) {
		//if(draw)
		this.validate();
		this.dt=System.currentTimeMillis()-lastStartFrame;
		e.cameraOffsetX=(mainClass.g.getJFrame().getWidth()/2)-e.world.player.x;
		e.cameraOffsetY=(mainClass.g.getJFrame().getHeight()/2)-e.world.player.y;
		frameCounter++;

		if(System.currentTimeMillis()-lastCheck>=1000) {
			System.out.println(lastFrame);
			lastFrame=frameCounter;
			frameCounter=0;
			lastCheck=System.currentTimeMillis();
	
		}
			panel.update(g);
			lastStartFrame=System.currentTimeMillis();
	}
	public Environment getEnvironment() {
		return e;
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		if(!mousePressed)
			mousePressed=true;
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(mousePressed)
			mousePressed=false;
		//e.stopPlayer();
		// TODO Auto-generated method stub
		
	}
}
