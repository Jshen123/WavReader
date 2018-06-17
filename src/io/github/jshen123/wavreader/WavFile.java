package io.github.jshen123.wavreader;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;

public class WavFile {
    private File file;
    public int[] samples;
    public AudioInputStream audioIn;

    public static WavFile loadWav (File file){
        WavFile wavFile = new WavFile();
        wavFile.file = file;
        wavFile.samples = null;
        wavFile.audioIn = null;

        try {
            // load the audio input stream from the wav file
            wavFile.audioIn = getAudioInputStream(wavFile.file);

            // obtain the byte size of the frame and the stream length
            // set the size of the byte array as frame size * stream length
            int byteSize = wavFile.audioIn.getFormat().getFrameSize();
            int streamLength = (int) wavFile.audioIn.getFrameLength();
            byte[] bytes = new byte[byteSize * streamLength];

            // read the data of the wav file
            int data = wavFile.audioIn.read(bytes);
            int len = bytes.length;
            wavFile.samples = new int[streamLength];

            int i = 0;
            int j;
            int low, high;

            for (j = 0; j < len;){
                    low = (int) bytes[j++];
                    high = (int) bytes[j++];
                    // convert a byte array to a unsigned short
                    wavFile.samples[i++] = (high << 8) + (low & 0x00ff);
            }

        } catch (Exception exp) {

            exp.printStackTrace();

        } finally {

            try {

                wavFile.audioIn.close();

            } catch (Exception e) {

            }
        }
        return wavFile;
    }
}
