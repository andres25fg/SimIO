/**
 * Created by felipe on 17/2/2017.
 */
import java.util.Random;
import java.lang.Math;
public class RandomGenerator {
    Random r = new Random();
    public double getRandom(){
        return r.nextDouble();
    }
    public double uniform(double a, double b){
        return a+(b-a)*r.nextDouble();
    }

    public double normal(double u, double o){
        double r1 = r.nextDouble();
        double r2 = r.nextDouble();


        //double z[]= new double[2];
        //z[1]= Math.pow(-2*Math.log(r1), .5)*Math.sin(2*Math.PI*r2);
        double z = Math.pow(-2*Math.log(r1), .5)*Math.sin(2*Math.PI*r2);
        z=u+o*z;
        //z[2]= Math.pow(-2*Math.log(r1), .5)*Math.cos(2*Math.PI*r2);
        //z[1]=u+o*z[1];
        //z[2]=u+o*z[2];
        return z;
    }

    public double exponential(double l){
        return -1/l*Math.log(r.nextDouble());
    }
}
