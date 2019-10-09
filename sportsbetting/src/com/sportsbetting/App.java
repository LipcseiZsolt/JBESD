/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting;

import com.sportsbetting.builder.BetBuilder;
import com.sportsbetting.builder.OutcomeBuilder;
import com.sportsbetting.builder.OutcomeOddBuilder;
import com.sportsbetting.factory.AppFactory;
import com.sportsbetting.builder.WagerBuilder;
import com.sportsbetting.service.ISportsBettingService;
import com.sportsbetting.service.SportsBettingService;
import com.sportsbetting.view.IView;
import com.sportsbetting.view.View;
import java.io.IOException;
import java.util.Scanner;
import com.sportsbetting.builder.SportEventBuilder;
import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.BetType;
import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;

/**
 *
 * @author Lipcsei Zsolt
 */
public class App {
    private final ISportsBettingService service;
    private final IView view;
    
    public App(ISportsBettingService service, IView view){
        this.service = service;
        this.view = view;
    }
    
    public void Play() throws IOException{
        Scanner sc = new Scanner(System.in);
        
        this.createPlayer();
        this.doBetting();
        String in;
        while(true){
            this.doBetting();
        }
    }
    
    private void createPlayer(){
        service.savePlayer(view.readPlayerData());
        view.printWelcomeMessage(service.findPlayer());
    }
    
    private void doBetting() throws IOException{
        view.printOutcomeOdds(service.findAllSportEvents());
        WagerBuilder wb = new WagerBuilder();
        OutcomeOdd oo = view.selectOutcomeOdd(service.findAllSportEvents());
        if (oo == null) {
            calculateResults();
            System.exit(0);
        }
        wb.setOutcomeOdd(oo);
        BigDecimal wagerAmount;
        do{
            wagerAmount = view.readWagerAmount();
            if (wagerAmount.compareTo(service.findPlayer().getBalance()) == 1) {
                view.printNotEnoughBalance(service.findPlayer());
            }
            else
                break;
        }while(true);
        wb.setAmount(wagerAmount);
        wb.setCurrency(service.findPlayer().getCurrency());
        wb.setPlayer(service.findPlayer());
        Wager wager = wb.buildWager();
        service.saveWager(wager);
        view.printWagerSaved(wager);
        view.printBalance(service.findPlayer());
    }
    
    private void calculateResults() throws IOException{
        service.calculateResults();
        view.printResults(service.findPlayer(), service.findAllWagers());
        System.in.read();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        App app = AppFactory.getApp();
        app.Play();
    }
}
