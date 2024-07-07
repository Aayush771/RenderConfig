package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.Event;
import com.example.demo.repositories.IEventRepository;

public class EventService {
    private IEventRepository eventRepository;

    

    public EventService(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }



    public Event addEvent(List<String> values){
        return eventRepository.saveEvent(new Event(null, values.get(1), values.get(2)));
    }
    
}
