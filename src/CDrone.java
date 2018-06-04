import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.geometry.Point3D;

public class CDrone {
    public double g                     = 9.8;
    public double M                     = 10;
    public double J                     = 0;
    public double width                 = 3;
    public double height                = 20;
    
    public Point2D.Double pos_Meter     = new Point2D.Double(0, 0);
    //public double posY_Meter            = 0;
    public Point2D.Double pos_Meter_Old = new Point2D.Double(0, 0);
    //public double posY_Meter_Old        = 0;
    
    public double angle_rad             = 0;
    public double angle_deg             = 0;
    public double angle_rad_Old         = 0;
    public double angle_deg_Old         = 0;
    
    public double forceM_N              = M * g;
    //public double forceMain_N           = 0;
    public double torque                = 0.0;
    
    public Point2D.Double acc           = new Point2D.Double(0, 0);
    public double alpha                 = 0;
    
    public Point2D.Double velocity      = new Point2D.Double(0, 0);
    public double omega                 = 0;
    
    public ArrayList<Double> forcePropel = new ArrayList<Double>();
    
    public ArrayList<Point2D.Double> vertex = new ArrayList<Point2D.Double>(); 
    public ArrayList<Point2D.Double> force = new ArrayList<Point2D.Double>();
    
    
    /* control */
    public Point2D.Double targetPos         = new Point2D.Double(0, 0);
    public Point2D.Double targetPosFiltered = new Point2D.Double(0, 0);
    public Point2D.Double targetVelocity    = new Point2D.Double(0, 0);
    public Point2D.Double pPos              = new Point2D.Double(0, 0);
    public Point2D.Double iPos              = new Point2D.Double(0, 0);
    public Point2D.Double pVelocity         = new Point2D.Double(0, 0);
    public Point2D.Double iVelocity         = new Point2D.Double(0, 0);
    
    public double targetAngle               = 0.0;
    public double targetOmega               = 0.0;
    public double pAngle                    = 0.0;
    public double iAngle                    = 0.0;
    public double pOmega                    = 0.0;
    public double iOmega                    = 0.0;
    
    private void posControlRun() {
        Point2D.Double deltaPos = new Point2D.Double(0, 0);
        
        
        if (targetPosFiltered.x < targetPos.x)
        {
            targetPosFiltered.x += 0.01;
        }
        else if (targetPosFiltered.x > targetPos.x)
        {
            targetPosFiltered.x -= 0.01;
        }
        
        if (targetPosFiltered.y < targetPos.y)
        {
            targetPosFiltered.y += 0.01;
        }
        else if (targetPosFiltered.y > targetPos.y)
        {
            targetPosFiltered.y -= 0.01;
        }
        
        deltaPos.x = this.targetPosFiltered.getX() - this.pos_Meter.getX();
        deltaPos.y = this.targetPosFiltered.getY() - this.pos_Meter.getY();
        
        /* x */
        /*
        pPos.x = 20 * deltaPos.x;
        
        if (deltaPos.x > 0.1)
        {
            if (iPos.x < 100)
            {
                iPos.x += 0.01;
            }
        }
        else if (deltaPos.x < -0.1)
        {
            if (iPos.x > -100)
            {
                iPos.x -= 0.01;
            } 
        }
        else
        {
            
        }
        
        this.targetVelocity.x = pPos.x + iPos.x;
        
        this.targetVelocity.x = Math.min(this.targetVelocity.x, 10);
        this.targetVelocity.x = Math.max(this.targetVelocity.x, -10);
        */
        
        this.targetVelocity.x = Math.max(Math.sqrt(2*Math.abs(deltaPos.x) * 1/3), 0.1); /* de-accelerate when approaching target */
        
        if (deltaPos.x < 0) 
        {
            this.targetVelocity.x = - this.targetVelocity.x;
        }
        
        /* y */
        /*
        pPos.y = 20 * deltaPos.y;
        
        if (deltaPos.y > 0.1)
        {
            if (iPos.y < 100)
            {
                iPos.y += 0.01;
            }
        }
        else if (deltaPos.y < -0.1)
        {
            if (iPos.y > -100)
            {
                iPos.y -= 0.01;
            } 
        }
        else
        {
            
        }
        
        this.targetVelocity.y = pPos.y + iPos.y;
        
        this.targetVelocity.y = Math.min(this.targetVelocity.y, 10);
        this.targetVelocity.y = Math.max(this.targetVelocity.y, -10);
        */
        
        this.targetVelocity.y = Math.max(Math.sqrt(2*Math.abs(deltaPos.y) * g/2) - 2, 0.1); /* de-accelerate when approaching target */
        
        if (deltaPos.y < 0) 
        {
            this.targetVelocity.y = - this.targetVelocity.y;
        }
        
    }
    
