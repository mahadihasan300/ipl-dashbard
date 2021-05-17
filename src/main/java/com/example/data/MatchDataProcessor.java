package com.example.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.example.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match>{
	
	 private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	  @Override
	  public Match process(final MatchInput matchInput) throws Exception {
	   
		  Match match = new Match();
		  
		  match.setId(Long.parseLong(matchInput.getId()));
		  match.setCity(matchInput.getCity());
		  
		  match.setDate(LocalDate.parse(matchInput.getDate()));
		  
		  match.setPlayerOfMatch(matchInput.getPlayer_of_match());
		  
		  // Team 1 will be always who played 1st innings
		  String firstInningnsTeam, secoundInnigsTeam;
		  
		  if("bat".equals(matchInput.getToss_decision())) {
			  firstInningnsTeam = matchInput.getToss_winner();
			  secoundInnigsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) 
					  ? matchInput.getTeam2() : matchInput.getTeam1();
			  
		  } else {
			  secoundInnigsTeam = matchInput.getToss_winner();
			  firstInningnsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) 
					  ? matchInput.getTeam2() : matchInput.getTeam1();
		  }
		  
		  match.setTeam1(firstInningnsTeam);
		  match.setTeam2(secoundInnigsTeam);
		  match.setMatchWinner(matchInput.getWinner());
		  
		  match.setTossWinner(matchInput.getToss_winner());
		  match.setTossDecision(matchInput.getToss_decision());
		  match.setResult(matchInput.getResult());
		  match.setResultMargin(match.getResultMargin());
		  match.setUmpire1(matchInput.getUmpire1());
		  match.setUmpire2(matchInput.getUmpire2());
		  
		  return match;
	  }

}
