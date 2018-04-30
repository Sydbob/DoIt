package com.doIt.DoIt.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class that corresponds to Task entity in the database
 * Data members are named accordingly to the entity attributes in the database
 * Certain fields are annotated used @ annotations
 */
@Entity(name = "Task")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    private int taskID;

    private int sprintID;

    @NotEmpty(message = "Please provide a task name")
    @Size(max=50)
    private String name;

    private String username;

    @NotNull
    @Size(max=300)
    private String description;

    @NotNull
    @Size(max=30)
    private String status;

    @NotNull
    private int hours_contributed;

    @NotNull
    private int hours_estimated;

    @NotNull
    private int projectID;

    @Column(name = "start_date", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime start_date;

    @Column(name = "end_date", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime end_date;


    //===============constructor(s)=================
    public Task() {}

    public Task(String name, String description, String status, int hours_contributed, int hours_estimated, int projectID) {
        super();

        if(name.length() > 50 || name == null) {
            throw new IllegalArgumentException("Task name must be specified as a string under 50 characters.");
        }
        else this.name = name;

        if(description.length() > 300 || description == null) {
            throw new IllegalArgumentException("Task description must be specified as a string under 300 characters.");
        }
        else this.description = description;

        if(status.length() > 30 || status == null) {
            throw new IllegalArgumentException("Task status must be specified as a string under 30 characters.");
        }
        else this.status = status;

        this.hours_contributed = hours_contributed;

        this.hours_estimated = hours_estimated;


        this.projectID = projectID;
    }


    //=====================getters and setters========================
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getHours_contributed() {
        return hours_contributed;
    }

    public int getHours_estimated() {
        return hours_estimated;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setName(String name) {
        if(name.length() > 50 || name == null) {
            throw new IllegalArgumentException("Task name must be specified as a string under 50 characters.");
        }
        else this.name = name;
    }


    public int getSprintID() {
        return sprintID;
    }

    public void setSprintID(int sprintID) {
        this.sprintID = sprintID;
    }

    public void setDescription(String description) {
        if (description.length() > 300 || description == null) {
            throw new IllegalArgumentException("Task description must be specified as a string under 300 characters.");
        } else this.description = description;
    }

    public void setStatus(String status) {
        if(status.length() > 30 || status == null) {
            throw new IllegalArgumentException("Task status must be specified as a string under 30 characters.");
        }
        else this.status = status;
    }


    public void setHours_contributed(int hours_contributed) {
        this.hours_contributed = hours_contributed;}

    public void setHours_estimated(int hours_estimated) {
        this.hours_estimated = hours_estimated;}

    public void setProjectID(int projectID) {
        this.projectID = projectID;}

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", hours_contributed=" + hours_contributed +
                ", hours_estimated=" + hours_estimated +
                ", projectID=" + projectID +
                '}';
    }


}
