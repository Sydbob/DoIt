package com.doIt.DoIt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Class that corresponds to Role entity in the database
 * Data members are named accordingly to the entity attributes in the database
 * Certain fields are annotated used @ annotations
 */
@Entity
public class Role {

    @Id
    @Column(name = "roleID")
    private int roleID;

    @Column(name = "name")
    private String name;

    private String description;
    //===============constructor(s)=================
    public Role() {}

    public Role(int roleID, String name, String description) {
        this.roleID = roleID;
        this.name = name;
        this.description = description;
    }

    //===============getters and setters=================
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
