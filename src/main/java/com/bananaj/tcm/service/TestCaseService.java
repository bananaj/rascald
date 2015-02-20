package com.bananaj.tcm.service;

import com.bananaj.tcm.domain.Project;
import com.bananaj.tcm.domain.TestCase;
import com.bananaj.tcm.domain.TestCaseInfo;
import com.bananaj.tcm.repository.ProjectRepository;
import com.bananaj.tcm.repository.TestCaseInfoRepository;
import com.bananaj.tcm.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private TestCaseInfoRepository testCaseInfoRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public TestCase saveTestCase(TestCase testCase) {
        if (testCase == null) {
            return null;
        }
        return testCaseRepository.save(testCase);
    }

    public TestCaseInfo saveTestCaseInfo(TestCaseInfo testCaseInfo) {
        if (testCaseInfo == null) {
            return null;
        }
        return testCaseInfoRepository.save(testCaseInfo);
    }

    public TestCase findTestCaseById(String id) {
        if (id == null) {
            return null;
        }
        return testCaseRepository.findOne(id);
    }

    public List<TestCaseInfo> findTestCaseInfosForTestCaseId(String testCaseId) {
        if (testCaseId == null) {
            return null;
        }
        return testCaseInfoRepository.findTestCaseInfosForTestCaseId(testCaseId);
    }

    public Project findProjectById(String id) {
        if (id == null) {
            return null;
        }
        return projectRepository.findOne(id);
    }

    public List<TestCase> findTestCasesForProjectId(String projectId) {
        if (projectId == null) {
            return null;
        }
        return testCaseRepository.findTestCasesForProjectId(projectId);
    }



}
