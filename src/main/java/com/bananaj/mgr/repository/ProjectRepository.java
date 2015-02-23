package com.bananaj.mgr.repository;

import com.bananaj.mgr.domain.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {

    @Query("select e from Project e join e.owner c where c.username = ?1")
    public List<Project> findProjectsForUsername(String username);

}
