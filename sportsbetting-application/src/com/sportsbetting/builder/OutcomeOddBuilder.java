/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.builder;

import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

/**
 *
 * @author Lipcsei Zsolt
 */
public class OutcomeOddBuilder {
    private BigDecimal value;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private Outcome outcome;

    /**
     * @param value the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * @param validFrom the validFrom to set
     */
    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * @param validUntil the validUntil to set
     */
    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * @param outcome the outcome to set
     */
    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }
    
    public OutcomeOdd build(){
        return new OutcomeOdd(value, validFrom, validUntil, outcome);
    }
}
