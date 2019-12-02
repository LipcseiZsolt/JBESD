package com.sportsbetting.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfigDataSource {
	private static final String URL = "jdbc:mysql://localhost/sportsbetting_zsolt_lipcsei";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	@Bean
	public DataSource dataSource() {
		return new DriverManagerDataSource(URL,USER, PASSWORD);
	}
	
}
