package com.doIt.DoIt.service;

import com.doIt.DoIt.dao.MemberRepository;
import com.doIt.DoIt.dao.TeamRepository;
import com.doIt.DoIt.entity.Member;
import com.doIt.DoIt.entity.Team;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Service class for team entity
 * This class has all the methods involving team
 */
@Service
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;
    private MemberRepository memberRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams(){
        List<Team> teams = new ArrayList<>();
        for(Team t : teamRepository.findAll())
            teams.add(t);
        return teams;
    }

    public Set<List<Member>> getTeamsSorted() {
        Set<List<Member>> sortedTeams = new HashSet<>();
        for(Team team: teamRepository.findAll()){
            List<Member> teamMembers = new ArrayList<>();
            for (Member member: memberRepository.findAll()){
                if(team.getTeamID() == member.getTeamID()){
                    teamMembers.add(member);
                }
            }
            sortedTeams.add(teamMembers);
        }
        return sortedTeams;
    }

    public void save(Team team){
        teamRepository.save(team);
    }

    public void delete(int teamID){
        teamRepository.delete(teamID);
    }

    public Team getTeamByID(int id){
        return teamRepository.findOne(id);
    }
}
