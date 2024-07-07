package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.entities.AuctionedPlayers;
//import com.example.demo.entities.AuctionedPlayers;

public class AuctionedPlayersRepository implements IAuctionedPlayersRepository{
    private final Map<Long,AuctionedPlayers> auctionedMap = new HashMap<>();

    @Override
    public AuctionedPlayers save(AuctionedPlayers auctionedPlayers) {
        // TODO Auto-generated method stub
        auctionedMap.put(auctionedPlayers.getId(), auctionedPlayers);
        return auctionedPlayers;
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return auctionedMap.containsKey(id);
    }

    @Override
    public Optional<AuctionedPlayers> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(auctionedMap.get(id));
    }

    @Override
    public List<AuctionedPlayers> findAll() {
        // TODO Auto-generated method stub
        return auctionedMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub;
        auctionedMap.remove(id);
        
    }




}
