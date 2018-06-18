package io.github.jshen123.wavreader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class Chart extends ApplicationFrame{

    public Chart(final String title, String dataName, String xName, String yName, String info, float[] data){

        super(title);
        final XYSeries series = new XYSeries(dataName);
        for (int i = 0; i < data.length; i++){
            series.add(i, data[i]);
        }

        final XYSeriesCollection collection = new XYSeriesCollection(series);
        final JFreeChart xyChart = ChartFactory.createXYLineChart(
                title,
                xName,
                yName,
                collection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = (XYPlot) xyChart.getPlot();
        XYTextAnnotation textAnnotation = new XYTextAnnotation(info, 7500, 0.7);
        plot.addAnnotation(textAnnotation);

        final ChartPanel chartPanel = new ChartPanel(xyChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(640, 480));
        setContentPane(chartPanel);
    }
}
