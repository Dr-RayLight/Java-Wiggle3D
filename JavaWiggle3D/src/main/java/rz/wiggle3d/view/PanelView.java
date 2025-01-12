package rz.wiggle3d.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class PanelView extends JPanel {

    public PanelView() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
    }

    public static PanelView build() {
        return new PanelView();
    }
}
