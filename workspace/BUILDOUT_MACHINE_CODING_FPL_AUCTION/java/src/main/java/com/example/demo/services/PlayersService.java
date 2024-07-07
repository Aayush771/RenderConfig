package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.Players;
import com.example.demo.repositories.IPlayersRepository;

public class PlayersService implements IPlayerService{

    private final IPlayersRepository playersRepository;

    public PlayersService(IPlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    @Override
    public Players create(Players players) {
        // TODO Auto-generated method stub
       // System.out.println();
        return playersRepository.save(players);

    }

    

    @Override
    public List<Players> getAllPlayerss() {
        // TODO Auto-generated method stub
        return playersRepository.findAll();
    }

    @Override
    public Players getPlayers(Long id) {
        // TODO Auto-generated method stub
    return playersRepository.findById(id).orElseThrow(() -> new RuntimeException("Greeting with id: " + id + " not found!"));

    }

   
    
}
