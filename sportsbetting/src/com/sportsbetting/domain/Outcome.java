/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.domain;

import java.util.List;

/**
 *
 * @author Lipcsei Zsolt
 */
public class Outcome {
    private String description;
    private List<OutcomeOdd> outcomeOdds;
    private Bet bet;
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the outcomeOdds
     */
    public List<OutcomeOdd> getOutcomeOdds() {
        return outcomeOdds;
    }

    /**
     * @param outcomeOdds the outcomeOdds to set
     */
    public void setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
        this.outcomeOdds = outcomeOdds;
    }

    /**
     * @return the bet
     */
    public Bet getBet() {
        return bet;
    }

    /**
     * @param bet the bet to set
     */
    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public Outcome(String description, List<OutcomeOdd> outcomeOdds, Bet bet) {
        this.description = description;
        this.outcomeOdds = outcomeOdds;
        this.bet = bet;
    }
    
    
}
