package com.doIt.DoIt.controller;

import com.doIt.DoIt.service.MemberService;
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
    @Autowired
    private MemberService memberService;

    /*
    @GetMapping("/teams")
    public String usersTeams(HttpServletRequest request){
        request.setAttribute("teams", teamService.getAllTeams());
        request.setAttribute("mode", "MODE_TEAMS");
        return "teams";
    }*/


    //my teams
    @GetMapping("/teams")
    public String usersTeams(Authentication auth, HttpServletRequest request){
        request.setAttribute("teams", memberService.getTeamMembersByTeamID(memberService.findTeamIDByUsername(auth.getName())));
        request.setAttribute("members", memberService);
        request.setAttribute("mode", "MODE_TEAMS");
        return "teams";
    }
}
