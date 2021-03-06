import java.awt.Point;
import java.awt.geom.Point2D;

public class CCoordinate {
    public static Point2D.Double move (Point2D.Double point, double deltaX, double deltaY) {
        Point2D.Double pt;
        pt = point;
        pt.x += deltaX;
        pt.y += deltaY;
        
        return pt;
    }
    
    public static Point2D.Double rotate (Point2D.Double point, Point2D.Double ptOrigin, double angle) {
        Point2D.Double pt;
        pt = point;
        double distance;
        double startAngle;
        double endAngle;
        
        distance = Math.sqrt( (pt.x - ptOrigin.x) * (pt.x - ptOrigin.x) + (pt.y - ptOrigin.y) * (pt.y - ptOrigin.y) );
        
        startAngle = Math.atan2(pt.y - ptOrigin.y, pt.x - ptOrigin.x);       
        endAngle = startAngle + (angle * Math.PI) / 180;
        
        pt.x = ptOrigin.x + Math.cos(endAngle) * distance;
        pt.y = ptOrigin.y + Math.sin(endAngle) * distance;
        
        return pt;
    }
     
    /*
    public static Point localToGround (Point point)
    {
        Point pt;
        
        return pt;
    }
    
    public static Point groundToLocal (Point point)
    {
        Point pt;
        
        return pt;  
    }
    
    public static Point groundToScreen (Point point)
    {
        Point pt;
        
        return pt; 
    }
    
    public static Point screenToGround (Point point)
    {
        Point pt;
        
        return pt;
    }
    */
}
