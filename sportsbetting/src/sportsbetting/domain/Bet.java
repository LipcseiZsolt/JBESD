/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsbetting.domain;

import java.util.List;

/**
 *
 * @author Lipcsei Zsolt
 */
public class Bet {
    private String description;
    private List<Outcome> outcomes;
    private BetType bet;
    private SportEvent event;
    
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
     * @return the outcomes
     */
    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    /**
     * @param outcomes the outcomes to set
     */
    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    /**
     * @return the bet
     */
    public BetType getBet() {
        return bet;
    }

    /**
     * @param bet the bet to set
     */
    public void setBet(BetType bet) {
        this.bet = bet;
    }

    /**
     * @return the event
     */
    public SportEvent getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(SportEvent event) {
        this.event = event;
    }    
}
