package com.sportsbetting.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@PropertySource(value = {"classpath:config.properties"})
public class MessagesConfig {
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messagesource = new ResourceBundleMessageSource();
		messagesource.setBasename("messages/messages");
		return messagesource;
	}
}
