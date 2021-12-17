package com.ipldemo.repository;

import org.springframework.data.repository.CrudRepository;

import com.ipldemo.model.Team;

public interface TeamRepository extends CrudRepository<Team,Long>{
	
	Team findByTeamName(String teamName);

}
