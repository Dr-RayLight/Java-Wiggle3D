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
import rz.wiggle3d.components.LoadingDialog;
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
    private ImagePanel mDepthMapPanel = new ImagePanel(PathUtil.IMAGE_NONE.get());
    private JTabbedPane mTabPanels;

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
        JPanel tabWiggleStereo = new JPanel();
        tabWiggleStereo.setBackground(Color.WHITE);

        mTabPanels = new JTabbedPane() {
            public Color getForegroundAt(int index) {
                if (getSelectedIndex() == index)
                    return Color.BLUE;
                return Color.BLACK;
            }
        };

        mTabPanels.addTab(TAB_TITLE_ORIGIN_IMAGE, mOriImagePanel);
        mTabPanels.addTab(TAB_TITLE_DEPTH_MAP, mDepthMapPanel);
        mTabPanels.addTab(TAB_TITLE_WIGGLE_STEREOSCOPY, tabWiggleStereo);

        mTabPanels.addChangeListener(new ChangeListener() {
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
                EventManager.dispatchEvent(EventTaskCreator.apply(mTabPanels.getSelectedIndex()));
            }
        });

        add(mTabPanels);
    }

    @Override
    public void onEventReceived(EventTask<?> eventTask) {

        EventManager.EventType eventType = eventTask.getEventType();
        System.out.println(TAG + " eventTask: " + eventTask.getEventType());
        switch (eventType) {
            case BUTTON_DELETE: {
                mOriImagePanel.clearImage();
                mOriImagePanel.updateImage(PathUtil.IMAGE_NONE.get());

                mDepthMapPanel.clearImage();
                mDepthMapPanel.updateImage(PathUtil.IMAGE_NONE.get());

                mTabPanels.setSelectedIndex(TAB_INEDX_ORIGIN_IMAGE);
            }
                break;
            case ORIGINAL_IMAGE_UPLOAD: {
                mTabPanels.setSelectedIndex(TAB_INEDX_ORIGIN_IMAGE);

                String imagePath = eventTask.getValue().get().toString();
                mOriImagePanel.updateImage(imagePath.toString());
            }
                break;
            case DEPTH_MAP_UPLOAD: {
                String imagePath = eventTask.getValue().get().toString();
                mDepthMapPanel.updateImage(imagePath);
                LoadingDialog.instance().stop();

                mTabPanels.setSelectedIndex(TAB_INEDX_DEPTH_MAP);
            }
                break;
            default:
                break;
        }
    }

}