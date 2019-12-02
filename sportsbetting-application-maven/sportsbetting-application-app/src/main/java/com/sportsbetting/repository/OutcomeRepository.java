package com.sportsbetting.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportsbetting.domain.Outcome;

public interface OutcomeRepository extends CrudRepository<Outcome, Long>{

}
