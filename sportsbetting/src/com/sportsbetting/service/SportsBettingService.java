/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.service;

import java.util.List;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;

/**
 *
 * @author Lipcsei Zsolt
 */
public interface SportsBettingService {
    void savePlayer(Player newPlayer);
    Player findPlayer();
    List<SportEvent> findAllSportEvents();
    void saveWager(Wager wager);
    List<Wager> findAllWagers();
    void calculateResults();
}
