package com.doIt.DoIt.controller;

import com.doIt.DoIt.entity.Task;
import com.doIt.DoIt.service.MemberService;
import com.doIt.DoIt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

//notes: use rest controller, return response obj not Strings
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;


    /*
    @GetMapping("/tasks")
    public String allTasks(@RequestParam String username, HttpServletRequest request){
        taskService.getTasksByUsername(username);
        request.setAttribute("tasks", taskService.getTasksByUsername(username));
        request.setAttribute("mode", "MODE_TASKS");
        return "tasks";
    }*/


    @GetMapping("/tasks")
    public String allTasks(Authentication auth, HttpServletRequest request){
        request.setAttribute("tasks", taskService.getTasksByUsername(auth.getName()));
        request.setAttribute("mode", "MODE_TASKS");
        return "tasks";
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam int id, HttpServletRequest request){
        taskService.delete(id);
        request.setAttribute("tasks", taskService.getAllTasks());
        request.setAttribute("mode", "MODE_TASKS");
        return "tasks";
    }

    @GetMapping("/new-task")
    public String newTask(HttpServletRequest request){
        request.setAttribute("mode", "MODE_NEW");
        return "newtask";
    }

    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
        taskService.save(task);
        request.setAttribute("tasks", taskService.getAllTasks());
        request.setAttribute("mode", "MODE_TASKS");
        return "tasks";
    }

    @GetMapping("/update-task")
    public String updateTask(@RequestParam int id, HttpServletRequest request){
        request.setAttribute("task", taskService.findTask(id));
        request.setAttribute("mode", "MODE_UPDATE");
        return "updatetask";
    }

    //Addition validation check method, T Smith 3/4/18 unsure on  "/check-task" and "redirect:/results"
    @PostMapping("/check-task")
    public String checkTaskInfo(@Valid Task task, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "form";
        }
        return "redirect:/tasks";
    }





}
