package com.doIt.DoIt.service;

import com.doIt.DoIt.dao.ProjectRepository;
import com.doIt.DoIt.entity.Project;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
/**
 * Service class for project entity
 * This class has all the methods involving projects
 */
@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects(){
        List<Project> sprints = new ArrayList<>();
        for (Project p: projectRepository.findAll())
            sprints.add(p);
        return sprints;
    }

    public void save(Project project){
        projectRepository.save(project);
    }

    public void delete(int id){
        projectRepository.delete(id);
    }

    public Project findProject(int id){
        return projectRepository.findOne(id);
    }
}
