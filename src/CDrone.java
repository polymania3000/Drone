import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CDrone {
    public double M                     = 10;
    
    public double posX_Meter            = 0;
    public double posY_Meter            = 0;
    public double posX_Meter_Old        = 0;
    public double posY_Meter_Old        = 0;
    
    public double angle                 = 0;
    
    public double forceM_N              = 100;
    public double forceMain_N           = 0;
    
    private ArrayList<Point2D.Double> vertex = new ArrayList<Point2D.Double>(); 
    
    //private int posX_Ground;
    //private int posY_Ground;
    
    public CDrone() {
        this.posX_Meter = 400;
        this.posY_Meter = 400;
        
        this.posX_Meter_Old = this.posX_Meter;
        this.posY_Meter_Old = this.posY_Meter;
        
        this.angle = 0;
    }
    
    public void run() {
        
    }
    
    public ArrayList<Point2D.Double> getVertex() {
        return this.vertex;
    }
}
