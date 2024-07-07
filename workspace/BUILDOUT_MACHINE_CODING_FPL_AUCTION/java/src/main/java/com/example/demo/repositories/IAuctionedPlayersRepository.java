package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import com.example.demo.entities.AuctionedPlayers;

public interface IAuctionedPlayersRepository {
    AuctionedPlayers save(AuctionedPlayers player);
    boolean existsById(Long id);
    Optional<AuctionedPlayers> findById(Long id);
    List<AuctionedPlayers> findAll();
    void deleteById(Long id);
}
