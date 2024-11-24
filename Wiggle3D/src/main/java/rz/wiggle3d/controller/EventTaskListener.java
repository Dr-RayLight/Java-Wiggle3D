package rz.wiggle3d.controller;

import rz.wiggle3d.manager.EventTask;

public interface EventTaskListener {
    void onEventReceived(EventTask<?> eventTask);
}
