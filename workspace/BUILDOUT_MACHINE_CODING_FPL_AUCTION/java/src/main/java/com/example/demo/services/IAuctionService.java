package com.example.demo.services;

import com.example.demo.entities.Auction;
import com.example.demo.entities.BidResult;
import com.example.demo.entities.Bids;
import com.example.demo.entities.Team;


public interface IAuctionService {
    public Auction create(Auction auction);
    public BidResult registerBids(Long auctionId,Bids bids);
    public Team declearWinner(Long auctionId);
    public String getAllPlyaersByTeamId(Long id);
    public String getPlayerDetails(Long playerId);

}
