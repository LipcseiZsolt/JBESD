/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.domain;

/**
 *
 * @author Lipcsei Zsolt
 */
public enum Currency {
    HUF("HUF"),
    EUR("EUR"),
    USD("USD");
    
    public final String curr;
    
    private Currency(String curr){
        this.curr = curr;
    }
}
