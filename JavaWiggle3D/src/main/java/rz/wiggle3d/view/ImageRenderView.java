package rz.wiggle3d.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Optional;
import java.util.function.Function;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rz.util.PathUtil;
import rz.wiggle3d.components.ImagePanel;
import rz.wiggle3d.controller.EventTaskListener;
import rz.wiggle3d.manager.EventManager;
import rz.wiggle3d.manager.EventManager.EventType;
import rz.wiggle3d.manager.EventTask;

public class ImageRenderView extends JPanel implements EventTaskListener {

    private static final String TAG = "[ImageRenderView]";
    private static final String TAB_TITLE_ORIGIN_IMAGE = "Orignal Image";
    private static final String TAB_TITLE_DEPTH_MAP = "Depth Map";
    private static final String TAB_TITLE_WIGGLE_STEREOSCOPY = "Wiggle Stereoscopy";

    private static final int TAB_INEDX_ORIGIN_IMAGE = 0;
    private static final int TAB_INEDX_DEPTH_MAP = 1;
    private static final int TAB_INEDX_WIGGLE_STEREOSCOPY = 2;

    private ImagePanel mOriImagePanel = new ImagePanel(PathUtil.IMAGE_NONE.get());

    // ---------------------------------------------------------
    public ImageRenderView() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        onUpdate();
    }

    public static ImageRenderView build() {
        return new ImageRenderView();
    }

    public void onUpdate() {
        initTabs();
        EventManager.addListener(this);
    }

    private void initTabs() {
        // Original Images
        JPanel tabOriImg = new JPanel();
        tabOriImg.setLayout(new BorderLayout());
        tabOriImg.setBackground(Color.WHITE);

        // Draw Image.
        tabOriImg.add(mOriImagePanel);
        // ---------------------------------------------------------
        // Depth Map
        JPanel tabDepthMap = new JPanel();
        tabDepthMap.setBackground(Color.WHITE);

        // ---------------------------------------------------------
        // Wiggle Stereoscopy
        JPanel tabWiggleStereo = new JPanel();
        tabWiggleStereo.setBackground(Color.WHITE);

        JTabbedPane tabPanel = new JTabbedPane() {
            public Color getForegroundAt(int index) {
                if (getSelectedIndex() == index)
                    return Color.BLUE;
                return Color.BLACK;
            }
        };

        tabPanel.addTab(TAB_TITLE_ORIGIN_IMAGE, tabOriImg);
        tabPanel.addTab(TAB_TITLE_DEPTH_MAP, tabDepthMap);
        tabPanel.addTab(TAB_TITLE_WIGGLE_STEREOSCOPY, tabWiggleStereo);

        tabPanel.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Function<Integer, EventTask<Optional<?>>> EventTaskCreator = $ -> {
                    switch ($) {
                        case TAB_INEDX_ORIGIN_IMAGE:
                            return EventTask.create(EventType.TAB_ORIGINAL_IMAGE);
                        case TAB_INEDX_DEPTH_MAP:
                            return EventTask.create(EventType.TAB_DEPTH_MAP);
                        case TAB_INEDX_WIGGLE_STEREOSCOPY:
                            return EventTask.create(EventType.TAB_WIGGLE_STEREOSCOPY);
                        default:
                            return EventTask.empty();
                    }
                };
                EventManager.dispatchEvent(EventTaskCreator.apply(tabPanel.getSelectedIndex()));
            }
        });

        add(tabPanel);
    }

    @Override
    public void onEventReceived(EventTask<?> eventTask) {
        System.out.println(TAG + "Pick Image, eventTask: " + eventTask.getEventType().name());

        if (eventTask.getEventType() != EventType.BUTTON_BROWSE) {
            return;
        }

        eventTask.getValue().ifPresent(imgPath -> {
            System.out.println(TAG + "Pick Image Path: " + imgPath);
            mOriImagePanel.updateImage(imgPath.toString());
        });
    }

}