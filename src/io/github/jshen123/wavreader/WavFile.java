package io.github.jshen123.wavreader;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;

public class WavFile {
    private File file;
    public int[][] samples;

    public static WavFile loadWav (File file){
        WavFile wavFile = new WavFile();
        wavFile.file = file;
//        wavFile.samples = null;

        AudioInputStream audioIn = null;
        try {
            // load the audio input stream from the wav file
            audioIn = getAudioInputStream(wavFile.file);

            // obtain the number of channels
            int numChannels = audioIn.getFormat().getChannels();

            // obtain the byte size of the frame and the stream length
            // set the size of the byte array as frame size * stream length
            int byteSize = audioIn.getFormat().getFrameSize();
            int streamLength = (int) audioIn.getFrameLength();
            byte[] bytes = new byte[byteSize * streamLength];

            // read the data of the wav file
            int data = audioIn.read(bytes);
            int len = bytes.length;
            wavFile.samples = new int[numChannels][streamLength];
//            wavFile.samples = new int[numChannels][len];

            int i = 0;
            int j,k;
            int low, high;

            for (j = 0; j < len;){
                for (k = 0; k < numChannels; k++){
                    low = (int) bytes[j++];
                    high = (int) bytes[j++];
                    // convert a byte array to a unsigned short
                    wavFile.samples[k][i] = (high << 8) + (low & 0x00ff);
                }
                i++;
            }

        } catch (Exception exp) {

            exp.printStackTrace();

        } finally {

            try {

                audioIn.close();

            } catch (Exception e) {

            }
        }
        return wavFile;
    }
}
