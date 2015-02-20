package com.bananaj.tcm.domain;

import com.bananaj.user.domain.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cio on 12/02/15.
 */
public class Execution {

    private String id;
    private String comments;
    private String resultCode;
    private String statusCode;
    private User user;
    private Date date;
    private TestCaseInfo testCaseInfo;

    private Set<ExecutionStep> steps = new HashSet<ExecutionStep>();

    public Execution() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TestCaseInfo getTestCaseInfo() {
        return testCaseInfo;
    }

    public void setTestCaseInfo(TestCaseInfo testCaseInfo) {
        this.testCaseInfo = testCaseInfo;
    }

    public Set<ExecutionStep> getSteps() {
        return steps;
    }

    public void setSteps(Set<ExecutionStep> steps) {
        this.steps = steps;
    }
}
