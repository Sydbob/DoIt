package com.doIt.DoIt.controller;

import com.doIt.DoIt.entity.Task;
import com.doIt.DoIt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

//notes: use rest controller, return response obj not Strings
@Controller
public class MainController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String home(HttpServletRequest request){
        request.setAttribute("mode", "MODE_HOME");
        return "home";
    }

    @GetMapping("/tasks")
    public String allTasks(HttpServletRequest request){
        request.setAttribute("tasks", taskService.getAllTasks());
        request.setAttribute("mode", "MODE_TASKS");
        return "tasks";
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam int id, HttpServletRequest request){
        taskService.delete(id);
        request.setAttribute("tasks", taskService.getAllTasks());
        request.setAttribute("mode", "MODE_TASKS");
        return "index";
    }

    @GetMapping("/new-task")
    public String newTask(HttpServletRequest request){
        request.setAttribute("mode", "MODE_NEW");
        return "new";
    }

    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
        taskService.save(task);
        request.setAttribute("tasks", taskService.getAllTasks());
        request.setAttribute("mode", "MODE_TASKS");
        return "index";
    }

    @GetMapping("/update-task")
    public String updateTask(@RequestParam int id, HttpServletRequest request){
        request.setAttribute("task", taskService.findTask(id));
        request.setAttribute("mode", "MODE_UPDATE");
        return "update";
    }

    //Addition validation check method, T Smith 3/4/18 unsure on  "/check-task" and "redirect:/results"
    @PostMapping("/check-task")
    public String checkTaskInfo(@Valid Task task, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "form";
        }
        return "redirect:/index";
    }





}
