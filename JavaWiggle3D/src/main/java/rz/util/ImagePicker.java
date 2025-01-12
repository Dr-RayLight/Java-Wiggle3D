package rz.util;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImagePicker {

    // Method to pick an image
    public static String pick() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"), "Desktop"));

        // Set the file filter to allow only .jpeg, .jpg, .png files
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true; // Always allow directories
                }
                String fileName = file.getName().toLowerCase();
                return fileName.endsWith(".jpeg") || fileName.endsWith(".jpg") || fileName.endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "Image Files (*.jpeg, *.jpg, *.png)";
            }
        });

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(null);

        // If the user selected a file, return its path
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath(); // Return the file path
        } else {
            return null; // No file selected
        }
    }
}