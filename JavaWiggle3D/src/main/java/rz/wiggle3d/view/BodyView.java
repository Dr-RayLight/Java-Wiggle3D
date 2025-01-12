package rz.wiggle3d.view;

import javax.swing.JSplitPane;

public class BodyView extends JSplitPane {

    private static final double BODY_DIVIDER_RATIO = 0;
    private static final int LEFT_BODY_WIDTH = 0;
    private static final int BORDER_SPLITER_SIZE = 0;

    public BodyView() {
        super(JSplitPane.HORIZONTAL_SPLIT, PanelView.build(), ImageRenderView.build());
        
        setDividerSize(BORDER_SPLITER_SIZE);
        setDividerLocation(LEFT_BODY_WIDTH);
        setResizeWeight(BODY_DIVIDER_RATIO);
    }

    public static BodyView build() {
        return new BodyView();
    }
}
