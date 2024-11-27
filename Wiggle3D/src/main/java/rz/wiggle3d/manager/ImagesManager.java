package rz.wiggle3d.manager;

public class ImagesManager {

    private static String smOriImageLoadedPath = "";
    private static String smDepthMapLoadedPath = "";

    public static String getOriginalImagePath() {
        return smOriImageLoadedPath;
    }

    public static String getDepthMapOutputPath() {
        return smDepthMapLoadedPath;
    }

    public static void setOriginalImagePath(String path) {
        smOriImageLoadedPath = path;
    }

    public static void setDepthMapOutputPath(String path) {
        smDepthMapLoadedPath = path;
    }

    public static void clear() {
        smOriImageLoadedPath = "";
        smDepthMapLoadedPath = "";
    }
}
