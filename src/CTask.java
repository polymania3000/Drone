import java.util.TimerTask;


public class CTask extends TimerTask {
	private CMouse m_Mouse = new CMouse();
	private CGround m_Ground = new CGround();
	private CDrone m_Drone = new CDrone();
	private CDisplay m_Display = new CDisplay();
	
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
	    
	    m_Display.setGround(m_Ground);
	    
	    m_Mouse.run();
	    m_Ground.run();
	    m_Drone.run();
	    
	    m_Display.run();
	    m_Display.convertGround();
	    m_Display.convertDrone(m_Drone);
	    
	    
	    /*
	    MouseRun();
	    GroundRun();
	    DroneRun();
	    DisplayRun();
	    */
	}
	
	public CDisplay getCDisplay () {
	    return this.m_Display;
	}
	
}
