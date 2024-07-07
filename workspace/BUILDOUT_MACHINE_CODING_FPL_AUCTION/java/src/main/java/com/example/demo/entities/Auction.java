package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class Auction extends BaseEntity{

    private final Long playerId;
    private List<Bids> bids;
    


    public Auction(Long id, Long playerId) {
        super(id);
        this.playerId = playerId;
        this.bids = new ArrayList<>();
    }



    public List<Bids> getBids() {
        return bids;
    }



    public void setBids(List<Bids> bids) {
        this.bids = bids;
    }



    public Long getPlayerId() {
        return playerId;
    }



    @Override
    public String toString() {
        return "Auction [bids=" + bids + ", playerId=" + playerId + "]";
    }
    
    
}
