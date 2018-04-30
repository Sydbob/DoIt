package com.doIt.DoIt.controller;

import com.doIt.DoIt.service.MemberService;
import com.doIt.DoIt.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**Controller for the team entity.
Handles all requests involving team and provided page mapping for the entity as well. */
@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private MemberService memberService;


  /** Mapping for the team page
  * "myTeams" attribute returns a list of team that the logged in user is a part of at the moment
  * "members" attribute returns a list of all teams/members */
    @GetMapping("/teams")
    public String usersTeams(Authentication auth, HttpServletRequest request){
        request.setAttribute("myTeams", memberService.getTeamMembersByTeamID(memberService.findTeamIDByUsername(auth.getName())));
        request.setAttribute("members", memberService.getAllMembers());
        request.setAttribute("mode", "MODE_TEAMS");
        return "teams";
    }
}
