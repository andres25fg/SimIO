/**
 * Created by felipe on 17/2/2017.
 */
import java.util.Random;
import java.lang.Math;
public class RandomGenerator {
    Random r = new Random();
    public double uniform(double a, double b){

        return a+(b-a)*r.nextDouble();
    }
    public double normal(int u, int o){
        return 0;
    }
    public double exponential(double l){
        return 1-Math.pow(Math.E, l*r.nextDouble());
    }
}
