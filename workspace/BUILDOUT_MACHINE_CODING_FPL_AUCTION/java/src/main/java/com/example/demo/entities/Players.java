package com.example.demo.entities;

public class Players extends BaseEntity{

     private  final String playerName;
     private  final Long basePrice;
     private  AuctionStatus  auctionStatus = AuctionStatus.UNSOLD;
    public Players(Long id, String playerName, Long basePrice) {
        super(id);
        this.playerName = playerName;
        this.basePrice = basePrice;
    }
    public String getPlayerName() {
        return playerName;
    }
    public Long getBasePrice() {
        return basePrice;
    }
    @Override
    public String toString() {
        return "Players [basePrice=" + basePrice + ", playerName=" + playerName + "]";
    }
    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }
    public void setAuctionStatus(AuctionStatus auctionStatus) {
        this.auctionStatus = auctionStatus;
    }


}
