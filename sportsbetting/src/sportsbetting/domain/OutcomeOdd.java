/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Lipcsei Zsolt
 */
public class OutcomeOdd {
    private BigDecimal value;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private Wager wager;
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
     * @return the wager
     */
    public Wager getWager() {
        return wager;
    }

    /**
     * @param wager the wager to set
     */
    public void setWager(Wager wager) {
        this.wager = wager;
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
    
    
}
