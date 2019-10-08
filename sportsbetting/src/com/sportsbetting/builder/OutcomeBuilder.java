/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.builder;

import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;
import java.util.List;

/**
 *
 * @author Lipcsei Zsolt
 */
public class OutcomeBuilder {
    private String description;
    private List<OutcomeOdd> outcomeOdds;
    private Bet bet;

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param outcomeOdds the outcomeOdds to set
     */
    public void setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
        this.outcomeOdds = outcomeOdds;
    }

    /**
     * @param bet the bet to set
     */
    public void setBet(Bet bet) {
        this.bet = bet;
    }
    
    public Outcome build(){
        return new Outcome(description, outcomeOdds, bet);
    }
}
