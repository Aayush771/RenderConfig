package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.Team;

public interface ITeamService {
    public Team create(Team team);
    public List<Team> getAllTeams();
    public Team getTeamById(Long id);
    
}
