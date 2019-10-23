package org.xsteel.math.modeling.numerical;

import java.util.Arrays;

public class Partition {
    private double[] partition;
    private Delta delta;

    public Partition(Delta delta, int n) {
        partition = new double[n];
        this.delta = delta;

        partition[0] = 0;

        initPartition();
    }

    public Partition (double leftBound, Delta delta, int n) {
        partition = new double[n];
        this.delta = delta;

        partition[0] = leftBound;

        initPartition();
    }

    public Partition (double a, double b, int n) {
        partition = new double[n];
        this.delta = new Delta(a, b, n);

        partition[0] = a;

        initPartition();
    }

    private void initPartition() {
        for (int i = 1; i < partition.length; i++) {
            partition[i] = partition[0] + i * delta.getDouble();
        }
    }

    public double[] getPartition() {
        return partition;
    }

    public int length(){
        return partition.length;
    }

    public double get(int index) {
        return partition[index];
    }

    public Delta getDelta() {
        return delta;
    }

    @Override
    public String toString() {
        return "\nP = " + Arrays.toString(partition) +
                "\n" + delta.toString();
    }
}
