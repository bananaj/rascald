package com.bananaj.tcm.domain;

import com.bananaj.tcm.domain.TestCase;
import com.bananaj.user.domain.User;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by cio on 08/02/15.
 */
public class Project {

    private String id;
    private String name;
    private String description;

    private User owner;
    private Set<TestCase> testCases = new HashSet<TestCase>();
    private Set<User> users = new HashSet<User>();

    public Project(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Set<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(Set<TestCase> testCases) {
        this.testCases = testCases;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
