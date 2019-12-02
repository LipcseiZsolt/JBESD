/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;

/**
 *
 * @author Lipcsei Zsolt
 */
@Entity
public class TennisSportEvent extends SportEvent {
    
    public TennisSportEvent(String title, LocalDateTime startDate) {
        super(title, startDate);
    }
    
    public TennisSportEvent() {
    	super();
    }
}
