package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.example.demo.entities.Auction;

public class AuctionRepository implements IAuctionRepository {

    private final Map<Long,Auction> auctionMap = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Auction save(Auction auctions) {
        // TODO Auto-generated method stub
        Auction auction = new Auction(idCounter.getAndIncrement(), auctions.getPlayerId());
        auctionMap.putIfAbsent(auction.getId(),auction);
        return auction;
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return auctionMap.containsKey(id);
    }

    @Override
    public Optional<Auction> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(auctionMap.get(id));

    }

    @Override
    public List<Auction> findAll() {
        // TODO Auto-generated method stub
        return auctionMap.values().stream().collect(Collectors.toList());
    }
    
}
