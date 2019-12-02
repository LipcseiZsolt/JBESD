package com.sportsbetting.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportsbetting.domain.Bet;

public interface BetRepository extends CrudRepository<Bet, Long> {

}
