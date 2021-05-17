package com.example.controller;

import com.example.model.Team;
import com.example.repository.MatchRepository;
import com.example.repository.TeamRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("team/{teamName}")
    public Team getTeam(@PathVariable String teamName){

       Team team = this.teamRepository.findByTeamName(teamName);
       /*
       This is a good practice to not to introduce data related staf in Controller, so in Repository
       we call the Pagable
        */
       //Pageable pageable = PageRequest.of(0, 4);
       //team.setMatches(matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable));

        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName, 4));

       return team;

    }
}
