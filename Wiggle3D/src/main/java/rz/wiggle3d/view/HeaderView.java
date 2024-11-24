package rz.wiggle3d.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class HeaderView extends JPanel {

    public HeaderView() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
    }

    public static HeaderView build() {
        return new HeaderView();
    }
}
