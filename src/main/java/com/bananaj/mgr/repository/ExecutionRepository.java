package com.bananaj.mgr.repository;

import com.bananaj.mgr.domain.Execution;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExecutionRepository extends CrudRepository<Execution, String> {

    @Query("select e from Execution e join e.testCaseInfo c where c.id = ?1")
    public List<Execution> findExecutionsForTestCaseInfoId(String testCaseInfoId);


    @Query("select e from Execution e join e.testCaseInfo f join f.testCase g where g.id = ?1")
    public List<Execution> findExecutionsForTestCaseId(String testCaseId);
}