package com.bananaj.mgr.repository;

import com.bananaj.mgr.domain.TestCase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseRepository extends CrudRepository<TestCase, String> {

    @Query("select i from TestCase i join i.project j where j.id = ?1")
    public List<TestCase> findTestCasesForProjectId(String projectId);

}
