package rz.wiggle3d.manager;

import java.util.Optional;

import rz.wiggle3d.manager.EventManager.EventType;

public class EventTask<T> {

    private EventManager.EventType mEventType = EventType.NONE;
    private T m_t;

    private EventTask(T t, EventManager.EventType eventType) {
        this.m_t = t;
        this.mEventType = eventType;
    }

    public static <T> EventTask<T> create(T t, EventManager.EventType eventType) {
        return new EventTask<T>(t, eventType);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static EventTask<Optional<?>> empty() {
        return new EventTask(Optional.empty(), EventType.NONE);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static EventTask<Optional<?>> create(EventManager.EventType eventType) {
        return new EventTask(Optional.empty(), eventType);
    }

    public Optional<T> getValue() {
        return Optional.ofNullable(m_t);
    }

    public EventManager.EventType getEventType() {
        return mEventType;
    }
}
