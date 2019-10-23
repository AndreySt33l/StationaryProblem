package org.xsteel.math.modeling;

import org.xsteel.math.modeling.numerical.*;
import org.xsteel.math.modeling.numerical.Error;
import org.xsteel.math.modeling.ui.ConsoleTable;
import org.xsteel.math.modeling.ui.Plotter;

import java.util.ArrayList;

public class UnstationaryProblem {

    public static void main(String[] args) {
        System.out.println("Stationary Problem of Heat Members");
        System.out.println("Initial conditions: ");
        int N = 10;
        double k = 0.48;
        double l = 100;
        double T1 = 5;
        double T2 = 20;
        double alfa = 0.37;

        /*Add time*/

        System.out.println("\t(1) X divided into " + N + " parts");
        System.out.println("\t(2) Thermal conductivity: k = " + k);
        System.out.println("\t(3) Rod length: l = " + l);
        System.out.println("\t(4) Initial temp left-side: T1 = " + T1);
        System.out.println("\t(5) Initial temp right-side: T2 = " + T2 + "\n");

        /*Analytical Solution*/
        Delta deltaX = new Delta(l, N);

        Partition P1 = new Partition(deltaX, N);

        double C1, C2;

        C2 = k * T1;

        C1 = alfa * (T2 - T1)
                / (1 + (alfa/k*l));



        double[] u = new double[P1.length()];

        for (int index = 0; index < u.length; index++){
            u[index] = C1 /k  * P1.get(index) + C2/k; //exact
        }

        /*Numerical Solution*/
        double[] y = Tomas.solve(k, alfa, T1, T2, P1);

        /*Error*/

        double[] error = Error.accuracy(u, y);

        ArrayList<String> headers = new ArrayList<String>();
        headers.add("Partiton (x[i])");
        headers.add("Analytical Solution");
        headers.add("Numerical Approximation");
        headers.add("Error (" + Delta.getDeltaSign() + ")");

        ConsoleTable ct = new ConsoleTable(headers, buildContent(y, u, error, P1.getPartition()));

        ct.printTable();


        /*Plot Function*/
        Plotter plotter = new Plotter("Stationary problem of heat members");
        Function func = new Function(P1.getPartition(), u);
        Function func2 = new Function(P1.getPartition(), y);
        Function func3 = new Function(P1.getPartition(), error);

        plotter.addFunction("u", func.getFunction());
        plotter.addFunction("y", func2.getFunction());
        plotter.addFunction("Error", func3.getFunction());

        plotter.initUI();
        plotter.setVisible(true);
    }

    public static ArrayList< ArrayList<String> > buildContent(double[] exact, double[] numerical,
                                                              double[] error, double[] partition) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < numerical.length; i++){
            ArrayList<String> list = new ArrayList<String>();
            list.add(Double.toString(partition[i]));
            list.add(Double.toString(exact[i]));
            list.add(Double.toString(numerical[i]));
            list.add(Double.toString(error[i]));
            result.add(list);
        }

        return result;
    }
}
