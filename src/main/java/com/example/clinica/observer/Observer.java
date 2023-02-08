package com.example.clinica.observer;


import com.example.clinica.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}