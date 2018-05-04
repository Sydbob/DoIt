package com.doIt.DoIt.controller;

import com.doIt.DoIt.entity.Role;
import com.doIt.DoIt.entity.Team;
import com.doIt.DoIt.service.MemberService;
import com.doIt.DoIt.service.ProjectService;
import com.doIt.DoIt.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**Controller for the team entity.
Handles all requests involving team and provided page mapping for the entity as well. */
@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProjectService projectService;


  /** Mapping for the team page
  * "myTeams" attribute returns a list of team that the logged in user is a part of at the moment
  * "members" attribute returns a list of all teams/members */
    @GetMapping("/teams")
    public String usersTeams(Authentication auth, HttpServletRequest request){
        request.setAttribute("myteam", memberService.getTeamMembersByTeamID(memberService.findTeamIDByUsername(auth.getName())));
        request.setAttribute("myteamID", memberService.findTeamIDByUsername(auth.getName()));
        request.setAttribute("myproject", projectService.findProject(teamService.getTeamByID(memberService.findTeamIDByUsername(auth.getName())).getProjectID()));
        request.setAttribute("teams", teamService.getAllTeams());
        request.setAttribute("members", memberService.getAllMembers());
        request.setAttribute("username", auth.getName());
        request.setAttribute("isAdmin", memberService.isAdmin(auth.getName()));
        request.setAttribute("mode", "MODE_TEAMS");

        return "teams";
    }

    @GetMapping("/admin/update-team")
    public String editTeam(@RequestParam int id,  Authentication auth, HttpServletRequest request){
        request.setAttribute("task", teamService.getTeamByID(id));
        request.setAttribute("username", auth.getName());
        request.setAttribute("mode", "MODE_UPDATE");
        return "updateteam";
    }

    @PostMapping("/admin/save-team")
    public String saveTask(@ModelAttribute Team team, BindingResult bindingResult, HttpServletRequest request, Authentication auth){
        teamService.save(team);
        request.setAttribute("myteam", memberService.getTeamMembersByTeamID(memberService.findTeamIDByUsername(auth.getName())));
        request.setAttribute("myteamID", memberService.findTeamIDByUsername(auth.getName()));
        request.setAttribute("myproject", projectService.findProject(teamService.getTeamByID(memberService.findTeamIDByUsername(auth.getName())).getProjectID()));
        request.setAttribute("teams",teamService.getAllTeams());
        request.setAttribute("members", memberService.getAllMembers());
        request.setAttribute("username", auth.getName());
        request.setAttribute("mode", "MODE_TASKS");
        return "teams";
    }
}
