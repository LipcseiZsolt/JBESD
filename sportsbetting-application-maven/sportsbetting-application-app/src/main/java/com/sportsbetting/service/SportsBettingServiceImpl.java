/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.service;

import com.sportsbetting.builder.BetBuilder;
import com.sportsbetting.builder.OutcomeBuilder;
import com.sportsbetting.builder.OutcomeOddBuilder;
import com.sportsbetting.builder.SportEventBuilder;
import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.BetType;
import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.Result;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;
import com.sportsbetting.repository.BetRepository;
import com.sportsbetting.repository.OutcomeOddRepository;
import com.sportsbetting.repository.OutcomeRepository;
import com.sportsbetting.repository.ResultRepository;
import com.sportsbetting.repository.SportEventRepository;
import com.sportsbetting.repository.UserRepository;
import com.sportsbetting.repository.WagerRepository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author Lipcsei Zsolt
 */
public class SportsBettingServiceImpl implements SportsBettingService{
    
    private Player player;
    
    private WagerRepository wagerRepository;
	private SportEventRepository sportEventRepository;
    private BetRepository betRepository;
    private ResultRepository resultRepository;
    private OutcomeRepository outcomeRepository;
    private OutcomeOddRepository outcomeOddRepository;
    private UserRepository userRepository;
    
	public SportsBettingServiceImpl(){
    }
    
    public WagerRepository getWagerRepository() {
		return wagerRepository;
	}

	public void setWagerRepository(WagerRepository wagerRepository) {
		this.wagerRepository = wagerRepository;
	}

	public SportEventRepository getSportEventRepository() {
		return sportEventRepository;
	}

	public void setSportEventRepository(SportEventRepository sportEventRepository) {
		this.sportEventRepository = sportEventRepository;
	}

	public BetRepository getBetRepository() {
		return betRepository;
	}

	public void setBetRepository(BetRepository betRepository) {
		this.betRepository = betRepository;
	}

	public ResultRepository getResultRepository() {
		return resultRepository;
	}

	public void setResultRepository(ResultRepository resultRepository) {
		this.resultRepository = resultRepository;
	}

	public OutcomeRepository getOutcomeRepository() {
		return outcomeRepository;
	}

	public void setOutcomeRepository(OutcomeRepository outcomeRepository) {
		this.outcomeRepository = outcomeRepository;
	}

	public OutcomeOddRepository getOutcomeOddRepository() {
		return outcomeOddRepository;
	}

