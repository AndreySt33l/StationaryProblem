package org.xsteel.math.modeling.numerical;

import java.util.Arrays;

public class Function {
    private double[] domain;
    private double[] range;

    public Function(double[] domain, double[] range) {
        this.domain = domain;
        this.range = range;
    }

    public double value(int index) {
        return range[index];
    }

    public double[] getDomain() {
        return domain;
    }

    public void setDomain(double[] domain) {
        this.domain = domain;
    }

    public double[] getRange() {
        return range;
    }

    public void setRange(double[] range) {
        this.range = range;
    }

    public double[][] getFunction(){
        return new double[][]{domain, range};
    }

    @Override
    public String toString() {
        return "Function{" +
                "domain=" + Arrays.toString(domain) +
                ", range=" + Arrays.toString(range) +
                '}';
    }
}
