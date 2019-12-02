/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Lipcsei Zsolt
 */
@Entity
public class Bet {
	@Id
	@GeneratedValue
	private long id;
    private String description;
    @OneToMany(targetEntity=Outcome.class, mappedBy="bet", fetch=FetchType.EAGER)
    private List<Outcome> outcomes;
    @Enumerated(EnumType.STRING)
    private BetType bet;
    @ManyToOne(targetEntity=SportEvent.class, fetch=FetchType.EAGER)
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
        return new ArrayList<>(outcomes);
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

    public Bet(String description, List<Outcome> outcomes, BetType bet, SportEvent event) {
        this.description = description;
        this.outcomes = outcomes;
        this.bet = bet;
        this.event = event;
    }
    
    public Bet() {}
    
}
