package com.example.clinica.events;

public class Event {
    private ChangeEventType type;

    public Event(ChangeEventType type) {
        this.type = type;
    }
    public ChangeEventType getType() {
        return type;
    }

}
