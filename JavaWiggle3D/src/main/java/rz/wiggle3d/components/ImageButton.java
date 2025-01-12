package rz.wiggle3d.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

// Class to encapsulate PNG rendering into an ImageButton
public class ImageButton extends JButton {

    // ------------------------------------------------------------
    // Constructor to create an ImageButton from the given image resource path
    public ImageButton(String imagePath, int width, int height) {
        // Load the image from resources
        BufferedImage image = loadImage(imagePath);

        if (image != null) {
            Image scaledImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaledImage));
        } else {
            // Handle the case where the image wasn't found
            System.out.println("Image not found: " + imagePath);
        }

        // Set a default size for the button
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
    }

    // ------------------------------------------------------------
    // Load the image from resources folder
    private BufferedImage loadImage(String imagePath) {
        // Load the image using getClassLoader
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream(imagePath);

        if (imageStream != null) {
            try {
                return ImageIO.read(imageStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Image not found in path: " + imagePath);
        }

        return null;
    }
}
