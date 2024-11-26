package rz.wiggle3d.manager;

import java.util.ArrayList;

import rz.wiggle3d.controller.EventTaskListener;

public class EventManager {

    // ----------------------------------------------------------------------
    // Global Types
    public enum EventType {
        BUTTON_BROWSE,
        BUTTON_REFRESH,
        BUTTON_DELETE,

        TAB_ORIGINAL_IMAGE,
        TAB_DEPTH_MAP,
        TAB_WIGGLE_STEREOSCOPY,

        ORIGINAL_IMAGE_UPLOAD,
        DEPTH_MAP_UPLOAD,

        NONE;
        ;
    }

    private static ArrayList<EventTaskListener> mListeners = new ArrayList<>();
    // -----------------------------------------------------------------------
    public static void addListener(EventTaskListener listener) {
        mListeners.add(listener);
    }

    public static void removeListener(EventTaskListener listener) {
        mListeners.remove(listener);
    }

    public static void clearAllListener() {
        mListeners.clear();
    }

    public static void dispatchEvent(EventTask<?> eventTask) {
        mListeners.forEach($ -> $.onEventReceived(eventTask));
    }
}
