package com.bridgelabz.fundoonotes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfigurartions {
	@Bean
	public BCryptPasswordEncoder getPasswordEncryption() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper getModelMapper()
	{
		return new ModelMapper();
	}
}
