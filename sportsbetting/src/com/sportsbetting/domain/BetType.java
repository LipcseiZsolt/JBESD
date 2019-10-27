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
public enum BetType {
    WINNER("WINNER"),
    GOALS("GOALS"),
    PLAYERS_SCORE("PLAYERSSCORE"),
    NUMBER_OF_SETS("NUMBEROFSETS");
    
    public final String type;
    
    private BetType(String type){
        this.type = type;
    }
}
