package com.sportsbetting.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportsbetting.domain.Wager;

public interface WagerRepository extends CrudRepository<Wager, Long> {

}
