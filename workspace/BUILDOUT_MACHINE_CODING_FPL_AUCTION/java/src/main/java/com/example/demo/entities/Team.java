package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class Team extends BaseEntity {

    private final String teamName;
    private Long walletMoney;
    private List<Long> playerId;
    public Team(Long id, String teamName, Long walletMoney) {
        super(id);
        this.teamName = teamName;
        this.walletMoney = walletMoney;
        this.playerId = new ArrayList<>();
    }
    public String getTeamName() {
        return teamName;
    }
    public Long getWalletMoney() {
        return walletMoney;
    }
    public List<Long> getPlayerId() {
        return playerId;
    }
    public void setPlayerId(List<Long> playerId) {
        this.playerId = playerId;
    }
    @Override
    public String toString() {
        return "Team [playerId=" + playerId + ", teamName=" + teamName + ", walletMoney="
                + walletMoney + "]";
    }
    public void setWalletMoney(Long walletMoney) {
        this.walletMoney = walletMoney;
    }

    
    
}
