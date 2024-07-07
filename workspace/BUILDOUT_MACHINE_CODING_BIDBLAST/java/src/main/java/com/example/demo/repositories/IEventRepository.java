package com.example.demo.repositories;

import java.util.Optional;
import com.example.demo.entities.Event;

public interface IEventRepository{

 public Event saveEvent(Event event);
    public Event findEventById(Long id);
    public Optional<Event> findEventByName(String name);
    Optional<Boolean> existsById(Long id);

}