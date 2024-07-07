package com.example.demo.entities;

import java.time.LocalDate;

public class Event extends BaseEntity{

    private String eventName;
    private String eventPrize;
    private LocalDate eventDate = LocalDate.now();
    public Event(Long id, String eventName, String eventPrize) {
        super(id);
        this.eventName = eventName;
        this.eventPrize = eventPrize;
    }
    public Event(String eventName, String eventPrize) {
        this.eventName = eventName;
        this.eventPrize = eventPrize;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getEventPrize() {
        return eventPrize;
    }
    public void setEventPrize(String eventPrize) {
        this.eventPrize = eventPrize;
    }
    @Override
    public String toString() {
        return "Event [eventDate=" + eventDate + ", eventName=" + eventName + ", eventPrize="
                + eventPrize + "]";
    }   
    
}
