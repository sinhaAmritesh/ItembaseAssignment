package org.currency.convertCurrency.controller;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.currency.convertCurrency.exception.ReadValueException;
import org.currency.convertCurrency.request.ConversionRequest;
import org.currency.convertCurrency.response.ApiResponseBuilder;
import org.currency.convertCurrency.response.ApiResponseObject;
import org.currency.convertCurrency.response.ConversionResponse;
import org.currency.convertCurrency.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
	
	@Autowired
	ConversionService conversionService;
	
	@PostMapping("/convert")
	public ResponseEntity<ApiResponseObject> convertAndSend(@RequestBody ConversionRequest request) throws JsonMappingException, JsonParseException, ReadValueException{
		ConversionResponse response =	conversionService.convert(request);
		return ApiResponseBuilder.createEntity("Currency Exchanged Successfully.",response, HttpHeaders.EMPTY, HttpStatus.OK);
	}

}
