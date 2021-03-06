package com.doIt.DoIt.service;

import com.doIt.DoIt.dao.TaskRepository;
import com.doIt.DoIt.entity.Task;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for task entity
 * This class has all the methods involving tasks
 */
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

    //returns all tasks of a specific person
    public List<Task> getTasksByUsername(String username){
        List<Task> myTasks = new ArrayList<>();
        for(Task t: taskRepository.findAll())
        {
            if(t.getUsername().equals(username))
            {
                myTasks.add(t);
            }
        }
        return myTasks;
    }

    //returns all tasks on a particular sprint
    public List<Task> getTasksBySprintID(int sprintID){
        List<Task> tasks = new ArrayList<>();
        for(Task t: taskRepository.findAll())
        {
            if(t.getSprintID() == sprintID)
                tasks.add(t);
        }
        return tasks;
    }

    //returns all that on the specific project
    public List<Task> getTasksByProjectID(int projectID){
        List<Task> tasks = new ArrayList<>();
        for(Task t: taskRepository.findAll())
        {
            if(t.getProjectID() == projectID)
                tasks.add(t);
        }
        return tasks;
    }

    public int getNumTasksDone(String username){
        int amount = 0;
        for (Task t : taskRepository.findAll()){
            if (t.getStatus().equals("completed") && t.getUsername().equals(username)){
                amount++;
            }
        }
        return amount;
    }

    public int getNumTasksOverdue(String username){
        int amount = 0;
        for (Task t : taskRepository.findAll()){
            if (t.getStatus().equals("overdue") && t.getUsername().equals(username)){
                amount++;
            }
        }
        return amount;
    }

    public int getNumTasksPending(String username){
        int amount = 0;
        for (Task t : taskRepository.findAll()){
            if (t.getStatus().equals("pending") && t.getUsername().equals(username)){
                amount++;
            }
        }
        return amount;
    }

    public int getNumTasksOnGoing(String username){
        int amount = 0;
        for (Task t : taskRepository.findAll()){
            if ( t.getUsername().equals(username) && t.getStatus().equals("on-going")){
                amount++;
            }
        }
        return amount;
    }

    public int totalHoursContributed(String username){
        int amount = 0;
        for (Task t : taskRepository.findAll()){
            if ( t.getUsername().equals(username)){
                amount+= t.getHours_contributed();
            }
        }
        return amount;
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
