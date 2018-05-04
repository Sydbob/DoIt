package com.doIt.DoIt.controller;

import com.doIt.DoIt.entity.Member;
import com.doIt.DoIt.entity.Sprint;
import com.doIt.DoIt.service.MemberService;
import com.doIt.DoIt.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**Controller for the sprint entity.
 Handles all requests involving sprint and provided page mapping for the entity as well. */
@Controller
public class SprintController {

    @Autowired
    private SprintService sprintService;
    @Autowired
    private MemberService memberService;

    /** Mapping for the sprints page
     * "sprints" attribute returns a list of logged-in user's sprints (based on their team id since sprints are assigned to teams)*/
    @GetMapping("/sprints")
    public String usersSprints(Authentication auth, HttpServletRequest request){
        request.setAttribute("sprints", sprintService.getAllSprintsByTeamID(memberService.findUserByUsername(auth.getName()).getTeamID()));
        request.setAttribute("allsprints", sprintService.getAllSprints());
        request.setAttribute("username", auth.getName());
        request.setAttribute("allsprints", sprintService.getAllSprints());
        request.setAttribute("isAdmin", memberService.isAdmin(auth.getName()));
        request.setAttribute("mode", "MODE_SPRINTS");
        return "sprints";
    }

    /** Mapping for the all sprints page
     * "sprints" attribute returns a list of all sprints in the database
     * admin-accessible only*/
    @GetMapping("/admin/all-sprints")
    public String allSprints(HttpServletRequest request, Authentication authentication){
        request.setAttribute("sprints", sprintService.getAllSprintsByTeamID(memberService.findUserByUsername(authentication.getName()).getTeamID()));
        request.setAttribute("allsprints", sprintService.getAllSprints());
        request.setAttribute("isAdmin", memberService.isAdmin(authentication.getName()));
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_SPRINTS");
        return "sprints";
    }

    /** Mapping for the delete sprint option
     * "sprints" attribute returns a list of logged-in user's sprints
     * deletes a sprint based on the id provided*/
    @GetMapping("/delete-sprint")
    public String deleteSprint(@RequestParam int id, HttpServletRequest request, Authentication auth){
        sprintService.delete(id);
        request.setAttribute("sprints", sprintService.getAllSprintsByTeamID(memberService.findUserByUsername(auth.getName()).getTeamID()));
        request.setAttribute("allsprints", sprintService.getAllSprints());
        request.setAttribute("username", auth.getName());
        request.setAttribute("isAdmin", memberService.isAdmin(auth.getName()));
        request.setAttribute("mode", "MODE_SPRINTS");
        return "sprints";
    }

    /** Mapping for the new sprint option*/
    @GetMapping("/new-sprint")
    public String newSprint (HttpServletRequest request, Authentication authentication){
        request.setAttribute("mode", "MODE_NEW");
        request.setAttribute("username", authentication.getName());
        return "newsprint";
    }

    /** Mapping for the save sprint option
     * "sprints" attribute returns a list of logged-in user's sprints*/
    @PostMapping("/save-sprint")
    public String saveSprint(@ModelAttribute Sprint sprint, BindingResult bindingResult, HttpServletRequest request, Authentication auth){
        sprintService.save(sprint);
        request.setAttribute("sprints", sprintService.getAllSprintsByTeamID(memberService.findUserByUsername(auth.getName()).getTeamID()));
        request.setAttribute("allsprints", sprintService.getAllSprints());
        request.setAttribute("username", auth.getName());
        request.setAttribute("isAdmin", memberService.isAdmin(auth.getName()));
        request.setAttribute("mode", "MODE_SPRINTS");
        return "sprints";
    }

    /** Mapping for the delete sprint option
     * "sprint" attribute returns a sprint based on the id*/
    @GetMapping("/update-sprint")
    public String updateSprint(@RequestParam int id, HttpServletRequest request, Authentication authentication){
        request.setAttribute("sprints", sprintService.getAllSprintsByTeamID(memberService.findUserByUsername(authentication.getName()).getTeamID()));
        request.setAttribute("allsprints", sprintService.getAllSprints());
        request.setAttribute("username", authentication.getName());
        request.setAttribute("isAdmin", memberService.isAdmin(authentication.getName()));
        request.setAttribute("mode", "MODE_UPDATE");
        return "updatesprint";
    }
}
