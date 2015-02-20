package com.bananaj.tcm.repository;

import com.bananaj.tcm.domain.ExecutionStep;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionStepRepository extends CrudRepository<ExecutionStep, String> {


}
