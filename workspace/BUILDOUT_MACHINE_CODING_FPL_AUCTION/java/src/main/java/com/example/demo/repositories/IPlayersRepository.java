package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import com.example.demo.entities.Players;

public interface IPlayersRepository {
    Players save(Players player);
    boolean existsById(Long id);
    Optional<Players> findById(Long id);
    List<Players> findAll();
    void deleteById(Long id);
    

}
