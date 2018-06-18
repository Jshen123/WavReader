package io.github.jshen123.wavreader;

import org.jfree.ui.RefineryUtilities;

import javax.sound.sampled.AudioFormat;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileOpener {
    private JButton openFileButton;
    public JPanel panelMain;

    private final JFileChooser openFileSelector;

    public FileOpener() {

        openFileSelector = new JFileChooser();
        openFileSelector.setCurrentDirectory(new File("/home"));
        openFileSelector.setFileFilter(new FileNameExtensionFilter("Wav Audio", "wav"));

        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileSelector.showOpenDialog(null);

//                    try{
                        int max = 0;

                        WavFile wavFile = WavFile.loadWav(openFileSelector.getSelectedFile());

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

            }
        });
    }
}
