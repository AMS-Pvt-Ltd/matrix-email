package com.matrix.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class matrixEmailApplication implements WebServerFactoryCustomizer<ConfigurableWebServerFactory>{

	public static void main(String[] args) {
		SpringApplication.run(matrixEmailApplication.class, args);
	}
	 @Override
	    public void customize(ConfigurableWebServerFactory factory) {
	        factory.setPort(8123); // Change the port here
	    }
}
