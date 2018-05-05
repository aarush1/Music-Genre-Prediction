package com.aarush.tensorflow.audio;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class AudioUtils {

    private static final Logger logger = LoggerFactory.getLogger(AudioUtils.class);

    
    // converting mp3 to wave file format so that missing data from the
    // mp3 file (data which is still compressed and missing 
    // can be regenerated
    public static File convertMp3ToWave(File mp3){

        logger.info("converting {} to wav file", mp3.getName());

        File temp=null;
        try {
            temp = File.createTempFile(mp3.getName().split("\\.")[0],".wav");
            temp.deleteOnExit();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Converter converter = new Converter();
        try {
            converter.convert(mp3.getAbsolutePath(), temp.getAbsolutePath());
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        if(temp != null) {
            logger.info("successfully convert {} to {}", mp3.getName(), temp.getName());
        }
        return temp;
    }

}
