package com.voting.sessions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.voting.sessions.client.CustomErrorDecode;

import feign.codec.ErrorDecoder;

@Configuration
public class ClientConfiguration {

	 @Bean
	    public ErrorDecoder errorDecoder() {
	        return new CustomErrorDecode();
	    }
}
