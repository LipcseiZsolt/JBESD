/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsbetting.domain;

import java.util.List;

/**
 *
 * @author Lipcsei Zsolt
 */
public class Result {
    private List<Outcome> winnerOutcomes;

    /**
     * @return the winnerOutcomes
     */
    public List<Outcome> getWinnerOutcomes() {
        return winnerOutcomes;
    }

    /**
     * @param winnerOutcomes the winnerOutcomes to set
     */
    public void setWinnerOutcomes(List<Outcome> winnerOutcomes) {
        this.winnerOutcomes = winnerOutcomes;
    }
}
