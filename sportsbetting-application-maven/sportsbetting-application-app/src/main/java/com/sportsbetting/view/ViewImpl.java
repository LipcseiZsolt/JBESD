/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.view;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

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
public class ViewImpl implements View, MessageSourceAware {

	private Locale locale;
	private Scanner sc;
    private StringBuilder sr;
    private DateTimeFormatter formatter;
    
    private MessageSource messageSource;
    
    public ViewImpl() {
        this.sc = new Scanner(System.in);
        this.sr = new StringBuilder();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Override
    public Player readPlayerData() {
        PlayerBuilder pb = new PlayerBuilder();
        System.out.println(messageSource.getMessage("msg.name", null, locale));
        pb.setName(sc.nextLine());
        System.out.println(messageSource.getMessage("msg.money", null, locale));
        pb.setBalance(sc.nextBigDecimal());
        System.out.println(messageSource.getMessage("msg.currency", null, locale));
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
            System.out.println(messageSource.getMessage("msg.currencyException", null, locale));
        } while (true);

        pb.setCurrency(CurrencyConverter.StringToCurrency(readedCurrency));
        return pb.build();
    }

    @Override
    public void printWelcomeMessage(Player player) {
        System.out.println(messageSource.getMessage("msg.welcome", new Object[] {player.getName()}, locale));
    }

    @Override
    public void printBalance(Player player) {
        System.out.println(messageSource.getMessage("msg.balance", new Object[] {player.getBalance().toString(), player.getCurrency().toString()}, locale));
    }

    @Override
    public void printOutcomeOdds(List<SportEvent> sportEvents) {
        StringBuilder temp = new StringBuilder();
        System.out.println(messageSource.getMessage("play.doBet", null, locale));
        int betIdx = 0;
        List<OutcomeOdd> tempOutcomeOdds = new LinkedList<>();
        OutcomeOdd actualOutcomeOdd;
        for (SportEvent sportEvent : sportEvents) {
            temp.append(messageSource.getMessage("play.sportEvent", new Object[] { ++betIdx, sportEvent.getTitle(), sportEvent.getStartDate().format(formatter)}, locale));
            for (Bet bet : sportEvent.getBets()) {
                if (sportEvent.getBets().indexOf(bet) > 0) {
                    temp.append(messageSource.getMessage("play.sportEvent", new Object[] {++betIdx, sportEvent.getTitle(), sportEvent.getStartDate().format(formatter)}, locale));
                }
                temp.append(messageSource.getMessage("play.Bet", new Object[] {bet.getDescription()}, locale));
                for (Outcome outcome : bet.getOutcomes()) {
                    if (bet.getOutcomes().indexOf(outcome) > 0) {
                        temp.append(messageSource.getMessage("play.sportEvent", new Object[] { ++betIdx, sportEvent.getTitle(), sportEvent.getStartDate().format(formatter)}, locale));
                        temp.append(messageSource.getMessage("play.Bet", new Object[] {bet.getDescription()}, locale));
                    }
                    temp.append(messageSource.getMessage("play.printOutcome", new Object[] {outcome.getDescription()}, locale));
                    for(OutcomeOdd outcomeOdd : outcome.getOutcomeOdds()){
                        if (outcomeOdd.getValidFrom().isBefore(LocalDateTime.now()) && outcomeOdd.getValidUntil().isAfter(LocalDateTime.now()))
                            tempOutcomeOdds.add(outcomeOdd);
                    }
                    if (!tempOutcomeOdds.isEmpty()) {
                        actualOutcomeOdd = tempOutcomeOdds.stream().max((odd1, odd2) -> {
                             return odd1.getValue().compareTo(odd2.getValue());
                        }).get();
                        temp.append(messageSource.getMessage("play.printOdd", new Object[] { actualOutcomeOdd.getValue().toString(), actualOutcomeOdd.getValidFrom().format(formatter), actualOutcomeOdd.getValidUntil().format(formatter) }, locale));
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
        System.out.println(messageSource.getMessage("play.amount", null, locale));
        return sc.nextBigDecimal();

    }
    
    @Override
    public void printWagerSaved(Wager wager) {
        System.out.println(messageSource.getMessage("play.wagerSaved", new Object[] { wager.getOdd().getOutcome().getBet().getDescription(), wager.getOdd().getOutcome().getDescription(),
                wager.getOdd().getOutcome().getBet().getEvent().getTitle(), wager.getOdd().getValue().toString(), wager.getAmount().toString()}, locale));
    }

    @Override
    public void printNotEnoughBalance(Player player) {
        System.out.println(messageSource.getMessage("play.notEnoughMoney", new Object[] { player.getBalance().toString(), player.getCurrency()}, locale));
    }

    @Override
    public void printResults(Player player, List<Wager> wagers) {
        sr.append(messageSource.getMessage("play.result", null, locale));
        for (Wager wager : wagers) {
            sr.append(messageSource.getMessage("play.results", new Object[] { wager.getOdd().getOutcome().getBet().getDescription(), wager.getOdd().getOutcome().getDescription(),
                    wager.getOdd().getOutcome().getBet().getEvent().getTitle(), wager.getOdd().getValue().toString(), wager.getAmount().toString(), wager.isWin()}, locale));
        }
        sr.append(messageSource.getMessage("play.newBalance", new Object[] { player.getBalance().toString(), player.getCurrency()}, locale));

        System.out.println(sr.toString());
        sr.setLength(0);
    }

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
		
	}
	
    public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
