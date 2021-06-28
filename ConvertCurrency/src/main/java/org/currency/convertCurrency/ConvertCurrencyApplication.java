package org.currency.convertCurrency;

import org.currency.convertCurrency.request.ConversionRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ConvertCurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConvertCurrencyApplication.class, args);
	}
	
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}
	
	@Bean
	public CommandLineRunner createMarshaller() {
		
		return args ->{
			
			ConversionRequest cr = new ConversionRequest();
			System.out.println("--Serializing--");
			ObjectMapper om = new ObjectMapper();
			String s = om.writeValueAsString(cr);
			System.out.println(s);
			System.out.println("--Deserializing--");
			
		};
	}
}
