package rz.wiggle3d.components;

import javax.swing.*;

public class LoadingDialog {
    private static LoadingDialog instance; // 單例模式
    private JDialog dialog; // 用於顯示的對話框

    // 私有構造方法，避免直接實例化
    private LoadingDialog() {
    }

    // 獲取單例實例
    public static LoadingDialog instance() {
        if (instance == null) {
            instance = new LoadingDialog();
        }
        return instance;
    }

    // 啟動 LoadingDialog
    public void start() {
        // 如果對話框已經在顯示，直接返回
        if (dialog != null && dialog.isShowing()) {
            return;
        }

        SwingUtilities.invokeLater(() -> {
            // 創建 JOptionPane
            JOptionPane pane = new JOptionPane(
                    "請稍候，Wiggle Stereoscopy 製作中....", // 顯示訊息
                    JOptionPane.INFORMATION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null,
                    new Object[] {},
                    null);

            // 創建 JDialog
            dialog = pane.createDialog(null, "");
            dialog.setModal(false); // 非模態
            dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // 禁止關閉

            // 添加進度條
            JProgressBar progressBar = new JProgressBar();
            progressBar.setIndeterminate(true); // 不確定進度
            pane.add(progressBar, 1); // 加入進度條至 JOptionPane

            dialog.pack(); // 調整大小以適應內容
            dialog.setLocationRelativeTo(null); // 居中顯示
            dialog.setVisible(true); // 顯示對話框
        });
    }

    // 停止 LoadingDialog
    public void stop() {
        SwingUtilities.invokeLater(() -> {
            if (dialog != null) {
                dialog.dispose(); // 關閉對話框
                dialog = null; // 釋放資源
            }
        });
    }
}
