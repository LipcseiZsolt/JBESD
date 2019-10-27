/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.builder;

import com.sportsbetting.domain.FootballSportEvent;
import com.sportsbetting.domain.TennisSportEvent;
import java.time.LocalDateTime;

/**
 *
 * @author Lipcsei Zsolt
 */
public class SportEventBuilder {
    private String title;
    private LocalDateTime startDate;
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    
    public TennisSportEvent buildTennisSportEvent(){
        return new TennisSportEvent(title, startDate);
    }
    
    public FootballSportEvent buildFootballSportEvent(){
        return new FootballSportEvent(title, startDate);
    }
}
