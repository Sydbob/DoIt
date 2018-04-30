package com.doIt.DoIt.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
/**
 * Class that corresponds to Member entity in the database
 * Data members are named accordingly to the entity attributes in the database
 * Certain fields are annotated used @ annotations
 */
@Entity
public class Member implements Serializable {

    @Id
    @NotEmpty(message = "Username cannot be empty")
    @Column(name ="username")
    private String username;

    @NotEmpty(message = "Please provide a password")
    @Length(min = 5, max = 30, message = "Your password must be 5 and 30 characters long")
    @Transient
    @Column(name = "password")
    private String password;

    @Column(name = "teamID")
    private int teamID;

    @Column(name = "name")
    @NotEmpty(message = "Please provide a name")
    private String name;

    @Column(name = "email")
    @NotEmpty(message = "Please provide an email")
    private String email;

    @Column(name = "telephone")
    @NotEmpty(message = "Please provide a phone number")
    private String telephone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "member_roles", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "roleID"))
    private Set<Role> roles;

    //===============constructor(s)=================
    public Member() {}
    public Member(String username, String password, int teamID, String name, String email, String telephone) {
        this.username = username;
        this.password = password;
        this.teamID = teamID;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }

    //===============getters and setters=================

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
