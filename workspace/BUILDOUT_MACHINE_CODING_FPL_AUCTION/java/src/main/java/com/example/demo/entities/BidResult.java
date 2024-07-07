package com.example.demo.entities;

public class BidResult {

    private final String name;
    private final Long bidAmount;
    public BidResult(String name, Long bidAmount) {
        this.name = name;
        this.bidAmount = bidAmount;
    }
    public String getName() {
        return name;
    }
    public Long getBidAmount() {
        return bidAmount;
    }

    
    
}
