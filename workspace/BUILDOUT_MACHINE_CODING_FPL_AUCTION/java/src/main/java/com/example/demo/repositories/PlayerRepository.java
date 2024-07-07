package com.example.demo.repositories;

import java.util.HashMap;
import java.util.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.example.demo.entities.Players;

public class PlayerRepository implements IPlayersRepository {

    private final Map<Long,Players> playersMap = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);


    @Override
    public Players save(Players player) {
        // TODO Auto-generated method stub
        Players players = new Players(idCounter.getAndIncrement(), player.getPlayerName(),player.getBasePrice());
        playersMap.putIfAbsent(players.getId(),players);
     //   System.out.println(playersMap);
        return players;
    }

    @Override
    public boolean existsById(Long id) {
        return playersMap.containsKey(id);
    }

    @Override
    public Optional<Players> findById(Long id) {
        return Optional.ofNullable(playersMap.get(id));
    }

    @Override
    public List<Players> findAll() {
        return playersMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        playersMap.remove(id);
    }
    
}
