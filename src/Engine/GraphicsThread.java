package Engine;
import java.awt.Graphics;

import WindowManager.WindowHandler;
import World.Environment;

public class GraphicsThread extends Thread {
	public Environment e;
	public static WindowHandler w;
	//public EnvironmentThread eThread;
    public GraphicsThread(String name,Environment e) {
        super(name);
        this.e=e;
        w = new WindowHandler(e);
      //  eThread= new EnvironmentThread("Env",e);
    }

    @Override
    public void run() {
        System.out.println("Graphics Thread started - START "+Thread.currentThread().getName());
		
		Graphics g =  w.getGraphics();
		while(true) {
	//	eThread.start();
		w.update(g);
		try {
			doDBProcessing();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	w.getEnvironment().updateEnvironment();
		}
    }

    private void doDBProcessing() throws InterruptedException {
        Thread.sleep(0);
    }
    public WindowHandler getJFrame() {
    	return w;
    }
    
}