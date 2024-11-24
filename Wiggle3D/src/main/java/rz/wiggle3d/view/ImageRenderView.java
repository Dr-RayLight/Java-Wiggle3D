package rz.wiggle3d.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

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
        JPanel tabOriImg = new JPanel();
        tabOriImg.setBackground(Color.WHITE);
        tabOriImg.add(new JLabel("Orignal Image"));

        JPanel tabDepthMap = new JPanel();
        tabDepthMap.setBackground(Color.WHITE);
        tabDepthMap.add(new JLabel("Depth Map"));

        JPanel tabWiggleStereo = new JPanel();
        tabWiggleStereo.setBackground(Color.WHITE);
        tabWiggleStereo.add(new JLabel("Wiggle Stereoscopy"));

        JTabbedPane tabPanel = new JTabbedPane();
        tabPanel.addTab(TAB_TITLE_ORIGIN_IMAGE, tabOriImg);
        tabPanel.addTab(TAB_TITLE_DEPTH_MAP, tabDepthMap);
        tabPanel.addTab(TAB_TITLE_WIGGLE_STEREOSCOPY, tabWiggleStereo);

        add(tabPanel);
    }

}