package org.currency.convertCurrency.exception;

public class CurrencyNotFoundException extends RuntimeException{
	
	public CurrencyNotFoundException(String currencyId) {
		super(String.format("Currency with CurrencyId %s not found",currencyId));
	}

}
