package rz.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Supplier;

public class PyCmd {

    private static final String TAG = "[PyCmd]";

    public enum Script {
        PYTHON_GEN_DEPTH_MAP(() -> System.getProperty("user.dir").concat("../../../script/generate_depth_map.py"));

        // -----------------------------------------------------------------
        String mPath;

        Script(Supplier<String> path) {
            mPath = path.get();
        }

        public String path() {
            return mPath;
        }
        // -----------------------------------------------------------------
    }

    // 執行 Python 腳本，並傳入圖片路徑作為參數
    public static String execute(String pyScript, String imagePath) {
        System.out.println(TAG + "after: PyScript: " + pyScript + " , Image Path: " + imagePath);
        String[] command = new String[] { "python", pyScript, imagePath };

        try {
            // 使用 ProcessBuilder 執行命令
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            // 讀取腳本執行過程中的輸出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 讀取腳本執行過程中的錯誤輸出
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println("[PyCmd]: " + line);
            }

            return line;

        } catch (Exception e) {
            System.out.println("[PyCmd]: " + e.getMessage());
            return "";
        }
    }
}
