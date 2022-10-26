package com.earts.earts.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {
    
    @Value("${SUPABASE_URL}")
	private String url;
	
	@Value("${SUPABASE_PASSWORD}")
	private String password;
	
	@Bean
	public DataSource dataSource() {
		HikariDataSource hikari = new HikariDataSource();
		hikari.setConnectionTimeout(3000);
		hikari.setJdbcUrl(url);
		hikari.setUsername("postgres");
		hikari.setPassword(password);
		return hikari;
	}
}
