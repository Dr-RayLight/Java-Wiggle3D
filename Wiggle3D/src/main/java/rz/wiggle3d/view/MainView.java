package rz.wiggle3d.view;

import javax.swing.*;
import rz.wiggle3d.controller.EventTaskListener;
import rz.wiggle3d.manager.EventManager;
import rz.wiggle3d.manager.EventTask;

public class MainView extends JFrame implements EventTaskListener {

    // Global Variables
    private static final String TAG = "[MainView]";
    private static final int HEADER_DIVDER_HEIGHT = 100;

    private static final int BORDER_SPLITER_SIZE = 5;
    private static final int WIDTH = 1200;
    private static final int HIGHT = 800;

    private final static String TITLE = "Simple Wiggle Stereoscopy Generator";

    public enum RenderType {
        ORIGIN, DEPTH_MAP, WIGGLE
    }

    public RenderType mRenderType = RenderType.ORIGIN;

    // --------------------------------------------------------------------------
    // Constructor
    public MainView() {
        // Basic setup.
        setTitle(TITLE);
        setSize(WIDTH, HIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Layout Def.
        JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, HeaderView.build(), BodyView.build());
        mainPane.setDividerLocation(HEADER_DIVDER_HEIGHT);
        mainPane.setResizeWeight(0.1);
        mainPane.setDividerSize(BORDER_SPLITER_SIZE);
        add(mainPane);

        // Register Listener
        EventManager.addListener(this);
    }

    @Override
    public void onEventReceived(EventTask<?> eventTask) {
        System.out.println(TAG + " eventTask: " + eventTask.getEventType());
    }
}
