package com.doIt.DoIt.controller;

import com.doIt.DoIt.entity.Sprint;
import com.doIt.DoIt.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @GetMapping("/sprints")
    public String allSprints(HttpServletRequest request){
        request.setAttribute("sprints", sprintService.getAllSprints());
        request.setAttribute("mode", "MODE_SPRINTS");
        return "sprints";
    }

    @GetMapping("/delete-sprint")
    public String deleteSprint(@RequestParam int id, HttpServletRequest request){
        sprintService.delete(id);
        request.setAttribute("sprints", sprintService.getAllSprints());
        request.setAttribute("mode", "MODE_SPRINTS");
        return "sprints";
    }

    @GetMapping("/new-sprint")
    public String newSprint (HttpServletRequest request){
        request.setAttribute("mode", "MODE_NEW");
        return "newsprint";
    }

    @PostMapping("/save-sprint")
    public String saveSprint(@ModelAttribute Sprint sprint, BindingResult bindingResult, HttpServletRequest request){
        sprintService.save(sprint);
        request.setAttribute("sprints", sprintService.getAllSprints());
        request.setAttribute("mode", "MODE_SPRINTS");
        return "sprints";
    }

    @GetMapping("/update-sprint")
    public String updateSprint(@RequestParam int id, HttpServletRequest request){
        request.setAttribute("sprint", sprintService.findSprint(id));
        request.setAttribute("mode", "MODE_UPDATE");
        return "updatesprint";
    }
}
