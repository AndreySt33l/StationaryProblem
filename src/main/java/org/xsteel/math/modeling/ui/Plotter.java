package org.xsteel.math.modeling.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class Plotter extends JFrame{
    private String name;
    private XYSeriesCollection dataset;

    public Plotter(String plotName){
        dataset = new XYSeriesCollection();
        this.name = plotName;
    }

    public void initUI() {
        XYDataset dataset = this.dataset;
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );

        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Developed by xsteel");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addFunction(String name, double[][] function) {
        XYSeries series = new XYSeries(name);
        for (int i = 0; i < function.length; i++) {
            series.add(function[0][i], function[1][i]);
        }
        dataset.addSeries(series);
    }

    public void addFunction(String name, double[] funcValues, double[] partition) {
        XYSeries series = new XYSeries(name);
        for (int i = 0; i < funcValues.length; i++) {
            series.add(partition[i], funcValues[i]);
        }
        dataset.addSeries(series);
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "a title",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.GRAY);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.WHITE);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.WHITE);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(name,
                        new Font("sans-serif", Font.BOLD, 18)
                )
        );

        return chart;
    }
}