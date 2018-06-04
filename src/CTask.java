import java.util.TimerTask;


public class CTask extends TimerTask {

	
    CTask() {
        
    }
	
    private void MouseRun() {
        
    }
    
    private void GroundRun() {
        
    }
    
    private void DroneRun() {
        
    }
    
    private void DisplayRun() {
        
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("Task run!");
	    
	    DroneApp.m_Display.setGround(DroneApp.m_Ground);
	    
	    DroneApp.m_Mouse.run();
	    DroneApp.m_Ground.run();
	    DroneApp.m_Drone.run();
	    
	    DroneApp.m_Display.run();
	    DroneApp.m_Display.convertGround();
	    DroneApp.m_Display.convertDrone(DroneApp.m_Drone);
	    
	    DroneApp.g_Panel.revalidate();
	    DroneApp.g_FrameMain.repaint();
	    /*
	    MouseRun();
	    GroundRun();
	    DroneRun();
	    DisplayRun();
	    */
	    
	    
	}
	
	public CDisplay getCDisplay () {
	    return DroneApp.m_Display;
	}
	
}
