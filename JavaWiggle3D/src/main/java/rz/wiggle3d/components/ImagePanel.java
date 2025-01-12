package rz.wiggle3d.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {

    private static final String TAG = "[ImagePanel]";

    // ------------------------------------------------------------
    private BufferedImage image;
    private boolean isImageRendered = false;

    public ImagePanel(String imagePath) {
        loadImage(imagePath);
    }

    // ------------------------------------------------------------
    private void loadImage(String imagePath) {
        try {
            System.out.println(TAG + "image path: " + imagePath);
            InputStream is = getClass().getClassLoader().getResourceAsStream(imagePath); // Here is under src folder.
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void clearImage() {
        image = null;
        isImageRendered = false;
        repaint();
    }

    public boolean isImageRendered() {
        return isImageRendered;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            isImageRendered = true;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (image != null) {
            return new Dimension(image.getWidth(), image.getHeight());
        } else {
            return super.getPreferredSize();
        }
    }
}
