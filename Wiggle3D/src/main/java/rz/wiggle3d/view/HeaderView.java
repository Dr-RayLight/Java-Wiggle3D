package rz.wiggle3d.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.function.Function;

import javax.swing.Box;
import javax.swing.JPanel;

import rz.util.ImagePicker;
import rz.util.PathUtil;
import rz.util.PyCmd;
import rz.wiggle3d.components.ImageButton;
import rz.wiggle3d.components.LoadingDialog;
import rz.wiggle3d.manager.EventManager;
import rz.wiggle3d.manager.EventTask;
import rz.wiggle3d.manager.ImagesManager;
import rz.wiggle3d.manager.EventManager.EventType;

public class HeaderView extends JPanel implements ActionListener {

    // private static final String TAG = "[HeaderView]";
    private static final int IMAGE_BUTTON_WIDTH = 80;
    private static final int IMAGE_BUTTON_HEIGHT = 80;

    private static final String BUTTON_NAME_BROWSE = "Browse";
    private static final String BUTTON_NAME_REFRESH = "Refresh";
    private static final String BUTTON_NAME_DELETE = "Delete";
    private static final String BUTTON_NAME_GEN_3D = "Gen 3D";

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

        // Download 3D content Button
        ImageButton download3DBtn = new ImageButton(PathUtil.ICON_3D.get(), IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        download3DBtn.setBackground(Color.WHITE);
        download3DBtn.setText(BUTTON_NAME_GEN_3D);
        download3DBtn.addActionListener(this);

        Box box = Box.createHorizontalBox();
        box.add(browseBtn);
        box.add(refreshBtn);
        box.add(deleteBtn);
        box.add(download3DBtn);

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
                    String pikedImagedPath = ImagePicker.pick();
                    ImagesManager.setOriginalImagePath(pikedImagedPath);

                case BUTTON_NAME_REFRESH:
                    String oriImagePath = ImagesManager.getOriginalImagePath();
                    String pyScript = getClass().getClassLoader().getResource("script/generate_depth_map.py").getPath();

                    if (!oriImagePath.isEmpty()) {
                        LoadingDialog.instance().start();
                    }

                    new Thread(() -> {

                        System.out.println("[HeaderView]: Start Gen");
                        File fileDepthMap = new File(new File(oriImagePath).getParent() + "/output_depth_map.png");
                        PyCmd.execute(pyScript, oriImagePath);

                        boolean isGenSucc = fileDepthMap.exists();
                        System.out.println("[HeaderView]: isGenSucc: " + isGenSucc);

                        if (isGenSucc) {
                            EventManager
                                    .dispatchEvent(EventTask.create(fileDepthMap.toPath(), EventType.DEPTH_MAP_UPLOAD));
                        }
                    }).start();
                    return EventTask.create(oriImagePath, EventType.ORIGINAL_IMAGE_UPLOAD);
                case BUTTON_NAME_DELETE:
                    return EventTask.create(EventType.BUTTON_DELETE);
                // case BUTTON_NAME_REFRESH:
                // return EventTask.create(EventType.BUTTON_REFRESH);
                case BUTTON_NAME_GEN_3D:
                    return EventTask.create(EventType.BUTTON_3D);
                default:
                    return EventTask.empty();
            }
        };

        String cmd = e.getActionCommand();
        EventManager.dispatchEvent(EventTaskCreator.apply(cmd));
    }
}
