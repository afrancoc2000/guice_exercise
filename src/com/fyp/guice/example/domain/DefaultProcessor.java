package com.fyp.guice.example.domain;

import com.fyp.guice.example.domain.interfaces.CreditCardProcessor;

import com.fyp.guice.example.estructure.UnreachableException;

public class DefaultProcessor implements CreditCardProcessor  {
	
	@Override
	public ChargeResult charge(CreditCard creditCard, int orderAmount) throws UnreachableException{
		ChargeResult result = new ChargeResult();

		//Logica para implementar el charge para Default processor
		
		return result;
	
	}
}
