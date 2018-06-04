import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CDisplay {
    private int width;
    private int height;
    
    private CGround gnd = new CGround();
    
    private ArrayList<Point> vertexGroundDisplay = new ArrayList<Point>();
    private ArrayList<Point> vertexDroneDisplay = new ArrayList<Point>();
    private ArrayList<Point> vertexDroneForce = new ArrayList<Point>();

    public CDisplay () {
        this.vertexDroneForce.add(new Point(0, 0));
        this.vertexDroneForce.add(new Point(0, 0));
        this.vertexDroneForce.add(new Point(0, 0));
        this.vertexDroneForce.add(new Point(0, 0));
    }
    
    private void displayGround() {
        //drawLine(vertexGroundDisplay.get(0).getX(), vertexGroundDisplay.get(0).getY(),
        //        vertexGroundDisplay.get(1).getX(), vertexGroundDisplay.get(1).getY());
    }
    
    private void displayDrone() {
        
    }
    
    private void drawGround(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(this.vertexGroundDisplay.get(0).x, this.vertexGroundDisplay.get(0).y,
                this.vertexGroundDisplay.get(1).x, this.vertexGroundDisplay.get(1).y);
    }
    
    private void drawDrone(Graphics g) {
        //g.drawRect(this.vertexDroneDisplay.get(3).x, 
        //        this.vertexDroneDisplay.get(3).y, 
        //        this.vertexDroneDisplay.get(1).x - this.vertexDroneDisplay.get(3).x, 
        //        this.vertexDroneDisplay.get(1).y - this.vertexDroneDisplay.get(3).y);
        
        /* draw the frame of drone */
        g.setColor(Color.BLACK);
        
        g.drawLine(this.vertexDroneDisplay.get(0).x, this.vertexDroneDisplay.get(0).y,
                this.vertexDroneDisplay.get(1).x, this.vertexDroneDisplay.get(1).y);
        
        g.drawLine(this.vertexDroneDisplay.get(1).x, this.vertexDroneDisplay.get(1).y,
                this.vertexDroneDisplay.get(2).x, this.vertexDroneDisplay.get(2).y);
        
        g.drawLine(this.vertexDroneDisplay.get(2).x, this.vertexDroneDisplay.get(2).y,
                this.vertexDroneDisplay.get(3).x, this.vertexDroneDisplay.get(3).y);
        
        g.drawLine(this.vertexDroneDisplay.get(3).x, this.vertexDroneDisplay.get(3).y,
                this.vertexDroneDisplay.get(0).x, this.vertexDroneDisplay.get(0).y);
        
        g.drawLine(this.vertexDroneDisplay.get(0).x, this.vertexDroneDisplay.get(0).y,
                this.vertexDroneDisplay.get(2).x, this.vertexDroneDisplay.get(2).y);
        
        g.drawLine(this.vertexDroneDisplay.get(1).x, this.vertexDroneDisplay.get(1).y,
                this.vertexDroneDisplay.get(3).x, this.vertexDroneDisplay.get(3).y);
        
        /* draw the force */
        g.setColor(Color.red);
        
        g.drawLine(this.vertexDroneDisplay.get(0).x, 
                this.vertexDroneDisplay.get(0).y, 
                this.vertexDroneDisplay.get(0).x - this.vertexDroneForce.get(0).x, 
                this.vertexDroneDisplay.get(0).y + this.vertexDroneForce.get(0).y);
        
        g.drawLine(this.vertexDroneDisplay.get(1).x, 
                this.vertexDroneDisplay.get(1).y, 
                this.vertexDroneDisplay.get(1).x - this.vertexDroneForce.get(1).x, 
                this.vertexDroneDisplay.get(1).y + this.vertexDroneForce.get(1).y);
        
        g.drawLine(this.vertexDroneDisplay.get(2).x, 
                this.vertexDroneDisplay.get(2).y, 
                this.vertexDroneDisplay.get(2).x - this.vertexDroneForce.get(2).x, 
                this.vertexDroneDisplay.get(2).y + this.vertexDroneForce.get(2).y);
        
        g.drawLine(this.vertexDroneDisplay.get(3).x, 
                this.vertexDroneDisplay.get(3).y, 
                this.vertexDroneDisplay.get(3).x - this.vertexDroneForce.get(3).x, 
                this.vertexDroneDisplay.get(3).y + this.vertexDroneForce.get(3).y);
        
        /* draw target point */
        g.setColor(Color.YELLOW);
        g.fillOval(this.convertPoint(DroneApp.m_Drone.targetPos).x - 10, this.convertPoint(DroneApp.m_Drone.targetPos).y - 10, 
                20, 20);
        
        g.setColor(Color.ORANGE);
        g.fillOval(this.convertPoint(DroneApp.m_Drone.targetPosFiltered).x - 10, this.convertPoint(DroneApp.m_Drone.targetPosFiltered).y - 10, 
                20, 20);
        //g.drawRect(912, 400, 100, 100);
    }
    
    public void run() {
        this.width = DroneApp.g_FrameMain.getContentPane().getWidth();
        this.height = DroneApp.g_FrameMain.getContentPane().getHeight();
                
        displayGround();
        displayDrone();
        
        //DroneApp.m_Drone.targetPos = this.reconvertPoint(DroneApp.m_MousePos);
        
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public Point convertPoint (Point2D.Double pt2D) {
        Point pt = new Point();
        
        pt.x = (int)((double)this.width * pt2D.getX() / (double)gnd.getWidthMeter());
        pt.y = (int)( ( ( -(double)this.height * ( pt2D.getY() + (double)gnd.getHeightMargin() ) ) / (double)gnd.getHeightMeter() + (double)this.height) );
          
        return pt;
    }
    
    public Point2D.Double reconvertPoint (Point pt) {
        Point2D.Double pt2D = new Point2D.Double();
        
        pt2D.x = (double)pt.x * (double)gnd.getWidthMeter() / (double)this.width;
        pt2D.y = -(((pt.y - (double)this.height) * (double)gnd.getHeightMeter())/ (double)this.height) - (double)gnd.getHeightMargin();
        
        
        return pt2D;
    }
    
    public void setGround (CGround obj) {
        this.gnd = obj;
    }
    
    public void convertGround() {
        Point pt = new Point();
        this.vertexGroundDisplay.clear();
        
        for (int i = 0; i < gnd.getVertex().size() ; i ++) {
            
            pt = convertPoint ( gnd.getVertex().get(i) );
            
            this.vertexGroundDisplay.add(pt);  

        }
    }
    
    public void convertDrone(CDrone obj) {
        Point pt = new Point();
        this.vertexDroneDisplay.clear();
        int size = obj.getVertex().size();
        int i;
        for (i = 0; i < size; i ++) {
            
            pt = convertPoint ( obj.getVertex().get(i) );
            
            this.vertexDroneDisplay.add(pt);  
        }
        
        this.vertexDroneForce.set(0, new Point(2*(int)DroneApp.m_Drone.force.get(0).getX(), 2*(int)DroneApp.m_Drone.force.get(0).getY()));
        this.vertexDroneForce.set(1, new Point(2*(int)DroneApp.m_Drone.force.get(1).getX(), 2*(int)DroneApp.m_Drone.force.get(1).getY()));
        this.vertexDroneForce.set(2, new Point(2*(int)DroneApp.m_Drone.force.get(2).getX(), 2*(int)DroneApp.m_Drone.force.get(2).getY()));
        this.vertexDroneForce.set(3, new Point(2*(int)DroneApp.m_Drone.force.get(3).getX(), 2*(int)DroneApp.m_Drone.force.get(3).getY()));

    }
    
    public void paint(Graphics graphics) {
        drawGround(graphics);
        drawDrone(graphics);
    }
    
}
