package com.sportsbetting.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportsbetting.domain.User;

public interface UserRepository extends CrudRepository<User, Long>  {

}