	public void setOutcomeOddRepository(OutcomeOddRepository outcomeOddRepository) {
		this.outcomeOddRepository = outcomeOddRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
  
    @Override
    public void savePlayer(Player newPlayer) {
        player = newPlayer;
        userRepository.save(newPlayer);
    }

    @Override
    public Player findPlayer() {
        return this.player;
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        //return new ArrayList<>(sportEvents);
    	List<SportEvent> re = new LinkedList<>();
    	sportEventRepository.findAll().forEach(re::add);
    	return re;
    }

    @Override
    public void saveWager(Wager wager) {
        Player wagerPlayer = wager.getPlayer();
        wagerPlayer.setBalance(wagerPlayer.getBalance().subtract(wager.getAmount()));
        //wagers.add(wager);
        userRepository.save(player);
        wagerRepository.save(wager);
    }

    @Override
    public List<Wager> findAllWagers() {
        //return new ArrayList<>(wagers);
    	List<Wager> re = new LinkedList<>();
    	wagerRepository.findAll().forEach(re::add);
    	return re;
    }

    
    @Override
    public void calculateResults() {
    	List<Outcome> winnerOutcomes = new LinkedList<>();
        Random r = new Random();
        int amount = 0; 
        int oddsSize;
        for (SportEvent sportEvent : findAllSportEvents()) {
        	winnerOutcomes = sportEvent.getResult().getWinnerOutcomes();
        	for (Bet bet : sportEvent.getBets()) {
        		for (Outcome outcome : bet.getOutcomes()) {
        			if (r.nextBoolean()) {
        				winnerOutcomes.add(outcome);
        			}
        		}
        	}
        	sportEvent.getResult().setWinnerOutcomes(winnerOutcomes);
        	sportEventRepository.save(sportEvent);
        	resultRepository.save(sportEvent.getResult());
        }
        
        for (Wager wager : findAllWagers()) {
            oddsSize = wager.getOdd().getOutcome().getOutcomeOdds().size()-1;
            wager.setOdd(wager.getOdd().getOutcome().getOutcomeOdds().get(oddsSize));
            if (wager.getPlayer().getId() == player.getId()) {
                if (wager.getOdd().getOutcome().getBet().getEvent().getResult().getWinnerOutcomes().contains(wager.getOdd().getOutcome())) {
                    amount += wager.getAmount().multiply(wager.getOdd().getValue()).intValue();
                    wager.setProcessed(true);
                    wager.setWin(true);
                    winnerOutcomes.add(wager.getOdd().getOutcome());
                }
            }
            wagerRepository.save(wager);
        }
        
        
        player.setBalance(player.getBalance().add(BigDecimal.valueOf(amount)));
        userRepository.save(player);
    }
    
    public void setMockData(){ 
        SportEventBuilder seb = new SportEventBuilder();
        seb.setTitle("Arsenal vs Chelse");
        seb.setStartDate(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 00, 00));
        SportEvent seavc = seb.buildFootballSportEvent();
        
        
        BetBuilder bb = new BetBuilder();
        bb.setBetType(BetType.PLAYERS_SCORE);
        bb.setDescription("Oliver Giroud score");
        bb.setSportEvent(seavc);
        bb.setOutcomes(new LinkedList<>());
        Bet seavcPlayerScoreBet = bb.build();
        List<Bet> seavcBets = seavc.getBets();
        seavcBets.add(seavcPlayerScoreBet);
        
        
        bb.setBetType(BetType.WINNER);
        bb.setDescription("winner");
        bb.setOutcomes(new LinkedList<>());
        Bet seavcWinnerBet = bb.build();
        seavcBets.add(seavcWinnerBet);
        seavc.setBets(seavcBets);
        
        OutcomeBuilder ob = new OutcomeBuilder();
        ob.setDescription("1");
        ob.setBet(seavcPlayerScoreBet);
        ob.setOutcomeOdds(new LinkedList<>());
        Outcome seavcPlayerScoreBetOutcomeOne = ob.build();
        List<Outcome> seavcPlayerScoreBetOutcomes = seavcPlayerScoreBet.getOutcomes();
        seavcPlayerScoreBetOutcomes.add(seavcPlayerScoreBetOutcomeOne);
        
        ob.setDescription("3");
        ob.setBet(seavcPlayerScoreBet);
        ob.setOutcomeOdds(new LinkedList<>());
        Outcome seavcPlayerScoreBetOutcomeThree = ob.build();
        seavcPlayerScoreBetOutcomes.add(seavcPlayerScoreBetOutcomeThree);
        seavcPlayerScoreBet.setOutcomes(seavcPlayerScoreBetOutcomes);
        
        ob.setDescription("Arsenal");
        ob.setBet(seavcWinnerBet);
        ob.setOutcomeOdds(new LinkedList<>());
        Outcome seavcWinnerOutcomeArsenal = ob.build();
        List<Outcome> seavcWinnerBetOutcomes = seavcWinnerBet.getOutcomes();
        seavcWinnerBetOutcomes.add(seavcWinnerOutcomeArsenal);
        
        ob.setDescription("Chelsea");
        ob.setBet(seavcWinnerBet);
        ob.setOutcomeOdds(new LinkedList<>());
        Outcome seavcWinnerOutcomeChelsea = ob.build();
        seavcWinnerBetOutcomes.add(seavcWinnerOutcomeChelsea);
        seavcWinnerBet.setOutcomes(seavcWinnerBetOutcomes);
        
        OutcomeOddBuilder oob = new OutcomeOddBuilder();
        oob.setOutcome(seavcPlayerScoreBetOutcomeOne);
        oob.setValue(new BigDecimal(1));
        oob.setValidFrom(LocalDateTime.of(2000, Month.JANUARY, 01, 12, 0, 0));
        oob.setValidUntil(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 0, 0));
        OutcomeOdd seavcPlayerScoreBetOutcomeOddOne_One = oob.build();
        List<OutcomeOdd> seavcPlayerScoreBetOutcomeOneOutcomeOdds = seavcPlayerScoreBetOutcomeOne.getOutcomeOdds();
        seavcPlayerScoreBetOutcomeOneOutcomeOdds.add(seavcPlayerScoreBetOutcomeOddOne_One);
        seavcPlayerScoreBetOutcomeOne.setOutcomeOdds(seavcPlayerScoreBetOutcomeOneOutcomeOdds);
        
