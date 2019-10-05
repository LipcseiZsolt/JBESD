/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Lipcsei Zsolt
 */
public abstract class SportEvent {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Result result;
    private List<Bet> bets;
    
    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * @return the bets
     */
    public List<Bet> getBets() {
        return bets;
    }

    /**
     * @param bets the bets to set
     */
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the startDate
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
