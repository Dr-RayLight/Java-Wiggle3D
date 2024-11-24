package rz.wiggle3d.view;

import javax.swing.JSplitPane;

public class BodyView extends JSplitPane {

    private static final double BODY_DIVIDER_RATIO = 0.35;
    private static final int BORDER_SPLITER_SIZE = 5;

    public BodyView() {
        super(JSplitPane.HORIZONTAL_SPLIT, PanelView.build(), ImageRenderView.build());
        
        setDividerSize(BORDER_SPLITER_SIZE);
        setDividerLocation(BODY_DIVIDER_RATIO);
        setResizeWeight(BODY_DIVIDER_RATIO);
    }

    public static BodyView build() {
        return new BodyView();
    }
}
