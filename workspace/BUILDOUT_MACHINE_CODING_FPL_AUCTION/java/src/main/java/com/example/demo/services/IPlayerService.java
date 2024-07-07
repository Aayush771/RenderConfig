package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.Players;

public interface IPlayerService {
    
    public Players create(Players players);
    public List<Players> getAllPlayerss();
    public Players getPlayers(Long id);
    




}
