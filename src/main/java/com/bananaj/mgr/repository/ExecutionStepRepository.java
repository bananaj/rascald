package com.bananaj.mgr.repository;

import com.bananaj.mgr.domain.ExecutionStep;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionStepRepository extends CrudRepository<ExecutionStep, String> {


}
