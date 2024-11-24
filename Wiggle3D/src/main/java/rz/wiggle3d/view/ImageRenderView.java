package rz.wiggle3d.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class ImageRenderView extends JPanel {

    public ImageRenderView() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
    }

    public static ImageRenderView build() {
        return new ImageRenderView();
    }
}