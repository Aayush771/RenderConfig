package com.example.demo.services;

import java.util.List;
//import javax.management.RuntimeErrorException;
import com.example.demo.entities.Team;
import com.example.demo.repositories.ITeamRepository;

public class TeamService implements ITeamService{

    private final ITeamRepository teamRepository;
    

    public TeamService(ITeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }



    @Override
    public Team create(Team team) {
        // TODO Auto-generated method stub
        teamRepository.getTeamByName(team.getTeamName()).ifPresent(i -> {
        throw new RuntimeException("TEAM_ALREADY_EXISTS");
        });
        //orElseThrow(() -> new RuntimeException("TEAM_ALREADY_EXISTS"));

        return teamRepository.save(team);
    }

    

    @Override
    public List<Team> getAllTeams() {
        // TODO Auto-generated method stub
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Long id) {
        // TODO Auto-generated method stub
    return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Greeting with id: " + id + " not found!"));

    }



    
}
