package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import com.example.demo.entities.Auction;

public interface IAuctionRepository {
    Auction save(Auction auction);
    boolean existsById(Long id);
    Optional<Auction> findById(Long id);
    
    List<Auction> findAll();
}
