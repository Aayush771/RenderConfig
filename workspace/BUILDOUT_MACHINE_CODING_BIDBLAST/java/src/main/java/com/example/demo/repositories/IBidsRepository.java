package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
//import java.util.Optional;
import com.example.demo.entities.Bids;

public interface IBidsRepository {
    public Bids saveBids(Bids bids);
    public Bids findById(Long id);
    Optional<Boolean> existsById(Long id);
    public List<Bids> findAll();
  //  public Optional<Bids> findBidsByName(String name);
}
