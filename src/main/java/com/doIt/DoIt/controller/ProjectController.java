package com.doIt.DoIt.controller;
import com.doIt.DoIt.entity.Project;
import com.doIt.DoIt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**Controller for the project entity.
 Handles all requests involving project and provided page mapping for the entity as well. */
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /** Mapping for the project page
     * "projects" attribute returns a list of all projects
     * "username" return username of a currently loggen in user*/
    @GetMapping("/projects")
    public String allProjects(HttpServletRequest request, Authentication authentication){
        request.setAttribute("projects", projectService.getAllProjects());
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_PROJECTS");
        return "projects";
    }



    /** Mapping for the delete project
     * "projects" attribute returns a list of all projects
     * "username" return username of a currently logged in user*/
    @GetMapping("/delete-project")
    public String deleteProject(@RequestParam int id, HttpServletRequest request, Authentication authentication){
        projectService.delete(id);
        request.setAttribute("projects", projectService.getAllProjects());
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_PROJECTS");
        return "projects";
    }

    /** Mapping for the new project page
     * "username" return username of a currently loggen in user*/
    @GetMapping("/new-project")
    public String newProject (HttpServletRequest request, Authentication authentication){
        request.setAttribute("mode", "MODE_NEW");
        request.setAttribute("username", authentication.getName());
        return "newproject";
    }

    /** Mapping for the save project
     * "projects" attribute returns a list of all projects
     * "username" return username of a currently logged in user*/
    @PostMapping("/save-project")
    public String saveTask(@ModelAttribute Project proj, BindingResult bindingResult, HttpServletRequest request, Authentication authentication){
        projectService.save(proj);
        request.setAttribute("projects", projectService.getAllProjects());
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_PROJECTS");
        return "projects";
    }

    /** Mapping for the update project
     * "projects" attribute returns a list of all projects
     * "username" return username of a currently logged in user*/
    @GetMapping("/update-project")
    public String updateProject(@RequestParam int id, HttpServletRequest request, Authentication authentication){
        request.setAttribute("project", projectService.findProject(id));
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_UPDATE");
        return "updateproject";
    }
}
