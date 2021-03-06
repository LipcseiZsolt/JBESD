/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Lipcsei Zsolt
 */
@Entity
public class Wager {
	@Id
	@GeneratedValue
	private long id;
    private BigDecimal amount;
    private final LocalDateTime timestampCreated;
    private boolean processed;
    private boolean win;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @OneToOne
    private Player player;
    @OneToOne
    private OutcomeOdd odd;
    
    public Wager(BigDecimal amount, Currency currency, Player player, OutcomeOdd outcomeodd) {
        this.amount = amount;
        this.currency = currency;
        this.player = player;
        this.odd = outcomeodd;
        this.timestampCreated = LocalDateTime.now();
        this.processed = Boolean.FALSE;
    }
        
    public Wager() {
    	this.timestampCreated = LocalDateTime.now();
    }
    
    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the timestampCreated
     */
    public LocalDateTime getTimestampCreated() {
        return timestampCreated;
    }

    /**
     * @return the processed
     */
    public boolean isProcessed() {
        return processed;
    }

    /**
     * @param processed
     */
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    /**
     * @return the win
     */
    public boolean isWin() {
        return win;
    }

    /**
     * @param win the win to set
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * @return the currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the odd
     */
    public OutcomeOdd getOdd() {
        return odd;
    }

    /**
     * @param odd the odd to set
     */
    public void setOdd(OutcomeOdd odd) {
        this.odd = odd;
    }

}
