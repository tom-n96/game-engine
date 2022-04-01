package World;
import java.awt.Graphics;

import WindowManager.WindowHandler;

public class EnvironmentThread extends Thread {
	public Environment e;
	public static WindowHandler w;
    public EnvironmentThread(String name,Environment e) {
        super(name);
        this.e=e;
    }

    @Override
    public void run() {
        System.out.println("Env Thread started - START "+Thread.currentThread().getName());
		//while(true) {
			e.updateEnvironment();
		//}
    }

    private void doDBProcessing() throws InterruptedException {
        Thread.sleep(5000);
    }
    public Environment getEnv() {
    	return e;
    }
    
}