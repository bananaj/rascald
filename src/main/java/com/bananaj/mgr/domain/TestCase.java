package com.bananaj.mgr.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cio on 07/02/15.
 */
public class TestCase {

    private String id;
    private String title;

    private Project project;
    private Set<TestCaseInfo> infos = new HashSet<TestCaseInfo>();

    public TestCase(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<TestCaseInfo> getInfos() {
        return infos;
    }

    public void setInfos(Set<TestCaseInfo> infos) {
        this.infos = infos;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
