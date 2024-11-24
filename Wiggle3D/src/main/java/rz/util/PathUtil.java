package rz.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

public enum PathUtil {
    IMAGE_DEMO("demo.jpg"),
    IMAGE_NONE("no_image.png"),

    ICON_FOLDER("folder.png"),
    ICON_DELETE("delete.png"),
    ICON_REFRESH("refresh.png"),

    ;

    String _path;
    PathUtil(String path) {
        _path = path;
    }

    public String get() {
        return "images/".concat(_path);
    }

    public Optional<BufferedImage> getImage(ClassLoader cl) {
        try {
            return Optional.ofNullable(ImageIO.read(cl.getResourceAsStream(get())));
        } catch (IOException e) {
           return Optional.empty();
        }
    }
}
