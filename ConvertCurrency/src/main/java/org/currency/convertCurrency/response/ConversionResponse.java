package org.currency.convertCurrency.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class ConversionResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String from;
	private String to;
	private double amount;
	private double converted;
}
