package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import com.example.demo.entities.Team;

public interface ITeamRepository {
    Team save(Team player);
    boolean existsById(Long id);
    Optional<Team> findById(Long id);
    List<Team> findAll();
    void deleteById(Long id);
    public Optional<Team> getTeamByName(String name);
    public Optional<Team> findTeamByPlayerId(Long Id);
    }