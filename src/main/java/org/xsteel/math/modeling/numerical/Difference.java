package org.xsteel.math.modeling.numerical;

public class Difference {
    /**
     * return:
     *      Forward Scheme ( f(x[i] + ∆x) - f(x[i]) ) / ∆x;
     */
    public static double[] forward(Function f, Delta delta){
        System.out.print("forward(function, ∆x): ");
        double[] solutions = new double[f.getDomain().length];

        for (int i = 0; i < solutions.length - 1; i++){
            solutions[i] = (f.value(i + 1) - f.value(i))
                    / delta.getDouble();
        }
        System.out.println("Complete");
        return solutions;
    }


    /***
     *
     * @param u - function
     * @param partition - set of arguments
     * @return - array of differences
     */
    public static double[] forward(Function u, Partition partition) {
        double[] solutions = new double[partition.length()];

        System.out.println(solutions.length);

        for (int i = 0; i < solutions.length - 1; i++){
            solutions[i] = (u.value(i + 1) - u.value(i))
                    / partition.getDelta().getDouble();
        }

        return solutions;
    }

    public static double[] backward(Function u, Partition partition) {
        double[] solutions = new double[u.getRange().length];

        try {
            for (int i = 0; i < solutions.length; i++) {
                solutions[i] = (u.value(i) - u.value(i - 1))
                        / partition.getDelta().getDouble();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return solutions;
    }

    public static double[] central(Function u, Partition partition) {
        double[] solutions = new double[partition.length()];

        try {
            for (int i = 0; i < solutions.length; i++) {
                solutions[i] = (u.value(i + 1) - u.value(i - 1))
                       / (2 * partition.getDelta().getDouble());
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return solutions;
    }
}
