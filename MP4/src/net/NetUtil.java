package net;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NetUtil {
    public static byte[] convertPNGtoByteArr(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        baos.flush();
        byte[] byteArr = baos.toByteArray();
        baos.close();
        return byteArr;
    }

    public static void convertByteArrToPNG(byte[] byteArr, String outputPath) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArr);
        BufferedImage image = ImageIO.read(bais);
        ImageIO.write(image, "png", new File(outputPath));
    }
}
