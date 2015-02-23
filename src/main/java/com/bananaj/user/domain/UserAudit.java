package com.bananaj.user.domain;

import java.util.Date;

public class UserAudit {

    /*
    ACTION_CD
        CR - Create
        UP - Update
        LGN - Login

    CONTEXT_CD
        TC - Test Case
        SY - System
        PRJ - Project
        EXE - Execution
    */

    private String id;
    private String actionCd;
    private String contextCd;
    private String contextId;
    private Date date;
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActionCd() {
        return actionCd;
    }

    public void setActionCd(String actionCd) {
        this.actionCd = actionCd;
    }

    public String getContextCd() {
        return contextCd;
    }

    public void setContextCd(String contextCd) {
        this.contextCd = contextCd;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
