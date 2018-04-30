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

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public String allProjects(HttpServletRequest request, Authentication authentication){
        request.setAttribute("projects", projectService.getAllProjects());
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_PROJECTS");
        return "projects";
    }

    @GetMapping("/delete-project")
    public String deleteProject(@RequestParam int id, HttpServletRequest request, Authentication authentication){
        projectService.delete(id);
        request.setAttribute("projects", projectService.getAllProjects());
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_PROJECTS");
        return "projects";
    }

    @GetMapping("/new-project")
    public String newProject (HttpServletRequest request, Authentication authentication){
        request.setAttribute("mode", "MODE_NEW");
        request.setAttribute("username", authentication.getName());
        return "newproject";
    }

    @PostMapping("/save-project")
    public String saveTask(@ModelAttribute Project proj, BindingResult bindingResult, HttpServletRequest request, Authentication authentication){
        projectService.save(proj);
        request.setAttribute("projects", projectService.getAllProjects());
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_PROJECTS");
        return "projects";
    }

    @GetMapping("/update-project")
    public String updateProject(@RequestParam int id, HttpServletRequest request, Authentication authentication){
        request.setAttribute("project", projectService.findProject(id));
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_UPDATE");
        return "update";
    }
}
