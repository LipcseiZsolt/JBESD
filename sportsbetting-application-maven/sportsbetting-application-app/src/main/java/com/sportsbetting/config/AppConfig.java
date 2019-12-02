package com.sportsbetting.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;

import com.sportsbetting.App;
import com.sportsbetting.repository.BetRepository;
import com.sportsbetting.repository.OutcomeOddRepository;
import com.sportsbetting.repository.OutcomeRepository;
import com.sportsbetting.repository.ResultRepository;
import com.sportsbetting.repository.SportEventRepository;
import com.sportsbetting.repository.UserRepository;
import com.sportsbetting.repository.WagerRepository;
import com.sportsbetting.service.*;
import com.sportsbetting.view.*;

@Configuration
@Import({MessagesConfig.class, SpringConfigDataSource.class})
public class AppConfig {
	
	@Value("${default.langauge}")
	public Locale locale; 
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private SportEventRepository sportEventRepository;
	
	@Autowired
	private WagerRepository wagerRepository;
	
	@Autowired
	private BetRepository betRepository;
	
	@Autowired
	private OutcomeOddRepository outcomeOddRepository;
	
	@Autowired
	private OutcomeRepository outcomeRepository;
	
	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Bean
	public App app() {
		return new App(service(), view());
	}
	
	@Bean
	public SportsBettingService service() {
		SportsBettingServiceImpl service = new SportsBettingServiceImpl();
		service.setBetRepository(betRepository);
		service.setOutcomeOddRepository(outcomeOddRepository);
		service.setOutcomeRepository(outcomeRepository);
		service.setResultRepository(resultRepository);
		service.setSportEventRepository(sportEventRepository);
		service.setUserRepository(userRepository);
		service.setWagerRepository(wagerRepository);
		service.setMockData();
		return service;
	}
	
	@Bean
	public View view() {
		ViewImpl view = new ViewImpl();
		view.setMessageSource(messageSource);
		view.setLocale(locale);
		return view;
	}	
	
}