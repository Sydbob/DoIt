package com.doIt.DoIt.controller;

import com.doIt.DoIt.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
