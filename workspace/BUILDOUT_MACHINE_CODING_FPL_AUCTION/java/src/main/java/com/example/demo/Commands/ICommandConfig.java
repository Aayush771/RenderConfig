package com.example.demo.Commands;

import java.util.List;

public interface ICommandConfig {

    public void REGISTER_PLAYER(List<String> tokens);
    public void REGISTER_TEAM(List<String> tokens);
    public void START_AUCTION(List<String> tokens);
    public void BID_PLAYER(List<String> tokens);
    public void CLOSE_AUCTION(List<String> tokens);
    public void TEAM_OVERVIEW(List<String> tokens);
    public void PLAYER_OVERVIEW(List<String> tokens);


    
}
