package com.fyp.guice.example.application;

import com.fyp.guice.example.bindings.*;
import com.fyp.guice.example.domain.*;
import com.google.inject.*;

public class Start{
	
	public static void main(String[] args) {
	    /*
	     * Guice.createInjector() takes your Modules, and returns a new Injector
	     * instance. Most applications will call this method exactly once, in their
	     * main() method.
	     */
		Injector injector;
		if (args[0] != null) {
			injector = Guice.createInjector(new BillingModule(args[0]));
		}
		else{
			injector = Guice.createInjector(new BillingModule("com.fyp.guice.example.domain.PaypalCreditCardProcessor"));
		}

	    /*
	     * Now that we've got the injector, we can build objects.
	     */
	    
	    CardType cardType = CardType.Visa;
	    int cardValueType = -1;
	    
	    switch (cardType) {
		case Visa:
			cardValueType = 0;
			break;

		case MasterCard:
			cardValueType = 1;
			break;
			
		case Amex:
			cardValueType = 2;
			break;
			
		default:			
			cardValueType = 3;			
			break;
		}	    
	    	
	    
	    BillingService billingService = injector.getInstance(BillingService.class);
	    
	    PizzaOrder order = new PizzaOrder(100);
	    CreditCard creditCard = new CreditCard(cardValueType,"Juan David Castaneda", 11, 2010);

	    billingService.chargeOrder(order, creditCard);
	    
	  }
	
}
