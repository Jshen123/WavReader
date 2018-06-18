package io.github.jshen123.wavreader;

import org.jfree.ui.RefineryUtilities;
//import org.jfree.chart.annotations.XYTextAnnotation;
//import org.jfree.chart.plot.XYPlot

import java.awt.*;
import javax.swing.*;


public class WavReader {
    public static void main(String[] args){
//        int min = 0, max =0;

        try{

            JFrame frame = new JFrame("File Opener");
            FileOpener opened = new FileOpener();
            frame.setContentPane(opened.panelMain);
            frame.setPreferredSize(new Dimension(320, 240));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        } catch (Exception e){
            System.err.println(e);
        }
    }
}
