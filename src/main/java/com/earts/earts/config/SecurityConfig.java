package com.earts.earts.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {
    
    @Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.cors((c) -> {
			CorsConfigurationSource cors = (request) -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedHeaders(List.of("*"));
				config.setAllowedMethods(List.of("*"));
				config.setAllowedOrigins(List.of("*"));
				return config;
			};
			c.configurationSource(cors);
		})
		.addFilterBefore(filter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeHttpRequests()
		.mvcMatchers("/admin/login", "/admin/create")
		.permitAll()
		.mvcMatchers("/superadmin/login", "/superadmin/signup")
		.permitAll()
		.mvcMatchers("/artist/login", "/artist/create")
		.permitAll()
		.mvcMatchers("/user/login", "/user/create")
		.permitAll()
		.mvcMatchers("/artist/**")
		.hasRole("ARTIST")
		.mvcMatchers("/admin/**")
		.hasRole("ADMIN")
		.mvcMatchers("/superadmin/**")
		.hasRole("SUPERADMIN")
		.mvcMatchers("/user/**")
		.hasRole("MEMBER")
		.and()
		.formLogin().disable()
		.httpBasic().disable();
		return http.build();
	}

	public AuthenticationFilter filter(){
		return null;
	}
}
