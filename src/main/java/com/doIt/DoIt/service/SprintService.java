package com.doIt.DoIt.service;

import com.doIt.DoIt.dao.SprintRepository;
import com.doIt.DoIt.entity.Sprint;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SprintService {

    private final SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public List<Sprint> getAllSprints(){
        List<Sprint> sprints = new ArrayList<>();
        for (Sprint s: sprintRepository.findAll())
            sprints.add(s);
        return sprints;
    }

    public List<Sprint> getAllSprintsByTeamID(int teamID){
        List<Sprint> sprints = new ArrayList<>();
        for (Sprint s: sprintRepository.findAll())
        {
            if (s.getTeamID() == teamID)
            {
            sprints.add(s);
            }
        }
        return sprints;
    }

    public void save(Sprint sprint){
        sprintRepository.save(sprint);
    }

    public void delete(int sprint_ID){
        sprintRepository.delete(sprint_ID);
    }

    public Sprint findSprint(int id){
        return sprintRepository.findOne(id);
    }
}
