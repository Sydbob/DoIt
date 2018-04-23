package com.doIt.DoIt.service;

import com.doIt.DoIt.dao.TeamRepository;
import com.doIt.DoIt.entity.Team;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams(){
        List<Team> teams = new ArrayList<>();
        for(Team t : teamRepository.findAll())
            teams.add(t);
        return teams;
    }

   

    public void save(Team team){
        teamRepository.save(team);
    }

    public void delete(int teamID){
        teamRepository.delete(teamID);
    }

    public Team findTeam(int id){
        return teamRepository.findOne(id);
    }
}
