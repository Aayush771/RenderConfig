package com.example.demo.repositories;

import java.util.List;
import java.util.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.example.demo.entities.Team;

public class TeamRepository implements ITeamRepository {

    private final Map<Long, Team> teamMap = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);


    @Override
    public Team save(Team team) {
        // TODO Auto-generated method stub
        Team teams =
                new Team(idCounter.getAndIncrement(), team.getTeamName(), team.getWalletMoney());
        teamMap.putIfAbsent(teams.getId(), teams);
        // System.out.println(teamMap);
        return teams;
    }

    @Override
    public boolean existsById(Long id) {
        return teamMap.containsKey(id);
    }

    @Override
    public Optional<Team> findById(Long id) {
        return Optional.ofNullable(teamMap.get(id));
    }

    @Override
    public List<Team> findAll() {
        return teamMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        teamMap.remove(id);
    }

    @Override
    public Optional<Team> getTeamByName(String name) {
        // TODO Auto-generated method stub
        return teamMap.values().stream().filter(team -> team.getTeamName().equals(name)).findAny();

    }

    @Override
    public Optional<Team> findTeamByPlayerId(Long Id) {
        // TODO Auto-generated method stub
        return teamMap.values().stream()
                .filter(team -> team.getPlayerId() != null && team.getPlayerId()
                .contains(Id)).findAny();
    }

}
