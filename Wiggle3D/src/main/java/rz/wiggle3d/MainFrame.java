package rz.wiggle3d;

import javax.swing.*; // For JFrame, JLabel, and ImageIcon
import java.awt.*; // For BorderLayout and other layout managers

public class MainFrame extends JFrame {

    private static final int BORDER_SPLITER_SIZE = 5;
    private static final double HEADER_DIVDER_RATIO = 0.1;
    private static final double BODY_DIVIDER_RATIO = 0.35;

    public MainFrame() {
        // 設定窗口標題
        setTitle("Wiggle Stereoscopy Generator");

        // 設定窗口大小
        setSize(1200, 800);

        // 設定關閉窗口時結束程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 設定窗口位置於螢幕中央
        setLocationRelativeTo(null);

        // --------------------------------------------------------------
        // // Start to render images.
        // ImageIcon imageIcon = new
        // ImageIcon("C:\\Users\\User\\Desktop\\Raymond-2023\\_RD_\\Code-Night\\Java-Wiggle3D\\Wiggle3D\\src\\images\\crystalball.jpg");
        // JLabel label = new JLabel(imageIcon);

        JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, header(), body());
        mainPane.setDividerLocation(HEADER_DIVDER_RATIO);
        mainPane.setResizeWeight(HEADER_DIVDER_RATIO);
        mainPane.setDividerSize(BORDER_SPLITER_SIZE);

        add(mainPane);
        setVisible(true);
    }

    private JPanel header() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.white);
        return headerPanel;
    }

    private JSplitPane body() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerSize(BORDER_SPLITER_SIZE);
        splitPane.setDividerLocation(BODY_DIVIDER_RATIO);
        splitPane.setResizeWeight(BODY_DIVIDER_RATIO);
        return splitPane;
    }

}
