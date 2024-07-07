package com.example.demo.repositories;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import com.example.demo.entities.Event;

public class EventRepository implements IEventRepository {

    private Map<Long,Event> eventMap = new LinkedHashMap<>();
    private AtomicLong autoIncrement = new AtomicLong(1);

    public EventRepository(Map<Long, Event> eventMap) {
        this.eventMap = eventMap;
        this.autoIncrement = new AtomicLong(eventMap.size());
    }

    public EventRepository() {}

    @Override
    public Event saveEvent(Event event) {
        // TODO Auto-generated method stub

        Event g = new Event(autoIncrement.getAndIncrement(),event.getEventName(),event.getEventPrize()); 
        eventMap.putIfAbsent(g.getId(),g);
        return g;
    }

    @Override
    public Event findEventById(Long id) {
        // TODO Auto-generated method stub
        return eventMap.get(id);
    }

    @Override
    public Optional<Event> findEventByName(String name) {
        // TODO Auto-generated method stub
        Optional<Event> optUser = Optional.empty();
        for (Event i : eventMap.values()) {
            if (i.getEventName().equals(name))
                optUser = Optional.of(i);
        }
        return optUser;
    }

    @Override
    public Optional<Boolean> existsById(Long id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(eventMap.containsKey(id));
    }
    
}
