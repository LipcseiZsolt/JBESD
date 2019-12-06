/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Lipcsei Zsolt
 */
@Entity
public class OutcomeOdd {
	@Id
	@GeneratedValue
	private long id;
    private BigDecimal value;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    @ManyToOne(targetEntity=Outcome.class, fetch=FetchType.EAGER)
    private Outcome outcome;

    /**
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * @return the validFrom
     */
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * @param validFrom the validFrom to set
     */
    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * @return the validUntil
     */
    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    /**
     * @param validUntil the validUntil to set
     */
    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * @return the outcome
     */
    public Outcome getOutcome() {
        return outcome;
    }

    /**
     * @param outcome the outcome to set
     */
    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public OutcomeOdd(BigDecimal value, LocalDateTime validFrom, LocalDateTime validUntil, Outcome outcome) {
        this.value = value;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.outcome = outcome;
    }
    
    public OutcomeOdd() {}
    
}
