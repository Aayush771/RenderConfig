package com.example.demo.entities;

import java.util.List;

public class Bids extends BaseEntity{

    private Long bidderId;
    private Long eventId;
    private Long maxAmounts;
    public Bids( Long id, Long bidderId, Long eventId, Long amounts) {
        super(id);
        this.bidderId = bidderId;
        this.eventId = eventId;
        this.maxAmounts = amounts;
    }
    public Bids(Long bidderId, Long eventId, Long amounts) {
        this.bidderId = bidderId;
        this.eventId = eventId;
        this.maxAmounts = amounts;
    }
    public Long getBidderId() {
        return bidderId;
    }
    public void setBidderId(Long bidderId) {
        this.bidderId = bidderId;
    }
    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    public Long getAmounts() {
        return maxAmounts;
    }
    public void setAmounts(Long amounts) {
        this.maxAmounts = amounts;
    }
    @Override
    public String toString() {
        return "Bids [amounts=" + maxAmounts + ", bidderId=" + bidderId + ", eventId=" + eventId + "]";
    }

    

    
}
