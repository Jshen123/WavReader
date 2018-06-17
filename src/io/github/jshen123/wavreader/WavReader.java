package io.github.jshen123.wavreader;

import java.io.*;
import javax.sound.sampled.AudioFormat;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;


public class WavReader {
    public static void main(String[] args){
        int min = 0, max =0;

        try{
            WavFile wavFile;
            wavFile = WavFile.loadWav(new File(args[0]));

            for (int i = 0; i < wavFile.samples.length; i++){
                max = Math.max(max, wavFile.samples[i]);
                min = Math.min(min, wavFile.samples[i]);
            }

            AudioFormat af = wavFile.audioIn.getFormat();
            float sampleRate = af.getSampleRate();
            int sampleBit = af.getSampleSizeInBits();
            int numSamples = (int) sampleRate * sampleBit;

            System.out.println(numSamples);

        } catch (Exception e){
            System.err.println(e);
        }
    }
}
