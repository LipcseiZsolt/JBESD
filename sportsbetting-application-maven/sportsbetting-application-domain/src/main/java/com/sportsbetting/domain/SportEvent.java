/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Lipcsei Zsolt
 */
@Entity
@Inheritance
public abstract class SportEvent {
	@Id
	@GeneratedValue
	private long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Result result;
    @OneToMany(targetEntity=Bet.class, mappedBy="event", fetch=FetchType.EAGER)
    private List<Bet> bets;
    
    public SportEvent(String title, LocalDateTime startDate) {
        this.title = title;
        this.startDate = startDate;
        this.bets = new LinkedList<>();
        this.result = new Result();
    }
    
    public SportEvent() {}
    
    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * @return the bets
     */
    public List<Bet> getBets() {
        return new ArrayList<>(bets);
    }

    /**
     * @param bets the bets to set
     */
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the startDate
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    
}
