import java.io.*;

public class simulation{
    static double cd = 0.5;
    static double cl = 0.25;
    static double p = 1.225;
    //mass in kg
    static double m = 0.4;
    //radius is in meters
    static double r = 0.11;
    static double g = 9.8;
    static double a = Math.PI*Math.pow(r, 2);

    static double vxi = 12;
    static double vyi = 3;
    static double vzi = 18;

    static double dt = 0.01;

    static double wxi = 0.2;
    static double wyi = 0.2;
    static double wzi = 0.2;
    
    static Double x = 0.0;
    static Double y = 0.0;
    static Double z = 0.0;

    public static void main(String[] args) throws IOException{
        FileWriter fw = new FileWriter("coordinates.out");
        //find velocity due to force caused by velocity from previous increment, multiply it by the time to get 
        //travel distanes in a single increment on the x, y, and z axis
        
        double prevx = vxi;
        double prevy = vyi;
        double prevz = vzi;
        double prevwx = wxi;
        double prevwy = wyi;
        double prevwz = wzi;

        // double test = 0;
        fw.write(x.toString() + " " + y.toString() + " " + z.toString() + "\n");
        System.out.println(a);
        while(z>=0){
            x += prevx*dt;
            y += prevy*dt;
            z += prevz*dt;
            fw.write(x.toString() + " " + y.toString() + " " + z.toString() + "\n");
            prevy = yvel(prevx, prevz, prevy, dt, prevwx, prevwz, prevwy);
            prevx = xvel(prevx, dt);
            prevz = zvel(prevz, dt);
            
            prevwy = angvy(prevwy, dt);
            prevwx = angvx(prevwx, dt);
            prevwz = angvz(prevwz, dt);
            // test+=dt;
        }
        fw.close();
    }

    //Forces
    public static double zforce(double v){
        double ret = -(Math.abs(v)/v)*(0.5*cd*Math.pow(v, 2)*p*a)-(g*m);
        if(z==0.0) ret += g*m;
        return ret;
    }
    public static double xforce(double v){
        double ret = -(0.5*cd*Math.pow(v, 2)*p*a);
        return ret;
    }
    public static double yforce(double vx, double vz, double v, double t, double prevwx, double prevwz, double prevwy){
        double wz = angvz(prevwz, t);
        double wx = angvx(prevwx, t);
        // double wy = angvy(prevwy, t);
        //(1/2) * p * A * Math.pow(v, 2) * (cl*(wz*vx-wx*)) - cl;
        double ret = 0.5 * p * a * Math.pow(v, 2) * (cl*(wz*vx-wx*vz)) - cd;
        return ret;
    }

    public static double xvel(double v, double t){
        //this equation got messed up
        return (xforce(v)/m)*t + v;
    }
    public static double yvel(double vx, double vz, double v, double t, double prevwx, double prevwz, double prevwy){
        return (((yforce(vx, vz, v, t, prevwx, prevwz, prevwy)/m)*t)+v);
    }
    public static double zvel(double v, double t){
        return (((zforce(v)/m)*t)+v);
    }

    //drag forces
    public static double dragy(double v){
        return 0.5*p*Math.pow(v, 2)*cd*a;
    }
    public static double dragx(double v){
        return 0.5*p*Math.pow(v, 2)*cd*a;
    }
    public static double dragz(double v){
        return 0.5*p*Math.pow(v, 2)*cd*a;
    }

    //angular velocity
    public static double angvy(double prevwy, double t){
        return (((dragy(prevwy)/m)*t)+prevwy);
    }
    public static double angvx(double prevwx, double t){
        return (((dragy(prevwx)/m)*t)+prevwx);
    }
    public static double angvz(double prevwz, double t){
        return (((dragy(prevwz)/m)*t)+prevwz);
    }
}


//Flow: at every increment, there is a new drag force that depends on the previously incremented velocity
//use drag force to calculate new angular velocity, which takes in the previously incremented angular velocity as the parameter
//replace old angular vel with new angular vel
//use new angular velocity of x and z to calculate yForce
//Force functions also require previously incremented velocity
//use new forces to calculate new velocities
//reset prev velocities to new velocities