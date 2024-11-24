package rz.wiggle3d.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.JPanel;

import rz.util.PathUtil;
import rz.wiggle3d.components.ImageButton;

public class HeaderView extends JPanel {

    private static final int IMAGE_BUTTON_WIDTH = 50;
    private static final int IMAGE_BUTTON_HEIGHT = 50;

    public HeaderView() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        ImageButton browseBtn = new ImageButton(PathUtil.ICON_FOLDER.get(), IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        browseBtn.setBackground(Color.WHITE);
        
        ImageButton refreshBtn = new ImageButton(PathUtil.ICON_REFRESH.get(), IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        refreshBtn.setBackground(Color.WHITE);

        ImageButton deleteBtn = new ImageButton(PathUtil.ICON_DELETE.get(), IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        deleteBtn.setBackground(Color.WHITE);

        Box box = Box.createHorizontalBox();
        box.add(browseBtn);
        box.add(refreshBtn);
        box.add(deleteBtn);

        add(box);
    }

    public static HeaderView build() {
        return new HeaderView();
    }
}
