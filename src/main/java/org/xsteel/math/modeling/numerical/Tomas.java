package org.xsteel.math.modeling.numerical;

public class Tomas {
    public static double[] solve(double k, double alfa, double T1,
                                 double T2, Partition partition) {
        double[] result = new double[partition.length()];

        Delta deltaX = partition.getDelta();

        int N = partition.length();

        result[0] = T1;

        double A = k / ( deltaX.getDouble() * deltaX.getDouble() );
        double C = A;
        double B = A + C;
        double F = 0;

        double[] alpha = new double[N];
        double[] betta = new double[N];

        alpha[N-1] = k / ( k + deltaX.getDouble() * alfa );
        betta[N-1] = alfa * T2 /( (k/deltaX.getDouble()) + alfa );

        for (int i = N - 2; i >= 0; i--) {
            final double v = B - A * alpha[i + 1];
            alpha[i] = C / v;
            //System.out.print("Alpha = " + alpha[i] + " ");
            betta[i] = (F + A * betta[i+1]) / v;
            //System.out.println("Betta = " + betta[i]);
        }

        for (int i = 1; i < result.length; i++) {
            result[i] = alpha[i-1]*result[i-1] + betta[i-1];
        }

        return result;
    }
}
