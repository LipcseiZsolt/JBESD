/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Lipcsei Zsolt
 */
public class Wager {
    private BigDecimal amount;
    private LocalDateTime timestampCreated;
    private Boolean isProcessed;
    private Boolean isWin;
    private Currency currency;
    private Player player;
    private OutcomeOdd odd;
    
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
    public boolean getIsProcessed() {
        return isProcessed;
    }

    /**
     * @param isProcessed
     */
    public void setIsProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    /**
     * @return the win
     */
    public boolean getIsWin() {
        return isWin;
    }

    /**
     * @param win the win to set
     */
    public void setIsWin(boolean win) {
        this.isWin = win;
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

    public Wager(BigDecimal amount, Currency currency, Player player, OutcomeOdd outcomeodd) {
        this.amount = amount;
        this.currency = currency;
        this.player = player;
        this.odd = outcomeodd;
        this.timestampCreated = LocalDateTime.now();
        this.isProcessed = Boolean.FALSE;
    }
    
    
}
