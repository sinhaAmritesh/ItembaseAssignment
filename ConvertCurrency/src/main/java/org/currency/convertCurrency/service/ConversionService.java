package org.currency.convertCurrency.service;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.currency.convertCurrency.exception.ReadValueException;
import org.currency.convertCurrency.request.ConversionRequest;
import org.currency.convertCurrency.response.ConversionResponse;

public interface ConversionService {
	
	ConversionResponse convert(ConversionRequest request)throws ReadValueException,JsonMappingException,JsonParseException;

}
