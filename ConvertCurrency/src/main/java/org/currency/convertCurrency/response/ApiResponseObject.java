package org.currency.convertCurrency.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public abstract class ApiResponseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LocalDateTime timestamp;
	private String message;
	
	public ApiResponseObject() {
		this.timestamp = LocalDateTime.now();
	}

}
