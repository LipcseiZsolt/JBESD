/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.builder;

import java.math.BigDecimal;
import com.sportsbetting.domain.Currency;
import com.sportsbetting.domain.Player;

/**
 *
 * @author Lipcsei Zsolt
 */
public class PlayerBuilder {
    private String name;
    private BigDecimal balance;
    private Currency currency;
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }
    
    public void setCurrency(Currency currency){
        this.currency = currency;
    }
    
    public Player build(){
        return new Player(this.name, this.balance, this.currency);
    }
}
