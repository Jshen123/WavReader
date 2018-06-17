package io.github.jshen123.wavreader;

import java.io.*;

public class WavReader {
    public static void main(String[] args){
        int min = 0, max =0;

        try{
            WavFile wavFile;
            wavFile = WavFile.loadWav(new File(args[0]));

            for (int sample : wavFile.samples[0]){
                max = Math.max(max, sample);
                min = Math.min(min, sample);

            }

            System.out.println(min);
            System.out.println(max);
        } catch (Exception e){
            System.err.println(e);
        }
    }
}
