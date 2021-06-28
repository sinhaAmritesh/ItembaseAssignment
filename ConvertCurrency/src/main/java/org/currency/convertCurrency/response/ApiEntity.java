package org.currency.convertCurrency.response;

import lombok.Data;

@Data
public class ApiEntity<T> extends ApiResponseObject{

	private static final long serialVersionUID = 1L;
	
	private T data;
	
	public ApiEntity(T data) {
		super();
		this.data = data;
	}
	
	public ApiEntity(String message, T data) {
		super();
		setMessage(message);
		this.data = data;
	}
}
