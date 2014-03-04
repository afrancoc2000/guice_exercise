package com.fyp.guice.example.domain;

import com.fyp.guice.example.domain.interfaces.*;
import com.fyp.guice.example.estructure.*;

public class FalabellaCreditCardProcessor implements CreditCardProcessor{
	private final double pizzaValue = 28000; 
	
	public ChargeResult charge(CreditCard creditCard, int orderAmount) throws UnreachableException{
		ChargeResult result = new ChargeResult();
		if(creditCard.getMaxDebt() > creditCard.getDebt() + pizzaValue * orderAmount){
			creditCard.addDebt(pizzaValue * orderAmount);
			result = new ChargeResult();
			result.setSuccessful(true);
			result.setTransValue(-pizzaValue * orderAmount);
		}
		else{
			result = new ChargeResult();
			result.setSuccessful(false);
			result.setTransValue(0);
		}
		return result;
	}
	
}
