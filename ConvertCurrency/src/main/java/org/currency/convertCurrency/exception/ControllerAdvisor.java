package org.currency.convertCurrency.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.currency.convertCurrency.response.ApiResponseBuilder;
import org.currency.convertCurrency.response.ApiResponseObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CurrencyNotFoundException.class)
	public ResponseEntity<ApiResponseObject> handleCurrencyNotfoundException(
			CurrencyNotFoundException ex, WebRequest request){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("Message", "Currency not found.");
		body.put("Resolution", "Please use a valid currency ID and Try Again!");
		return ApiResponseBuilder.createEntity("Exception Handling.",body,HttpHeaders.EMPTY,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ReadValueException.class)
	public ResponseEntity<ApiResponseObject> handleReadValueException(
			ReadValueException ex, WebRequest request){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("Message", "Unable to read data from the response.");
		body.put("Resolution", "Try Again!");
		return ApiResponseBuilder.createEntity("Exception Handling.",body,HttpHeaders.EMPTY,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidExchangeUrlException.class)
	public ResponseEntity<ApiResponseObject> handleInvalidUrlException(
			InvalidExchangeUrlException ex, WebRequest request){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("Message", "URL formed is incorrect.");
		body.put("Resolution", "Please Try Again! with a valid currency ID.");
		return ApiResponseBuilder.createEntity("Exception Handling.",body,HttpHeaders.EMPTY,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ExchangeBlockedException.class)
	public ResponseEntity<ApiResponseObject> handleInvalidUrlException(
			ExchangeBlockedException ex, WebRequest request){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("Message", "Exchange did not respond to the request.");
		body.put("Resolution", "Please Try Again! after sometime.");
		return ApiResponseBuilder.createEntity("Exception Handling.",body,HttpHeaders.EMPTY,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<ApiResponseObject> handleInvalidUrlException(
			JsonParseException ex, WebRequest request){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("Message", "Exception while parsing the JSON.");
		body.put("Resolution", "Please Try Again!");
		return ApiResponseBuilder.createEntity("Exception Handling.",body,HttpHeaders.EMPTY,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ApiResponseObject> handleInvalidUrlException(
			JsonMappingException ex, WebRequest request){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("Message", "Exception while mapping the JSON.");
		body.put("Resolution", "Please Try Again!");
		return ApiResponseBuilder.createEntity("Exception Handling.",body,HttpHeaders.EMPTY,HttpStatus.NOT_FOUND);
	}
	
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, 
        HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
