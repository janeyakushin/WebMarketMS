package ru.yajaneya.webmarket.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("secret.properties")
public class WebMarketApp {

	public static void main(String[] args) {
		SpringApplication.run(WebMarketApp.class, args);
	}

}
