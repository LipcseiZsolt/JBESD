/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Lipcsei Zsolt
 */
@Entity
public class Result {
	@Id
	@GeneratedValue
	private long id;
	@OneToMany(targetEntity=Outcome.class, fetch=FetchType.EAGER)
    private List<Outcome> winnerOutcomes;

	public Result() {
		this.winnerOutcomes = new LinkedList<> ();
	}
	
    /**
     * @return the winnerOutcomes
     */
    public List<Outcome> getWinnerOutcomes() {
        return new ArrayList<>(winnerOutcomes);
    }

    /**
     * @param winnerOutcomes the winnerOutcomes to set
     */
    public void setWinnerOutcomes(List<Outcome> winnerOutcomes) {
        this.winnerOutcomes = winnerOutcomes;
    }
    
    
    
}
