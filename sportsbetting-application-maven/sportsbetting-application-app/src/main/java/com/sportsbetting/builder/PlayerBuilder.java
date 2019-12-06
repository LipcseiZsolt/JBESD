/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    private String email;
    private String password;
    private LocalDate birth;
    
    public PlayerBuilder() {
    	this.email = "";
    	this.password = "";
    	this.birth = LocalDate.now();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }
    
    public void setCurrency(Currency currency){
        this.currency = currency;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public void setBirth(LocalDate birth) {
    	this.birth = birth;
    }
    
    public Player build(){
    	Player player = new Player();
    	player.setEmail(email);
    	player.setPassword(password);
    	player.setCurrency(this.currency);
    	player.setBalance(this.balance);
    	player.setName(this.name);
    	player.setBirth(this.birth);
        return player;
    }
}
