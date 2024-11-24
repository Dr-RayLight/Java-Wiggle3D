package rz.wiggle3d;

import javax.swing.*;   // For JFrame, JLabel, and ImageIcon
import java.awt.*;      // For BorderLayout and other layout managers

public class MainFrame extends JFrame {
    public MainFrame() {
        // 設定窗口標題
        setTitle("Wiggle Stereoscopy Generator");

        // 設定窗口大小
        setSize(800, 600);

        // 設定關閉窗口時結束程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 設定窗口位置於螢幕中央
        setLocationRelativeTo(null);

        // Start to render images.
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Desktop\\Raymond-2023\\_RD_\\Code-Night\\Java-Wiggle3D\\Wiggle3D\\src\\images\\crystalball.jpg");
        JLabel label = new JLabel(imageIcon);
        
        add(label);
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
    }


}
