package com.router.HammedBurg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Launcher {
    public static final int SCALE = 1;
    public static void main(String[] args) {
        Path source = Paths.get("hammedburger.jpg");
        Path target = Paths.get("output.png");

        try(InputStream is = new FileInputStream(source.toFile())) {
            resize(is, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resize(InputStream input, Path target) throws IOException {
        BufferedImage original = ImageIO.read(input);
        int W = original.getWidth();
        int H = original.getHeight();

        Image resized = original.getScaledInstance(W * SCALE, H * SCALE, Image.SCALE_SMOOTH);

        String s = target.getFileName().toString();
        String fileExt = s.substring(s.lastIndexOf(".") + 1);

        ImageIO.write(convert(resized), fileExt, target.toFile());
    }

    public static BufferedImage convert(Image img) {
        if(img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bi.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.dispose();
        return bi;
    }
}
