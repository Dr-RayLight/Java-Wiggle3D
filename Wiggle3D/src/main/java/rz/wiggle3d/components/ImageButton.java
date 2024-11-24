package rz.wiggle3d.components;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

// Class to encapsulate PNG rendering into an ImageButton
public class ImageButton extends JButton {

    // Constructor that initializes the button with a PNG image from a URL
    public ImageButton(String pngUri, int width, int height) {
        try {
            // Load the PNG image from the URL
            ImageIcon imageIcon = new ImageIcon(new URL(pngUri));

            // Resize the icon to fit the desired dimensions
            Image img = imageIcon.getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Set the button's icon
            this.setIcon(new ImageIcon(scaledImg));

        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Display error if the PNG cannot be loaded
            this.setText("Image Load Failed");
        }

        this.setPreferredSize(new Dimension(width, height)); // Adjust size as needed
        this.setContentAreaFilled(false); // Make button's background transparent
        this.setBorderPainted(false); // Remove border
    }

    // Public method to update the PNG image using a new URI
    public void updateImage(String path, int width, int height) {
        try {
            // Load the new PNG image from the URL
            ImageIcon imageIcon = new ImageIcon(path);

            // Resize the icon to fit the desired dimensions
            Image img = imageIcon.getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Update the button's icon
            this.setIcon(new ImageIcon(scaledImg));
            this.setText(null); // Clear any error text if successful
        } catch (Exception e) {
            e.printStackTrace();
            this.setIcon(null);
            this.setText("Image Load Failed");
        }
    }
}
