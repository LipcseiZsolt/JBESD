/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting;

import com.sportsbetting.builder.WagerBuilder;
import com.sportsbetting.config.AppConfig;

import java.io.IOException;

import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Wager;
import java.math.BigDecimal;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sportsbetting.service.SportsBettingService;
import com.sportsbetting.view.View;

/**
 *
 * @author Lipcsei Zsolt
 */
public class App {
    private final SportsBettingService service;
    private final View view;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
    	try (ConfigurableApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class)) {
            App app = appContext.getBean(App.class);
            app.Play();
            }

    }
    
    public App(SportsBettingService service, View view){
        this.service = service;
        this.view = view;
    }
    
    public void Play() throws IOException{
        createPlayer();
        doBetting();
        while(true){
            doBetting();
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
        printResults();
        System.in.read();
    }
    
    private void printResults(){
        view.printResults(service.findPlayer(), service.findAllWagers());
    }

}
