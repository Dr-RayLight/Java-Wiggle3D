package rz.wiggle3d.view;

import javax.swing.*;

public class MainView extends JFrame {

    // Global Variables
    private static final double HEADER_DIVDER_RATIO = 0.1;

    private static final int BORDER_SPLITER_SIZE = 5;
    private static final int WIDTH = 1200;
    private static final int HIGHT = 800;

    private final static String TITLE = "Wiggle Stereoscopy Generator";

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
        mainPane.setDividerLocation(HEADER_DIVDER_RATIO);
        mainPane.setResizeWeight(HEADER_DIVDER_RATIO);
        mainPane.setDividerSize(BORDER_SPLITER_SIZE);
        add(mainPane);
    }

    

}
