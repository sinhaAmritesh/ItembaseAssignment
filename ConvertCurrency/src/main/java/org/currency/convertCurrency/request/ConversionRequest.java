package org.currency.convertCurrency.request;

import lombok.Data;

@Data
public class ConversionRequest {
	private String from;
	private String to;
	private double amount;
}
