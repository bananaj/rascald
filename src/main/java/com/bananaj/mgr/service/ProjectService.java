package com.bananaj.mgr.service;

import com.bananaj.mgr.domain.Project;
import com.bananaj.mgr.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project saveProject(Project project) {
        if (project == null) {
            return null;
        }
        return projectRepository.save(project);
    }


    public Project findProjectById(String id) {
        if (id == null) {
            return null;
        }
        return projectRepository.findOne(id);
    }

    public List<Project> findProjectsForUsername(String username) {
        List<Project> returnValue = new ArrayList<Project>();
        List<Project> owned = projectRepository.findProjectsForUsername(username);
        if (owned != null) {
            returnValue.addAll(owned);
        }

        return returnValue;
    }

}