    private void velocityControlRun() {
        Point2D.Double deltaVelocity = new Point2D.Double(0, 0);
        deltaVelocity.x = this.targetVelocity.x - this.velocity.x;
        deltaVelocity.y = this.targetVelocity.y - this.velocity.y;
        //double force = 0;
        
        /* x */
        this.pVelocity.x = 50 * deltaVelocity.x;
        
        if (deltaVelocity.x > 0.1)
        {
            if (iVelocity.x < 50)
            {
                iVelocity.x += 0.01;
            }
        }
        else if (deltaVelocity.x < -0.1)
        {
            if (iVelocity.x > -50)
            {
                iVelocity.x -= 0.01;
            } 
        }
        else
        {
            
        }

        this.targetAngle = -this.pVelocity.x - iVelocity.x;
        
        this.targetAngle = Math.min(this.targetAngle, Math.min(10*deltaVelocity.x, 15));
        this.targetAngle = Math.max(this.targetAngle, Math.max(-10*deltaVelocity.x, -15));
        
        //force = -pVelocity.x - iVelocity.x;
        //force = Math.min(force, 50);
        //force = Math.max(force, -50);
        
        /*
        if (force > 0)
        {
            //this.forcePropel.set(2, Math.abs(force));
            this.forcePropel.set(3, 0.0);
        }
        else
        {
            this.forcePropel.set(2, 0.0);
            //this.forcePropel.set(3, Math.abs(force));
        }
        */
        
        /* y */
        this.pVelocity.y = 100 * deltaVelocity.y;
        
        if (deltaVelocity.y > 0.1)
        {
            if (iVelocity.y < 500)
            {
                iVelocity.y += 0.01;
            }
        }
        else if (deltaVelocity.y < -0.1)
        {
            if (iVelocity.y > -500)
            {
                iVelocity.y -= 0.01;
            } 
        }
        else
        {
            
        }
        
        this.forcePropel.set(0, Math.min(Math.max(10, (pVelocity.y + iVelocity.y)/2), 200));
        this.forcePropel.set(1, Math.min(Math.max(10, (pVelocity.y + iVelocity.y)/2), 200));
        
        //this.targetVelocity.y = Math.min(this.targetVelocity.y, 100);
        //this.targetVelocity.y = Math.max(this.targetVelocity.y, -100);
    }
    
    private void accControlRun() {
        
    }
    
    private void angleControlRun() {
        double deltaAngle = 0;
        
        //this.targetAngle = 10;
        
        deltaAngle = ((this.targetAngle + 360.0*2) - (this.angle_deg + 360.0)) % 360;
        
        if (deltaAngle > 180)
        {
            deltaAngle = - (360 - deltaAngle);
        }

        /*
        pAngle = 10 * deltaAngle;
        
        if (deltaAngle > 0.1)
        {
            iAngle += 0.01;  
        }       
        else if (deltaAngle < -0.1)
        {
            iAngle -= 0.01;   
        }
        else
        {
            
        }
        
        this.targetOmega = pAngle + iAngle;
        
        if (deltaAngle > 0.5 * Math.PI / 180)
        {
            this.targetOmega = 0.1;
        }
        else if (deltaAngle < -0.5 * Math.PI / 180)
        {
            this.targetOmega = -0.1;
        }
        else
        {
            
        }
        */
        this.targetOmega = deltaAngle *1;
        
        //this.targetOmega = Math.max(Math.sqrt(2*Math.abs(deltaAngle) * 0.1/2), 0.05);
        this.targetOmega = Math.max(this.targetOmega, -10);
        this.targetOmega = Math.min(this.targetOmega, 10);
        
    }
    
    private void omegaControlRun() {
        double deltaOmega = 0;
        double force = 0;
        
        deltaOmega = this.targetOmega - this.omega;
        
        pOmega = 500 * deltaOmega;
        
        if (deltaOmega > 0.0)
        {
            iOmega += 0.01;
        }
        else if (deltaOmega < 0.0)
        {
            iOmega -= 0.01;
        }
        iOmega = Math.min(iOmega, 100);
        iOmega = Math.max(iOmega, -100);
        
        
        force = pOmega + iOmega;
        force = Math.min(force, 30);
        force = Math.max(force, -30);
        
        if (force > 0)
        {
            this.forcePropel.set(2, Math.abs(force));
            this.forcePropel.set(3, 0.0);
        }
        else
        {
            this.forcePropel.set(2, 0.0);
            this.forcePropel.set(3, Math.abs(force));
        }
    }
    
    private void vertexRun() {
        this.vertex.get(0).x = this.pos_Meter.getX() - this.width/2;
        this.vertex.get(0).y = this.pos_Meter.getY() - this.height/2;
        
        this.vertex.get(1).x = this.pos_Meter.getX() + this.width/2;
        this.vertex.get(1).y = this.pos_Meter.getY() - this.height/2;
        
        this.vertex.get(2).x = this.pos_Meter.getX() + this.width/2;
        this.vertex.get(2).y = this.pos_Meter.getY() + this.height/2;
        
        this.vertex.get(3).x = this.pos_Meter.getX() - this.width/2;
        this.vertex.get(3).y = this.pos_Meter.getY() + this.height/2;
        
        
        this.vertex.set(0, CCoordinate.rotate(this.vertex.get(0), this.pos_Meter, this.angle_deg));
        this.vertex.set(1, CCoordinate.rotate(this.vertex.get(1), this.pos_Meter, this.angle_deg));
        this.vertex.set(2, CCoordinate.rotate(this.vertex.get(2), this.pos_Meter, this.angle_deg));
        this.vertex.set(3, CCoordinate.rotate(this.vertex.get(3), this.pos_Meter, this.angle_deg));
    }
    
