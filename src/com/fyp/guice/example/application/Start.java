package com.fyp.guice.example.application;

import com.fyp.guice.example.bindings.*;
import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.domain.interfaces.*;
import com.google.inject.*;

public class Start{
	
	public static void main(String[] args) throws ClassNotFoundException {
	    /*
	     * Guice.createInjector() takes your Modules, and returns a new Injector
	     * instance. Most applications will call this method exactly once, in their
	     * main() method.
	     */
		Injector injector;
	    CardType cardType = CardType.Falabella;

	    if (args[0] != null) {
			injector = Guice.createInjector(new BillingModule((Class<? extends CreditCardProcessor>)Class.forName(args[0])));
		}
		else{
    	    switch (cardType) {
    			case Paypal:
    				injector = Guice.createInjector(new BillingModule(PaypalCreditCardProcessor.class));
    				break;
    
    			case GoogleWallet:
    				injector = Guice.createInjector(new BillingModule(GoogleWalletProcessor.class));
    				break;
    				
    			case Falabella:
    				injector = Guice.createInjector(new BillingModule(FalabellaCreditCardProcessor.class));
    				break;
    				
    			default:			
    				injector = Guice.createInjector(new BillingModule(PaypalCreditCardProcessor.class));
    				break;
			}	    
		}
    		
        	injector = Guice.createInjector(new BillingModule(PaypalCreditCardProcessor.class));
    	

	    /*
	     * Now that we've got the injector, we can build objects.
	     */
	    
	    
	    BillingService billingService = injector.getInstance(BillingService.class);
	    
	    PizzaOrder order = new PizzaOrder(100);
	    CreditCard creditCard = new CreditCard(cardType.getValue(), "Juan David Castaneda", 11, 2010);

	    billingService.chargeOrder(order, creditCard);
	    
	  }
	
}
