package rz.util;

public enum Path {
    IMG_DEFAULT(""),


    ;

    String _path;
    Path(String path) {
        _path = path;
    }

    public String get() {
        return _path;
    }
}
