package com.sportsbetting.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportsbetting.domain.SportEvent;

public interface SportEventRepository extends CrudRepository<SportEvent, Long> {

}
