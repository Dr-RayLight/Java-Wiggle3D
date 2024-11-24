package rz.wiggle3d.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.function.Function;

import javax.swing.Box;
import javax.swing.JPanel;

import rz.util.ImagePicker;
import rz.util.PathUtil;
import rz.wiggle3d.components.ImageButton;
import rz.wiggle3d.manager.EventManager;
import rz.wiggle3d.manager.EventTask;
import rz.wiggle3d.manager.EventManager.EventType;

public class HeaderView extends JPanel implements ActionListener {

    private static final String TAG = "[HeaderView]";
    private static final int IMAGE_BUTTON_WIDTH = 80;
    private static final int IMAGE_BUTTON_HEIGHT = 80;

    private static final String BUTTON_NAME_BROWSE = "Browse";
    private static final String BUTTON_NAME_REFRESH = "Refresh";
    private static final String BUTTON_NAME_DELETE = "Delete";

    public HeaderView() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Browse Image Icon Button
        ImageButton browseBtn = new ImageButton(PathUtil.ICON_FOLDER.get(), IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        browseBtn.setBackground(Color.WHITE);
        browseBtn.setText(BUTTON_NAME_BROWSE);
        browseBtn.addActionListener(this);

        // Refresh Image Icon Button
        ImageButton refreshBtn = new ImageButton(PathUtil.ICON_REFRESH.get(), IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        refreshBtn.setBackground(Color.WHITE);
        refreshBtn.setText(BUTTON_NAME_REFRESH);
        refreshBtn.addActionListener(this);

        // Delete Image Icon Button
        ImageButton deleteBtn = new ImageButton(PathUtil.ICON_DELETE.get(), IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        deleteBtn.setBackground(Color.WHITE);
        deleteBtn.setText(BUTTON_NAME_DELETE);
        deleteBtn.addActionListener(this);

        Box box = Box.createHorizontalBox();
        box.add(browseBtn);
        box.add(refreshBtn);
        box.add(deleteBtn);

        add(box);
    }

    public static HeaderView build() {
        return new HeaderView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Function<String, EventTask<?>> EventTaskCreator = $ -> {
            switch ($) {
                case BUTTON_NAME_BROWSE:
                    return EventTask.create(ImagePicker.pick(), EventType.BUTTON_BROWSE);
                case BUTTON_NAME_DELETE:
                    return EventTask.create(EventType.BUTTON_DELETE);
                case BUTTON_NAME_REFRESH:
                    return EventTask.create(EventType.BUTTON_REFRESH);
                default:
                    return EventTask.empty();
            }
        };

        String cmd = e.getActionCommand();
        EventManager.dispatchEvent(EventTaskCreator.apply(cmd));
    }
}
