package org.xsteel.math.modeling.numerical;

public class Delta {
    private double delta;

    public Delta(double delta){
        this.delta = delta;
    }

    public Delta(double x, double n) { delta = x / (n-1); }

    public Delta(double a, double b, double n){
        delta = (b - a)/(n-1);
    }

    public double getDouble() {
        return delta;
    }

    public static String getDeltaSign(){
        return "∆";
    }

    @Override
    public String toString() {
        return "∆ = " + delta;
    }
}