    public CDrone() {
        this.pos_Meter.x = 50;
        this.pos_Meter.y = 30;
        
        this.pos_Meter_Old = this.pos_Meter;
        //this.pos_Meter_Old.y = this.posY_Meter;
        
        this.angle_rad = 0;
        this.angle_deg = 0;
        
        this.angle_rad_Old = this.angle_rad;
        this.angle_deg_Old = this.angle_deg;
        
        this.vertex.add(new Point2D.Double(this.pos_Meter.getX() - this.width/2, this.pos_Meter.getY() - this.height/2));
        this.vertex.add(new Point2D.Double(this.pos_Meter.getX() + this.width/2, this.pos_Meter.getY() - this.height/2));
        this.vertex.add(new Point2D.Double(this.pos_Meter.getX() + this.width/2, this.pos_Meter.getY() + this.height/2));
        this.vertex.add(new Point2D.Double(this.pos_Meter.getX() - this.width/2, this.pos_Meter.getY() + this.height/2));
           
        this.forcePropel.add(0.0);
        this.forcePropel.add(0.0);
        this.forcePropel.add(0.0);
        this.forcePropel.add(0.0);
        
        this.force.add(new Point2D.Double(0, 0));
        this.force.add(new Point2D.Double(0, 0));
        this.force.add(new Point2D.Double(0, 0));
        this.force.add(new Point2D.Double(0, 0));
        
        this.targetPos.x = 30;
        this.targetPos.y = 50;
        
        
        
        //this.targetPosFiltered = this.pos_Meter;
        this.targetPosFiltered = this.targetPos;
    
    }
    
    public void run() {
        this.pos_Meter_Old = this.pos_Meter;
        this.angle_rad_Old = this.angle_rad;
        this.angle_deg_Old = this.angle_deg;
        
        posControlRun();
        velocityControlRun();
        accControlRun();
        angleControlRun();
        omegaControlRun();
        
        
        
        /* calculate x/y movement */
        //this.forcePropel.set(0, 10.0);
        //this.forcePropel.set(1, 10.0);
        //this.forcePropel.set(2, 0.0);
        //this.forcePropel.set(3, 0.0);
        
        this.force.set(0, new Point2D.Double(0, this.forcePropel.get(0)));
        this.force.set(1, new Point2D.Double(0, this.forcePropel.get(1)));
        this.force.set(2, new Point2D.Double(-this.forcePropel.get(2), 0));
        this.force.set(3, new Point2D.Double(this.forcePropel.get(3), 0));
        
        this.force.set(0, CCoordinate.rotate(this.force.get(0), new Point2D.Double(0, 0), this.angle_deg));
        this.force.set(1, CCoordinate.rotate(this.force.get(1), new Point2D.Double(0, 0), this.angle_deg));
        this.force.set(2, CCoordinate.rotate(this.force.get(2), new Point2D.Double(0, 0), this.angle_deg));
        this.force.set(3, CCoordinate.rotate(this.force.get(3), new Point2D.Double(0, 0), this.angle_deg));
        
        this.acc.x = (this.force.get(0).getX() 
                + this.force.get(1).getX() 
                + this.force.get(2).getX() 
                + this.force.get(3).getX()) / this.M;
        
        this.acc.y = ( - this.forceM_N
                + this.force.get(0).getY() 
                + this.force.get(1).getY() 
                + this.force.get(2).getY() 
                + this.force.get(3).getY()) / this.M;
        
        this.velocity.x += this.acc.x * (double)1 / (double)1000;
        this.velocity.y += this.acc.y * (double)1 / (double)1000;
        
        this.pos_Meter.x += this.velocity.x * (double)1 / (double)1000;
        this.pos_Meter.y += this.velocity.y * (double)1 / (double)1000;
        
       
        /* calculate rotational movement */
        this.J = this.M * (this.width*this.width + this.height*this.height) / 3.0;
        
        
        this.torque = - this.forcePropel.get(0) * this.width/2 
                      + this.forcePropel.get(1) * this.width/2 
                      + this.forcePropel.get(2) * this.height/2
                      - this.forcePropel.get(3) * this.height/2;
        
        this.alpha = this.torque / this.J;
        this.omega += this.alpha * 0.001;
        this.angle_rad += this.omega * 0.001;
        
        angle_rad %= 2 * Math.PI;
        
        this.angle_deg = (this.angle_rad * 180) / Math.PI; 
        
        
        /* update the shape */
        vertexRun();
    }
    
    public ArrayList<Point2D.Double> getVertex() {
        return this.vertex;
    }
}
