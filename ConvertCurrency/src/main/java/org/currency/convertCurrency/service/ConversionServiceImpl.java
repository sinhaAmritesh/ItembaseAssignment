package org.currency.convertCurrency.service;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.currency.convertCurrency.exception.CurrencyNotFoundException;
import org.currency.convertCurrency.exception.ExchangeBlockedException;
import org.currency.convertCurrency.exception.InvalidExchangeUrlException;
import org.currency.convertCurrency.exception.ReadValueException;
import org.currency.convertCurrency.request.ConversionRequest;
import org.currency.convertCurrency.response.ConversionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class ConversionServiceImpl implements ConversionService {

	@Autowired
	WebClient.Builder webClientBuilder;

	@SuppressWarnings("unchecked")
	@Override
	public ConversionResponse convert(ConversionRequest request)
			throws ReadValueException, JsonMappingException, JsonParseException {
		String response = null;
		ConversionResponse conversionResponse = null;
		Map<String, Object> rates;
		Double exchangeRate = null;
		try {
			response = webClientBuilder.build().get()
					.uri("https://api.exchangerate-api.com/v4/latest/" + request.getFrom()).retrieve()
					.bodyToMono(String.class).block();
		} catch (WebClientResponseException ex) {
			throw new InvalidExchangeUrlException();
		} catch (Throwable t) {
			throw new ExchangeBlockedException();
		}
		try {
			rates = new ObjectMapper().readValue(response, Map.class);
			Map<String, Double> ratesFromMap = (Map<String, Double>) rates.get("rates");
			exchangeRate = ratesFromMap.get(request.getTo());
		} catch (JsonParseException e) {
			throw new JsonParseException("JSON Parse Exception", e.getLocation());
		} catch (JsonMappingException e) {
			throw new JsonMappingException("Exception while JSON mapping.");
		} catch (IOException e) {
			throw new ReadValueException();
		}

		try {
			conversionResponse = new ConversionResponse();
			conversionResponse.setAmount(request.getAmount());
			conversionResponse.setFrom(request.getFrom());
			conversionResponse.setTo(request.getTo());
			conversionResponse.setConverted(request.getAmount() * exchangeRate);
		} catch (NullPointerException ex) {
			throw new CurrencyNotFoundException(request.getTo());
		}

		return conversionResponse;
	}

}
