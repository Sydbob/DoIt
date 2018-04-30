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

/**Controller for the task entity.
Handles all requests involving task and provided page mapping for the entity as well. */
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;


    /** Mapping for the task page
     * "tasks" attribute returns a list of logged-in user's tasks */
    @GetMapping("/tasks")
    public String usersTasks(Authentication auth, HttpServletRequest request){
        request.setAttribute("tasks", taskService.getTasksByUsername(auth.getName()));
        request.setAttribute("username", auth.getName());
        request.setAttribute("mode", "MODE_TASKS");
        return "tasks";
    }


    /** Mapping for the all tasks page
     * "tasks" attribute returns a list of all tasks that are in the database
     * admin-only accessible page*/
    @GetMapping("/admin/all-tasks")
    public String allTasks(HttpServletRequest request, Authentication authentication){
        request.setAttribute("tasks", taskService.getAllTasks());
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_TASKS");
        return "tasks";
    }


    /** Mapping for the delete task option
     * "tasks" attribute returns a list of logged-in user's tasks
     * delets a task based on the id provided*/
    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam int id, HttpServletRequest request, Authentication auth){
        taskService.delete(id);
        request.setAttribute("tasks", taskService.getTasksByUsername(auth.getName()));
        request.setAttribute("username", auth.getName());
        request.setAttribute("mode", "MODE_TASKS");
        return "tasks";
    }

    /** Mapping for the new task option*/
    @GetMapping("/new-task")
    public String newTask(HttpServletRequest request, Authentication authentication){
        request.setAttribute("mode", "MODE_NEW");
        request.setAttribute("username", authentication.getName());
        return "newtask";
    }

    /** Mapping for the save task option
     * "tasks" attribute returns a list of logged-in user's tasks */
    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request, Authentication auth){
        taskService.save(task);
        request.setAttribute("tasks",taskService.getTasksByUsername(auth.getName()));
        request.setAttribute("username", auth.getName());
        request.setAttribute("mode", "MODE_TASKS");
        return "tasks";
    }

    /** Mapping for the delete task option
     * "task" attribute returns a task based on the id provided */
    @GetMapping("/update-task")
    public String updateTask(@RequestParam int id, HttpServletRequest request, Authentication authentication){
        request.setAttribute("task", taskService.findTask(id));
        request.setAttribute("username", authentication.getName());
        request.setAttribute("mode", "MODE_UPDATE");
        return "updatetask";
    }



}
