package com.doIt.DoIt.controller;

import com.doIt.DoIt.service.SprintService;
import com.doIt.DoIt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**Controller for the dashboard.
 Handles all requests involving dashboard page. */
@Controller
public class DashboardController {

    @Autowired
    private  TaskService taskService;
    @Autowired
    private SprintService sprintService;


    /**Mapping for home page and dashboard page
     * "tasks" returns logged-in user's tasks
     * "username" returns logged in user's username
     * */
    @RequestMapping( value = {"/dashboard", "/home"}, method = RequestMethod.GET)
    public String dashboard(Authentication auth, HttpServletRequest request){
        request.setAttribute("tasks", taskService.getTasksByUsername(auth.getName()));
        request.setAttribute("username", auth.getName());
       return "dashboard";
    }
}
