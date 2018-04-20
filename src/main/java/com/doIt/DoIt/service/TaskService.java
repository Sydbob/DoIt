package com.doIt.DoIt.service;

import com.doIt.DoIt.dao.TaskRepository;
import com.doIt.DoIt.entity.Task;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        List<Task> tasks = new ArrayList<>();
        for(Task t: taskRepository.findAll())
            tasks.add(t);
        return tasks;
    }

    public List<Task> getTasksByUsername(String username){
        List<Task> tasks = new ArrayList<>();
        for(Task t: taskRepository.findAll())
        {
            if(t.getUsername() == username)
                tasks.add(t);
        }
        return tasks;
    }

    public List<Task> getTasksBySprintID(int sprintID){
        List<Task> tasks = new ArrayList<>();
        for(Task t: taskRepository.findAll())
        {
            if(t.getSprintID() == sprintID)
                tasks.add(t);
        }
        return tasks;
    }

    public List<Task> getTasksByProjectID(int projectID){
        List<Task> tasks = new ArrayList<>();
        for(Task t: taskRepository.findAll())
        {
            if(t.getProjectID() == projectID)
                tasks.add(t);
        }
        return tasks;
    }

    public void save(Task task){
        taskRepository.save(task);
    }

    public void delete(int task_ID){
        taskRepository.delete(task_ID);
    }

    public Task findTask(int id){
        return taskRepository.findOne(id);
    }
}
