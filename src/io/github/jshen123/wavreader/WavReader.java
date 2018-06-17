package io.github.jshen123.wavreader;

import org.jfree.ui.RefineryUtilities;
//import org.jfree.chart.annotations.XYTextAnnotation;
//import org.jfree.chart.plot.XYPlot

import java.io.*;
import javax.sound.sampled.AudioFormat;


public class WavReader {
    public static void main(String[] args){
        int min = 0, max =0;

        try{
            WavFile wavFile;
            wavFile = WavFile.loadWav(new File(args[0]));

            float[] normalized = new float[wavFile.samples.length];

            for (int i = 0; i < wavFile.samples.length; i++){
                normalized[i] = wavFile.samples[i] / ((float)32767);
                max = Math.max(max, wavFile.samples[i]);
            }

            AudioFormat af = wavFile.audioIn.getFormat();
            float sampleRate = af.getSampleRate();
            int sampleBit = af.getSampleSizeInBits();
            int numSamples = (int) sampleRate * sampleBit;

            String maxStr = "Maximum Value: " + max;
            String numSamplesStr = "Number of Samples: " + numSamples;
            String info = maxStr + "   " + numSamplesStr;

            final Chart graph = new Chart("Waveform Plot", "Waveform", "Seconds", "Amplitude", info, normalized);
            graph.pack();
            RefineryUtilities.centerFrameOnScreen(graph);
            graph.setVisible(true);

            System.out.println(numSamples);

        } catch (Exception e){
            System.err.println(e);
        }
    }
}
