import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CGround {
    private final int width_Meter = 100;              // 100 meters
    private final int height_Meter = 75;              // 75 meters including space under ground
    private final int heightMargin_Meter = 10;       // 10 meters under ground to display
    
    ArrayList<Point2D.Double> vertexList = new ArrayList<Point2D.Double>();
    
    CGround() {
        
        this.vertexList.add(new Point2D.Double(0, 0));
        this.vertexList.add(new Point2D.Double((double)this.width_Meter, 0));
    }
    
    public ArrayList<Point2D.Double> getVertex() {
        return this.vertexList;
    }
    
    public int getWidthMeter() {
        return this.width_Meter;
    }
    
    public int getHeightMeter() {
        return this.height_Meter;
    }
    
    public int getHeightEffective() {
        return (this.height_Meter - this.heightMargin_Meter);
    }
    
    public int getHeightMargin() {
        return this.heightMargin_Meter;
    }
    
    public void run() {
        
    }
}
