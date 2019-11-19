/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.builder;

import java.math.BigDecimal;

import com.sportsbetting.domain.Currency;
import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.Wager;


/**
 *
 * @author Lipcsei Zsolt
 */
public class WagerBuilder {
    private Player player;
    private OutcomeOdd outcomeOdd;
    private BigDecimal amount;
    private Currency currency;
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    public void setOutcomeOdd(OutcomeOdd outcomeOdd){
        this.outcomeOdd = outcomeOdd;
    }
    
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
    
    public void setCurrency(Currency currency){
        this.currency = currency;
    }
    
    public Wager buildWager(){
        return new Wager(this.amount, this.currency, this.player, this.outcomeOdd);
    }
}
