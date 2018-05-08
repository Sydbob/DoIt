package com.doIt.DoIt.controller;

import com.doIt.DoIt.entity.Member;
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
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/new-team")
    public String newTask(HttpServletRequest request, Authentication auth){
        request.setAttribute("mode", "MODE_NEW");
        request.setAttribute("username", auth.getName());
        request.setAttribute("isAdmin", memberService.isAdmin(auth.getName()));
        request.setAttribute("myteam", memberService.getTeamMembersByTeamID(memberService.findTeamIDByUsername(auth.getName())));
        request.setAttribute("myteamID", memberService.findTeamIDByUsername(auth.getName()));
        request.setAttribute("myproject", projectService.findProject(teamService.getTeamByID(memberService.findTeamIDByUsername(auth.getName())).getProjectID()));
        request.setAttribute("teams", teamService.getAllTeams());
        request.setAttribute("members", memberService.getAllMembers());
        return "newteam";
    }

    @GetMapping("/update-teams")
    public String editTeam( Authentication auth, HttpServletRequest request){
        request.setAttribute("teams",teamService.getAllTeams());
        request.setAttribute("members", memberService.getAllMembers());
        request.setAttribute("username", auth.getName());
        request.setAttribute("isAdmin", memberService.isAdmin(auth.getName()));
        request.setAttribute("mode", "MODE_UPDATE");
        return "updateteam";
    }

    @PostMapping("/save-team")
    public String saveTeam(@ModelAttribute ArrayList<Member> member, BindingResult bindingResult, HttpServletRequest request, Authentication auth){
        for (Member m : member){
            memberService.saveMemeber(m);
        }
        request.setAttribute("myteam", memberService.getTeamMembersByTeamID(memberService.findTeamIDByUsername(auth.getName())));
        request.setAttribute("myteamID", memberService.findTeamIDByUsername(auth.getName()));
        request.setAttribute("myproject", projectService.findProject(teamService.getTeamByID(memberService.findTeamIDByUsername(auth.getName())).getProjectID()));
        request.setAttribute("teams",teamService.getAllTeams());
        request.setAttribute("members", memberService.getAllMembers());
        request.setAttribute("username", auth.getName());
        request.setAttribute("isAdmin", memberService.isAdmin(auth.getName()));
        request.setAttribute("mode", "MODE_TASKS");
        return "teams";
    }

    @PostMapping("/create-team")
    public String saveTeam(@ModelAttribute Team team, BindingResult bindingResult, HttpServletRequest request, Authentication auth){
        teamService.save(team);
        request.setAttribute("myteam", memberService.getTeamMembersByTeamID(memberService.findTeamIDByUsername(auth.getName())));
        request.setAttribute("myteamID", memberService.findTeamIDByUsername(auth.getName()));
        request.setAttribute("myproject", projectService.findProject(teamService.getTeamByID(memberService.findTeamIDByUsername(auth.getName())).getProjectID()));
        request.setAttribute("teams",teamService.getAllTeams());
        request.setAttribute("members", memberService.getAllMembers());
        request.setAttribute("username", auth.getName());
        request.setAttribute("isAdmin", memberService.isAdmin(auth.getName()));
        request.setAttribute("mode", "MODE_TASKS");
        return "teams";
    }

}
