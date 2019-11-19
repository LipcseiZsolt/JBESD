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
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Lipcsei Zsolt
 */
public class SportsBettingServiceImpl implements SportsBettingService{
    
    private Player player;
    private final List<Wager> wagers;
    private final List<SportEvent> sportEvents;

    public SportsBettingServiceImpl(){
        this.wagers = new LinkedList<>();
        this.sportEvents = new LinkedList<>();
        setMockData();
    }
    
    @Override
    public void savePlayer(Player newPlayer) {
        player = newPlayer;
    }

    @Override
    public Player findPlayer() {
        return this.player;
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        return new ArrayList<>(sportEvents);
    }

    @Override
    public void saveWager(Wager wager) {
        Player wagerPlayer = wager.getPlayer();
        wagerPlayer.setBalance(wagerPlayer.getBalance().subtract(wager.getAmount()));
        wagers.add(wager);
    }

    @Override
    public List<Wager> findAllWagers() {
        return new ArrayList<>(wagers);
    }

    @Override
    public void calculateResults() {
        Random r = new Random();
        int amount = 0; 
        int oddsSize;
        for (Wager wager : wagers) {
            oddsSize = wager.getOdd().getOutcome().getOutcomeOdds().size()-1;
            wager.setOdd(wager.getOdd().getOutcome().getOutcomeOdds().get(oddsSize));
            if (wager.getPlayer() == player) {
                wager.setWin(r.nextBoolean());
                if (wager.isWin()) {
                    amount += wager.getAmount().multiply(wager.getOdd().getValue()).intValue();
                    wager.setProcessed(true);
                }
            }
        }
        
        player.setBalance(player.getBalance().add(BigDecimal.valueOf(amount)));
    }
    private void setMockData(){       
        SportEventBuilder seb = new SportEventBuilder();
        seb.setTitle("Arsenal vs Chelse");
        seb.setStartDate(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 00, 00));
        SportEvent seavc = seb.buildFootballSportEvent();
        sportEvents.add(seavc);
        
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
    }
}
