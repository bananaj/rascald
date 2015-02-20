package com.bananaj.tcm.repository;

import com.bananaj.tcm.domain.TestCaseInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseInfoRepository extends CrudRepository<TestCaseInfo, String> {

    @Query("select i from TestCaseInfo i join i.testCase j where j.id = ?1")
    public List<TestCaseInfo> findTestCaseInfosForTestCaseId(String testCaseId);

}
