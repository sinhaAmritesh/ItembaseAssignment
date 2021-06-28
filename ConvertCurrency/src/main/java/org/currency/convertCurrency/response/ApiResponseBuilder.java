package org.currency.convertCurrency.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseBuilder {
	
	public static ResponseEntity<ApiResponseObject> createEntity(Object data, HttpHeaders httpHeaders, HttpStatus httpStatus){
		
		return new ResponseEntity<ApiResponseObject>(new ApiEntity<>(data), httpHeaders, httpStatus);
	}
	
	public static ResponseEntity<ApiResponseObject> createEntity(String message, Object data, HttpHeaders httpHeaders, HttpStatus httpStatus){
		
		return new ResponseEntity<ApiResponseObject>(new ApiEntity<>(message,data), httpHeaders, httpStatus);
	}

}
