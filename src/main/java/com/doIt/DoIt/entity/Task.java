package com.doIt.DoIt.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

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
    private int taskID;

    private int sprintID;

    @NotEmpty(message = "Please provide a task name")
    @Size(max=50)
    private String name;

    private String username;

    @NotNull
    @Size(max=300)
    private String description;

    private String status;
    private int hours_contributed;
    private int hours_estimated;
    private int projectID;

    @Column(name = "start_date", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start_date;

    @Column(name = "end_date", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime end_date;


    //===============constructor(s)=================
    public Task() {}

    public Task(int taskID, int sprintID, String name, String username, String description, String status, int hours_contributed, int hours_estimated, int projectID, LocalDateTime start_date, LocalDateTime end_date) {
        this.taskID = taskID;
        this.sprintID = sprintID;
        this.name = name;
        this.username = username;
        this.description = description;
        this.status = status;
        this.hours_contributed = hours_contributed;
        this.hours_estimated = hours_estimated;
        this.projectID = projectID;
        this.start_date = start_date;
        this.end_date = end_date;
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

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
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
        this.status = status;
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
