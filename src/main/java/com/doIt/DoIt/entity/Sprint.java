package com.doIt.DoIt.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that corresponds to Task entity in the database
 * Data members are named accordingly to the entity attributes in the database
 * Certain fields are annotated used @ annotations
 */
@Entity(name = "Sprint")
public class Sprint implements Serializable{

    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int sprintID;

    private int projectID;
    private int teamID;

    //had to use conversion here for the date to make it work with the html code
    @Column(name = "deadline", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime deadline;

    @Column(name = "start_date", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime start_date;

   //===============constructor(s)=================

    public Sprint(int sprintID, int projectID, int teamID, LocalDateTime deadline, LocalDateTime start_date) {
        this.sprintID = sprintID;
        this.projectID = projectID;
        this.teamID = teamID;
        this.deadline = deadline;
        this.start_date = start_date;
    }

    //================getters and setters===============

    public int getSprintID() {
        return sprintID;
    }

    public void setSprintID(int sprintID) {
        this.sprintID = sprintID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }
}
