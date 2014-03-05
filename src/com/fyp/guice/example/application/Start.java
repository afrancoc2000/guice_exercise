package com.fyp.guice.example.application;

import com.fyp.guice.example.bindings.*;
import com.fyp.guice.example.domain.*;
import com.google.inject.*;

public class Start{
	
	public static void main(String[] args) {

		
		// Creacion de objeto injector generico
		Injector injector ; 		

		//Creacion de enumerado para determinar que tipo de injector utilizar
	    CardType cardType = CardType.Visa;
	    int cardValueType = -1;
	    
	    switch (cardType) {
		case Visa:
			cardValueType = 0;
			injector = Guice.createInjector(new BillingModule());
			break;

		case MasterCard:
			cardValueType = 1;
			injector = Guice.createInjector(new GoogleWalletModule());
			break;
			
		case Amex:
			cardValueType = 2;
			injector = Guice.createInjector(new BitCoinModule()); 
			break;
			
		default:			
			cardValueType = 3;	
			injector = Guice.createInjector(new DefaultModule());  
			break;
		}	    
	    	
	    
	    BillingService billingService = injector.getInstance(BillingService.class);
	    
	    PizzaOrder order = new PizzaOrder(100);
	    CreditCard creditCard = new CreditCard(cardValueType,"Juan David Castaneda", 11, 2010);

	    billingService.chargeOrder(order, creditCard);
	    
	  }
	
}
