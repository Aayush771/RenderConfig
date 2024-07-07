package com.example.demo.repositories;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.example.demo.entities.Bids;

public class BidsRepository implements IBidsRepository{

    private Map<Long,Bids> bidsMap = new LinkedHashMap<>();
    private AtomicLong autoIncrement = new AtomicLong(1);
    

    public BidsRepository(Map<Long, Bids> bidsMap) {
        this.bidsMap = bidsMap;
        this.autoIncrement = new AtomicLong(bidsMap.size());
    }

    public BidsRepository() {
    }
    
    @Override
    public Bids saveBids(Bids bids) {
        // TODO Auto-generated method stub
            Bids g = new Bids(autoIncrement.getAndIncrement(),bids.getBidderId(),bids.getEventId(),bids.getAmounts());
            bidsMap.putIfAbsent(g.getId(),g);
            return g;
    }

    @Override
    public Bids findById(Long id) {
        // TODO Auto-generated method stub
        return bidsMap.get(id);
    }

    public Optional<Boolean> existsById(Long id) {
        return Optional.ofNullable(bidsMap.containsKey(id));
    }

    @Override
    public List<Bids> findAll() {
        // TODO Auto-generated method stub
     return bidsMap.values().stream().collect(Collectors.toList());
    }
    
}
