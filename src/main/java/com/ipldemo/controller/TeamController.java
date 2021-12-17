package com.ipldemo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipldemo.model.Match;
import com.ipldemo.model.Team;
import com.ipldemo.repository.MatchRepository;
import com.ipldemo.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {

	private TeamRepository teamRepo;
	private MatchRepository matchRepo;

	@Autowired
	public TeamController(TeamRepository teamRepo, MatchRepository matchRepo) {
		this.teamRepo = teamRepo;
		this.matchRepo = matchRepo;
		
	}
	
	@GetMapping("/teams")
	public Iterable<Team> getAllTeams(){
		return this.teamRepo.findAll();
	
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = this.teamRepo.findByTeamName(teamName);
		// Pageable pageable = PageRequest.of(0, 5);
		// team.setMatches(matchRepo.getByTeam1OrTeam2OrderByDateDesc(teamName,
		// teamName,pageable ));
		team.setMatches(matchRepo.findLatestMatchesByTeam(teamName, 5));
		return team;
	}

	@GetMapping("/team/{teamName}/matches")
	public List<Match> geMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year + 1, 1, 1);
		return this.matchRepo.getMatchesByTeamBetweenDates(
				teamName, startDate, endDate);

	}
}
