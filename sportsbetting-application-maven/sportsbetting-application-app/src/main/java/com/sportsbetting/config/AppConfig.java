package com.sportsbetting.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;

import com.sportsbetting.App;
import com.sportsbetting.service.*;
import com.sportsbetting.view.*;

@Configuration
@Import({MessagesConfig.class, SpringDataSourceConfig.class})
public class AppConfig {
	
	@Value("${default.langauge}")
	public Locale locale; 
	
	@Autowired
	private MessageSource messageSource;
	
	
	@Bean
	public App app() {
		return new App(service(), view());
	}
	
	@Bean
	public SportsBettingService service() {
		return new SportsBettingServiceImpl();
	}
	
	@Bean
	public View view() {
		ViewImpl view = new ViewImpl();
		view.setMessageSource(messageSource);
		view.setLocale(locale);
		return view;
	}	
	
}