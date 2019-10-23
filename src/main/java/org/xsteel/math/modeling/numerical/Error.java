package org.xsteel.math.modeling.numerical;

public class Error {
    public static double[] accuracy(double[] a, double[] b){
        double[] result = new double[a.length];

        for(int i = 0; i < result.length ; i++){
            result[i] = Math.abs(b[i] - a[i]);
        }

        return result;
    }
}
