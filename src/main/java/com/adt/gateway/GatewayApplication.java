package com.adt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class GatewayApplication {

	private final static Logger LOGGER = LoggerFactory.getLogger("GatewayApplication");

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
		LOGGER.info("Gateway-Services");
	}

}