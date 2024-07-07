package com.example.demo.entities;

public class AuctionedPlayers extends BaseEntity{
    private final String playerName;
    private final String teamName;
    private final Long auctionedAmount;
    public AuctionedPlayers(Long id, String playerName, String teamName, Long auctionedAmount) {
        super(id);
        this.playerName = playerName;
        this.teamName = teamName;
        this.auctionedAmount = auctionedAmount;
    }
    public String getPlayerName() {
        return playerName;
    }
    public String getTeamName() {
        return teamName;
    }
    public Long getAuctionedAmount() {
        return auctionedAmount;
    }
    
}
