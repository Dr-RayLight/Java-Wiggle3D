package rz.wiggle3d.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import rz.util.PathUtil;
import rz.wiggle3d.components.ImagePanel;

public class ImageRenderView extends JPanel {

    private static final String TAB_TITLE_ORIGIN_IMAGE = "Orignal Image";
    private static final String TAB_TITLE_DEPTH_MAP = "Depth Map";
    private static final String TAB_TITLE_WIGGLE_STEREOSCOPY = "Wiggle Stereoscopy";

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
    }

    private void initTabs() {
        // Original Images
        JPanel tabOriImg = new JPanel();
        tabOriImg.setLayout(new BorderLayout());
        tabOriImg.setBackground(Color.WHITE);

        // Draw Image.
        ImagePanel imagePanel = new ImagePanel(PathUtil.IMAGE_DEMO.get());
        tabOriImg.add(imagePanel);
        // ---------------------------------------------------------
        // Depth Map
        JPanel tabDepthMap = new JPanel();
        tabDepthMap.setBackground(Color.WHITE);

        // ---------------------------------------------------------
        // Wiggle Stereoscopy
        JPanel tabWiggleStereo = new JPanel();
        tabWiggleStereo.setBackground(Color.WHITE);

        JTabbedPane tabPanel = new JTabbedPane();
        tabPanel.addTab(TAB_TITLE_ORIGIN_IMAGE, tabOriImg);
        tabPanel.addTab(TAB_TITLE_DEPTH_MAP, tabDepthMap);
        tabPanel.addTab(TAB_TITLE_WIGGLE_STEREOSCOPY, tabWiggleStereo);

        add(tabPanel);
    }
}