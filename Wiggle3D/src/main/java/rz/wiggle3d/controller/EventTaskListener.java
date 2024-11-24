package rz.wiggle3d.controller;

import java.util.Optional;

import rz.wiggle3d.manager.EventTask;

public interface EventTaskListener {
    void onEventReceived(EventTask<Optional<?>> eventTask);
}
