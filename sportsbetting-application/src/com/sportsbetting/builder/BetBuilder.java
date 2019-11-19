/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.builder;

import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.BetType;
import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.SportEvent;
import java.util.List;

/**
 *
 * @author Lipcsei Zsolt
 */
public class BetBuilder {
    private String description;
    private List<Outcome> outcomes;
    private BetType betType;
    private SportEvent sportEvent;

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param outcomes the outcomes to set
     */
    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    /**
     * @param betType the betType to set
     */
    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    /**
     * @param sportEvent the sportEvent to set
     */
    public void setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }
    
    public Bet build(){
        return new Bet(description, outcomes, betType, sportEvent);
    }
}