        oob.setOutcome(seavcPlayerScoreBetOutcomeThree);
        oob.setValue(new BigDecimal(3));
        oob.setValidFrom(LocalDateTime.of(2000, Month.JANUARY, 01, 12, 0, 0));
        oob.setValidUntil(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 0, 0));
        OutcomeOdd seavcPlayerScoreBetOutcomeOddThree_Three = oob.build();
        List<OutcomeOdd> seavcPlayerScoreBetOutcomeThreeOutcomeOdds = seavcPlayerScoreBetOutcomeThree.getOutcomeOdds();
        seavcPlayerScoreBetOutcomeThreeOutcomeOdds.add(seavcPlayerScoreBetOutcomeOddThree_Three);
        seavcPlayerScoreBetOutcomeThree.setOutcomeOdds(seavcPlayerScoreBetOutcomeThreeOutcomeOdds);
        
        oob.setOutcome(seavcWinnerOutcomeArsenal);
        oob.setValue(new BigDecimal(2));
        oob.setValidFrom(LocalDateTime.of(2000, Month.JANUARY, 01, 12, 0, 0));
        oob.setValidUntil(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 0, 0));
        OutcomeOdd seavcWinnerBetOutcomeOddArsenal_Two = oob.build();
        List<OutcomeOdd> seavcWinnerOutcomeArsenalOutcomeOdds = seavcWinnerOutcomeArsenal.getOutcomeOdds();
        seavcWinnerOutcomeArsenalOutcomeOdds.add(seavcWinnerBetOutcomeOddArsenal_Two);
        seavcWinnerOutcomeArsenal.setOutcomeOdds(seavcWinnerOutcomeArsenalOutcomeOdds);
        
        oob.setOutcome(seavcWinnerOutcomeChelsea);
        oob.setValue(new BigDecimal(3));
        oob.setValidFrom(LocalDateTime.of(2000, Month.JANUARY, 01, 12, 0, 0));
        oob.setValidUntil(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 0, 0));
        OutcomeOdd seavcWinnerBetOutcomeOddChelsea_Three = oob.build();
        List<OutcomeOdd> seavcWinnerOutcomeChelseaOutcomeOdds = seavcWinnerOutcomeChelsea.getOutcomeOdds();
        seavcWinnerOutcomeChelseaOutcomeOdds.add(seavcWinnerBetOutcomeOddChelsea_Three);
        seavcWinnerOutcomeChelsea.setOutcomeOdds(seavcWinnerOutcomeChelseaOutcomeOdds);
        
        sportEventRepository.save(seavc);
        betRepository.saveAll(seavcBets);
        outcomeRepository.saveAll(seavcPlayerScoreBetOutcomes);
        outcomeRepository.saveAll(seavcWinnerBetOutcomes);
        outcomeOddRepository.saveAll(seavcPlayerScoreBetOutcomeOneOutcomeOdds);
        outcomeOddRepository.saveAll(seavcPlayerScoreBetOutcomeThreeOutcomeOdds);
        outcomeOddRepository.saveAll(seavcWinnerOutcomeArsenalOutcomeOdds);
        outcomeOddRepository.saveAll(seavcWinnerOutcomeChelseaOutcomeOdds);
        outcomeRepository.save(seavcPlayerScoreBetOutcomeThree);
        outcomeRepository.save(seavcWinnerOutcomeChelsea);
        outcomeRepository.saveAll(seavcWinnerBetOutcomes);
        outcomeRepository.save(seavcWinnerOutcomeArsenal);
    }
}
