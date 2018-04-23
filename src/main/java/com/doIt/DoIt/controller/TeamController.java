package com.doIt.DoIt.controller;

import com.doIt.DoIt.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public String usersTeams(HttpServletRequest request){
        request.setAttribute("teams", teamService.getAllTeams());
        request.setAttribute("mode", "MODE_TEAMS");
        return "teams";
    }
}
