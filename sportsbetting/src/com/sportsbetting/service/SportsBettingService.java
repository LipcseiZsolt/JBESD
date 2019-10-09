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
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.BetType;
import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.Result;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Random;

/**
 *
 * @author Lipcsei Zsolt
 */
public class SportsBettingService implements ISportsBettingService{
    
    private Player player;
    private final List<Wager> wagers;
    private final List<SportEvent> sportEvents;

    public SportsBettingService(){
        this.wagers = new LinkedList<>();
        this.sportEvents = new LinkedList<>();
        
        SportEventBuilder seb = new SportEventBuilder();
        seb.setTitle("Arsenal vs Chelse");
        seb.setStartDate(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 00, 00));
        SportEvent seavc = seb.buildFootballSportEvent();
        this.sportEvents.add(seavc);
        
        BetBuilder bb = new BetBuilder();
        bb.setBetType(BetType.PLAYERS_SCORE);
        bb.setDescription("Oliver Giroud score");
        bb.setSportEvent(seavc);
        bb.setOutcomes(new LinkedList<>());
        Bet seavcPlayerScoreBet = bb.build();
        seavc.getBets().add(seavcPlayerScoreBet);
        
        bb.setBetType(BetType.WINNER);
        bb.setDescription("winner");
        bb.setOutcomes(new LinkedList<>());
        Bet seavcWinnerBet = bb.build();
        seavc.getBets().add(seavcWinnerBet);
        
        OutcomeBuilder ob = new OutcomeBuilder();
        ob.setDescription("1");
        ob.setBet(seavcPlayerScoreBet);
        ob.setOutcomeOdds(new LinkedList<>());
        Outcome seavcPlayerScoreBetOutcomeOne = ob.build();
        seavcPlayerScoreBet.getOutcomes().add(seavcPlayerScoreBetOutcomeOne);
        
        ob.setDescription("3");
        ob.setBet(seavcPlayerScoreBet);
        ob.setOutcomeOdds(new LinkedList<>());
        Outcome seavcPlayerScoreBetOutcomeThree = ob.build();
        seavcPlayerScoreBet.getOutcomes().add(seavcPlayerScoreBetOutcomeThree);
        
        ob.setDescription("Arsenal");
        ob.setBet(seavcWinnerBet);
        ob.setOutcomeOdds(new LinkedList<>());
        Outcome seavcWinnerOutcomeArsenal = ob.build();
        seavcWinnerBet.getOutcomes().add(seavcWinnerOutcomeArsenal);
        
        ob.setDescription("Chelsea");
        ob.setBet(seavcWinnerBet);
        ob.setOutcomeOdds(new LinkedList<>());
        Outcome seavcWinnerOutcomeChelsea = ob.build();
        seavcWinnerBet.getOutcomes().add(seavcWinnerOutcomeChelsea);
        
        OutcomeOddBuilder oob = new OutcomeOddBuilder();
        oob.setOutcome(seavcPlayerScoreBetOutcomeOne);
        oob.setValue(new BigDecimal(1));
        oob.setValidFrom(LocalDateTime.of(2000, Month.JANUARY, 01, 12, 0, 0));
        oob.setValidUntil(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 0, 0));
        OutcomeOdd seavcPlayerScoreBetOutcomeOddOne_One = oob.build();
        seavcPlayerScoreBetOutcomeOne.getOutcomeOdds().add(seavcPlayerScoreBetOutcomeOddOne_One);
        
        oob.setOutcome(seavcPlayerScoreBetOutcomeThree);
        oob.setValue(new BigDecimal(3));
        oob.setValidFrom(LocalDateTime.of(2000, Month.JANUARY, 01, 12, 0, 0));
        oob.setValidUntil(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 0, 0));
        OutcomeOdd seavcPlayerScoreBetOutcomeOddThree_Three = oob.build();
        seavcPlayerScoreBetOutcomeThree.getOutcomeOdds().add(seavcPlayerScoreBetOutcomeOddThree_Three);
        
        oob.setOutcome(seavcWinnerOutcomeArsenal);
        oob.setValue(new BigDecimal(2));
        oob.setValidFrom(LocalDateTime.of(2000, Month.JANUARY, 01, 12, 0, 0));
        oob.setValidUntil(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 0, 0));
        OutcomeOdd seavcWinnerBetOutcomeOddArsenal_Two = oob.build();
        seavcWinnerOutcomeArsenal.getOutcomeOdds().add(seavcWinnerBetOutcomeOddArsenal_Two);
        
        oob.setOutcome(seavcWinnerOutcomeChelsea);
        oob.setValue(new BigDecimal(3));
        oob.setValidFrom(LocalDateTime.of(2000, Month.JANUARY, 01, 12, 0, 0));
        oob.setValidUntil(LocalDateTime.of(2020, Month.JANUARY, 01, 12, 0, 0));
        OutcomeOdd seavcWinnerBetOutcomeOddChelsea_Three = oob.build();
        seavcWinnerOutcomeChelsea.getOutcomeOdds().add(seavcWinnerBetOutcomeOddChelsea_Three);
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
        return this.sportEvents;
    }

    @Override
    public void saveWager(Wager wager) {
        Player wagerPlayer = wager.getPlayer();
        wagerPlayer.setBalance(wagerPlayer.getBalance().subtract(wager.getAmount()));
        wagers.add(wager);
    }

    @Override
    public List<Wager> findAllWagers() {
        return wagers;
    }

    @Override
    public void calculateResults() {
        Random r = new Random();
        int amount = 0; 
        int oddsSize;
        for (Wager wager : wagers) {
            oddsSize = wager.getOdd().getOutcome().getOutcomeOdds().size()-1;
            wager.setOdd(wager.getOdd().getOutcome().getOutcomeOdds().get(oddsSize));
            if (wager.getPlayer() == player)
                wager.setIsProcessed(r.nextBoolean());
                    if(wager.getIsProcessed()) {
                        wager.setIsWin(r.nextBoolean());
                        if(wager.getIsWin())
                            amount += wager.getAmount().multiply(wager.getOdd().getValue()).intValue();
            }
        }
        
        player.setBalance(player.getBalance().add(BigDecimal.valueOf(amount)));
    }
}
