package com.aarush.tensorflow.classifiers.utils;

import org.tensorflow.Tensor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class TensorUtils {
    public static Tensor<Float> getImageTensor(BufferedImage image, int imgWidth, int imgHeight) {

        final int channels = 1;

        // System.out.println("width: " + imgWidth + ", height: " + imgHeight);
        // Generate image file to array
        int index = 0;
        FloatBuffer fb = FloatBuffer.allocate(imgWidth * imgHeight * channels);
        // Convert image file to multi-dimension array

        for (int row = 0; row < imgHeight; row++) {
            for (int column = 0; column < imgWidth; column++) {
                int pixel = image.getRGB(column, row);

                float red = (pixel >> 16) & 0xff;
                /*
                float green = (pixel >> 8) & 0xff;
                float blue = pixel & 0xff;*/
                fb.put(index++, red);
            }
        }

        // now you have the FloatBuffer that says how much is the red value of a pixel in the image. 
        // Remember this image was created from the mp3 file
        return Tensor.create(new long[]{1, imgHeight, imgWidth, channels}, fb);
    }
}
