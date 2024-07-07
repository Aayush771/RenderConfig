package com.example.demo.entities;

public class Bids {

    private final Long teamId;
    private Long bidAmount;
    public Bids(Long teamId, Long bidAmount) {
        this.teamId = teamId;
        this.bidAmount = bidAmount;
    }
    public Long getTeamId() {
        return teamId;
    }
    public Long getBidAmount() {
        return bidAmount;
    }
    public void setBidAmount(Long bidAmount) {
        this.bidAmount = bidAmount;
    }
    @Override
    public String toString() {
        return "Bids [bidAmount=" + bidAmount + ", teamId=" + teamId
                + "]";
    }
   
    
    
}
