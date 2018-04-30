package com.doIt.DoIt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Class that corresponds to Team entity in the database
 * Data members are named accordingly to the entity attributes in the database
 * Certain fields are annotated used @ annotations
 */
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int teamID;

    private int projectID;


}
