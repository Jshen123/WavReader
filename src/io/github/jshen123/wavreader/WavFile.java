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
            /* load the audio input stream from the wav file */
            wavFile.audioIn = getAudioInputStream(wavFile.file);

            /*
            obtain the byte size of the frame and the stream length;
            set the size of the byte array as frame size * stream length
            */
            int byteSize = wavFile.audioIn.getFormat().getFrameSize();
            int streamLength = (int) wavFile.audioIn.getFrameLength();
            byte[] bytes = new byte[byteSize * streamLength];

            int len = bytes.length;

            /*
            read byte per byte of the wav file from the specified starting and ending position;
            store the read value into the byte array
            */
            int data = wavFile.audioIn.read(bytes, 0 , len);

            wavFile.samples = new int[len/2];

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
