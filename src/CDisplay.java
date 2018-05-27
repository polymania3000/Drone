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
    

    
    private void displayGround() {
        //drawLine(vertexGroundDisplay.get(0).getX(), vertexGroundDisplay.get(0).getY(),
        //        vertexGroundDisplay.get(1).getX(), vertexGroundDisplay.get(1).getY());
    }
    
    private void displayDrone() {
        
    }
    
    private void drawGround(Graphics g) {
        g.drawLine(this.vertexGroundDisplay.get(0).x, this.vertexGroundDisplay.get(0).y,
                this.vertexGroundDisplay.get(1).x, this.vertexGroundDisplay.get(1).y);
    }
    
    private void drawDrone(Graphics g) {
        
    }
    
    public void run() {
        this.width = DroneApp.g_FrameMain.getContentPane().getWidth();
        this.height = DroneApp.g_FrameMain.getContentPane().getHeight();
        
        //this.convertGround();
        //this.convertDrone();
        
        displayGround();
        displayDrone();
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    private Point convertPoint (Point2D.Double pt2D) {
        Point pt = new Point();
        
        pt.x = (int)((double)this.width * pt2D.getX() / (double)gnd.getWidthMeter());
        pt.y = (int)( ( ( -(double)this.height * ( pt2D.getY() + (double)gnd.getHeightMargin() ) ) / (double)gnd.getHeightMeter() + (double)this.height) );
          
        return pt;
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
        
        for (int i = 0; i < obj.getVertex().size() ; i ++) {
            
            pt = convertPoint ( gnd.getVertex().get(i) );
            
            this.vertexDroneDisplay.add(pt);  

        }
    }
    
    public void paint(Graphics graphics) {
        drawGround(graphics);
        drawDrone(graphics);
    }
    
}
