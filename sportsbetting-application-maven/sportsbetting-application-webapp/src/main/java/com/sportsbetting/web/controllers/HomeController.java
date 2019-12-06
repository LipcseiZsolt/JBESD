package com.sportsbetting.web.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sportsbetting.domain.Currency;
import com.sportsbetting.domain.Player;
import com.sportsbetting.service.SportsBettingService;

@Controller
public class HomeController {
	
	private SportsBettingService service;
	
	MessageSource messageSource;
	
    @Autowired
    public void setService(SportsBettingService service) {
		this.service = service;
	}

    @Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHomePage(Locale locale) {
        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        model.addObject("player", service.findPlayer());
        model.addObject("wagers", service.findAllWagers());
        localizeHome(model, locale);

        locale = LocaleContextHolder.getLocale();
        model.addObject("locale", locale);
        return model;
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView setPlayer(Locale locale, String playername, String dateofbirth, String accountnumber, String currency, BigDecimal balance) {
        ModelAndView model = new ModelAndView();
        Player player = service.findPlayer();
        player.setName(playername);
        player.setBirth(LocalDate.of(Integer.parseInt(dateofbirth.split("-")[0]),Integer.parseInt(dateofbirth.split("-")[1]), Integer.parseInt(dateofbirth.split("-")[1])));
        player.setAccountNumber(Integer.parseInt(accountnumber));
        player.setCurrency(Currency.valueOf(currency));
        player.setBalance(balance);
        service.savePlayer(player);
        service.calculateResults();

        model.setViewName("home");
        model.addObject("player", service.findPlayer());
        model.addObject("wagers", service.findAllWagers());

        localizeHome(model, locale);

        locale = LocaleContextHolder.getLocale();
        model.addObject("locale", locale);
        return model;
    }
    
    private ModelAndView localizeHome(ModelAndView model, Locale locale) {
        model.addObject("sportsbetting", messageSource.getMessage("sportsbetting", null, locale));
        model.addObject("home", messageSource.getMessage("home", null, locale));
        model.addObject("events", messageSource.getMessage("events", null, locale));
        model.addObject("language", messageSource.getMessage("language", null, locale));
        model.addObject("hungarian", messageSource.getMessage("hungarian", null, locale));
        model.addObject("english", messageSource.getMessage("english", null, locale));
        model.addObject("logout", messageSource.getMessage("logout", null, locale));
        model.addObject("account_details", messageSource.getMessage("account_details", null, locale));
        model.addObject("name", messageSource.getMessage("name", null, locale));
        model.addObject("enter_name", messageSource.getMessage("enter_name", null, locale));
        model.addObject("date_of_birth", messageSource.getMessage("date_of_birth", null, locale));
        model.addObject("account_number", messageSource.getMessage("account_number", null, locale));
        model.addObject("currency", messageSource.getMessage("currency", null, locale));
        model.addObject("balance_web", messageSource.getMessage("balance_web", null, locale));
        model.addObject("save", messageSource.getMessage("save", null, locale));
        model.addObject("player_info", messageSource.getMessage("player_info", null, locale));
        model.addObject("wagers_web", messageSource.getMessage("wagers_web", null, locale));
        model.addObject("event_title", messageSource.getMessage("event_title", null, locale));
        model.addObject("event_type", messageSource.getMessage("event_type", null, locale));
        model.addObject("bet_type", messageSource.getMessage("bet_type", null, locale));
        model.addObject("outcome_value", messageSource.getMessage("outcome_value", null, locale));
        model.addObject("outcome_odd", messageSource.getMessage("outcome_odd", null, locale));
        model.addObject("wager_amount", messageSource.getMessage("wager_amount", null, locale));
        model.addObject("winner", messageSource.getMessage("winner", null, locale));
        model.addObject("processed", messageSource.getMessage("processed", null, locale));
        model.addObject("start_date", messageSource.getMessage("start_date", null, locale));
        model.addObject("end_date", messageSource.getMessage("end_date", null, locale));
        model.addObject("remove_button", messageSource.getMessage("remove_button", null, locale));
        model.addObject("email_address", messageSource.getMessage("email_address", null, locale));
        return model;

    }
}
