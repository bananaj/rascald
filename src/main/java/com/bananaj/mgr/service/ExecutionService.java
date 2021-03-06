package com.bananaj.mgr.service;

import com.bananaj.mgr.domain.Execution;
import com.bananaj.mgr.domain.ExecutionStep;
import com.bananaj.mgr.repository.ExecutionRepository;
import com.bananaj.mgr.repository.ExecutionStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutionService {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private ExecutionRepository executionRepository;

    @Autowired
    private ExecutionStepRepository executionStepRepository;

    public Execution saveExecution(Execution execution) {
        if (execution == null) {
            return null;
        }
        return executionRepository.save(execution);
    }

    public ExecutionStep saveExecutionStep(ExecutionStep executionStep) {
        if (executionStep == null) {
            return null;
        }
        return executionStepRepository.save(executionStep);
    }

    public List<Execution> findExecutionsForTestCaseInfoId(String testCaseInfoId){
        if (testCaseInfoId == null) {
            return null;
        }
        return executionRepository.findExecutionsForTestCaseInfoId(testCaseInfoId);
    }


    public List<Execution> findExecutionsForTestCaseId(String testCaseId) {
        if (testCaseId == null) {
            return null;
        }
        return executionRepository.findExecutionsForTestCaseId(testCaseId);

    }


    public Execution findExecutionById(String executionId) {
        if (executionId == null) {
            return null;
        }
        return executionRepository.findOne(executionId);

    }

}
