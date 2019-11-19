/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.view;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import com.sportsbetting.builder.PlayerBuilder;
import com.sportsbetting.converter.CurrencyConverter;
import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.Currency;
import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Lipcsei Zsolt
 */
public class ViewImpl implements View {

    Scanner sc;
    StringBuilder sr;
    DateTimeFormatter formatter;

    public ViewImpl() {
        this.sc = new Scanner(System.in);
        this.sr = new StringBuilder();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Override
    public Player readPlayerData() {
        PlayerBuilder pb = new PlayerBuilder();
        System.out.println("What is your name?");
        pb.setName(sc.nextLine());
        System.out.println("How much money do you have?");
        pb.setBalance(sc.nextBigDecimal());
        System.out.println("What is your currency? (HUF, EUR or USD)");
        String readedCurrency;
        boolean rightCurrency = false;
        do {
            readedCurrency = sc.next().toUpperCase();
            for (Currency curr : Currency.values()) {
                String actualCurrency = CurrencyConverter.CurrencyToString(curr);
                if (actualCurrency.equals(readedCurrency)) {
                    rightCurrency = true;
                }
            }
            if (rightCurrency) {
                break;
            }
            System.out.println("Not supported currency.. \nWhat is your currency? (HUF, EUR or USD)");
        } while (true);

        pb.setCurrency(CurrencyConverter.StringToCurrency(readedCurrency));
        return pb.build();
    }

    @Override
    public void printWelcomeMessage(Player player) {
        System.out.println(String.format("Welcome %s!", player.getName()));
    }

    @Override
    public void printBalance(Player player) {
        System.out.println(String.format("Your balance is %s %s", player.getBalance().toString(), player.getCurrency().toString()));
    }

    @Override
    public void printOutcomeOdds(List<SportEvent> sportEvents) {
        StringBuilder temp = new StringBuilder();
        System.out.println("What are you want to bet on? (choose a number or press q for quit)");
        int betIdx = 0;
        List<OutcomeOdd> tempOutcomeOdds = new LinkedList<>();
        OutcomeOdd actualOutcomeOdd;
        for (SportEvent sportEvent : sportEvents) {
            temp.append(String.format("%d: Sport event: %s, (start: %s), ", ++betIdx, sportEvent.getTitle(), sportEvent.getStartDate().format(formatter)));
            for (Bet bet : sportEvent.getBets()) {
                if (sportEvent.getBets().indexOf(bet) > 0) {
                    temp.append(String.format("%d: Sport event: %s, (start: %s), ", ++betIdx, sportEvent.getTitle(), sportEvent.getStartDate().format(formatter)));
                }
                temp.append(String.format("Bet: %s, ", bet.getDescription()));
                for (Outcome outcome : bet.getOutcomes()) {
                    if (bet.getOutcomes().indexOf(outcome) > 0) {
                        temp.append(String.format("%d: Sport event: %s, (start: %s), ", ++betIdx, sportEvent.getTitle(), sportEvent.getStartDate().format(formatter)));
                        temp.append(String.format("Bet: %s, ", bet.getDescription()));
                    }
                    temp.append(String.format("Outcome: %s, ", outcome.getDescription()));
                    for(OutcomeOdd outcomeOdd : outcome.getOutcomeOdds()){
                        if (outcomeOdd.getValidFrom().isBefore(LocalDateTime.now()) && outcomeOdd.getValidUntil().isAfter(LocalDateTime.now()))
                            tempOutcomeOdds.add(outcomeOdd);
                    }
                    if (!tempOutcomeOdds.isEmpty()) {
                        actualOutcomeOdd = tempOutcomeOdds.stream().max((odd1, odd2) -> {
                             return odd1.getValue().compareTo(odd2.getValue());
                        }).get();
                        temp.append(String.format("Actual odd: %s, Valid between %s and %s.\n", actualOutcomeOdd.getValue().toString(), actualOutcomeOdd.getValidFrom().format(formatter), actualOutcomeOdd.getValidUntil().format(formatter)));
                        sr.append(temp.toString());
                    }
                    tempOutcomeOdds.clear();
                    temp.setLength(0);
                }
            }
        }

        System.out.println(sr.toString().trim());
        sr.setLength(0);
    }

    @Override
    public OutcomeOdd selectOutcomeOdd(List<SportEvent> sportEvents) {
        String in = sc.next();
        if (in.equals("q")) {
            return null;
        }

        List<OutcomeOdd> oo = new LinkedList<>();
        List<OutcomeOdd> tempOutcomeOdds = new LinkedList<>();
        
        for (SportEvent sportEvent : sportEvents) {
            for (Bet bet : sportEvent.getBets()) {
                for (Outcome outcome : bet.getOutcomes()) {
                    for (OutcomeOdd outcomeOdd : outcome.getOutcomeOdds()) {
                        if (outcomeOdd.getValidFrom().isBefore(LocalDateTime.now()) && outcomeOdd.getValidUntil().isAfter(LocalDateTime.now())) {
                            tempOutcomeOdds.add(outcomeOdd);
                        }
                    }
                    if (!tempOutcomeOdds.isEmpty()) {
                        oo.add(tempOutcomeOdds.stream().max((odd1, odd2) -> {
                            return odd1.getValue().compareTo(odd2.getValue());
                        }).get());
                    }
                    tempOutcomeOdds.clear();
                }
            }
        }

        return oo.get(Integer.parseInt(in) - 1);
    }

    @Override
    public BigDecimal readWagerAmount() {
        System.out.println("What amount do you wish to bet on it?");
        return sc.nextBigDecimal();

    }

    @Override
    public void printWagerSaved(Wager wager) {
        System.out.println(String.format("Wager '%s=%s' of %s [odd: %s, amount: %s] saved!", wager.getOdd().getOutcome().getBet().getDescription(), wager.getOdd().getOutcome().getDescription(),
                wager.getOdd().getOutcome().getBet().getEvent().getTitle(), wager.getOdd().getValue().toString(), wager.getAmount().toString()));
    }

    @Override
    public void printNotEnoughBalance(Player player) {
        System.out.println(String.format("You don't have enough money, your balance is %s %s", player.getBalance().toString(), player.getCurrency()));
    }

    @Override
    public void printResults(Player player, List<Wager> wagers) {
        sr.append("Results:\n");
        for (Wager wager : wagers) {
            sr.append(String.format("Wager '%s=%s' of %s [odd: %s, amount: %s], win: %s\n", wager.getOdd().getOutcome().getBet().getDescription(), wager.getOdd().getOutcome().getDescription(),
                    wager.getOdd().getOutcome().getBet().getEvent().getTitle(), wager.getOdd().getValue().toString(), wager.getAmount().toString(), wager.isWin()));
        }
        sr.append(String.format("Your new balance is %s %s.", player.getBalance().toString(), player.getCurrency()));

        System.out.println(sr.toString());
        sr.setLength(0);
    }

}
