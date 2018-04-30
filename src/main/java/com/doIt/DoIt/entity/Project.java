package com.doIt.DoIt.entity;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Class that corresponds to Project entity in the database
 * Data members are named accordingly to the entity attributes in the database
 * Certain fields are annotated used @ annotations
 */
@Entity(name = "Project")
public class Project implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int projectID;

    private String title;
    private String description;

    @Column(name = "start_date", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime start_date;

    @Column(name = "end_date", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime end_date;
    private String status;

    //===============constructor(s)=================
    public Project() {}

    public Project(int projectID, String title, String description, LocalDateTime start_date, LocalDateTime end_date, String status) {
        this.projectID = projectID;
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
    }

    //===============getters and setters=================

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
