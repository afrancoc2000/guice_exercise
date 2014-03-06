package com.fyp.guice.example.application;

import java.io.*;

import com.fyp.guice.example.bindings.*;
import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.domain.interfaces.*;
import com.google.inject.*;

public class Start{
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		// Leer tipo de tarjeta
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		int inCardType = 0;
		System.out.println("Ingrese su tipo de tarjeta: ");
		try {
			inCardType = Integer.parseInt(lectura.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    /*
	     * Guice.createInjector() takes your Modules, and returns a new Injector
	     * instance. Most applications will call this method exactly once, in their
	     * main() method.
	     */
		Injector injector;
		
		CardType cardType = null;
		if (inCardType == 0) cardType = CardType.Paypal;
		else if (inCardType == 1) cardType = CardType.GoogleWallet;
		else cardType = CardType.Falabella;

	    if (args.length > 0) {
			injector = Guice.createInjector(new BillingModule((Class<? extends CreditCardProcessor>)Class.forName(args[0])));
		}
		else{
    	    switch (cardType) {
    			case Paypal:
    				injector = Guice.createInjector(new BillingModule(PaypalCreditCardProcessor.class));
    				System.out.println("Tarjeta ingresada: Paypal");
    				break;
    
    			case GoogleWallet:
    				injector = Guice.createInjector(new BillingModule(GoogleWalletProcessor.class));
    				System.out.println("Tarjeta ingresada: GoogleWallet");
    				break;
    				
    			case Falabella:
    				injector = Guice.createInjector(new BillingModule(FalabellaCreditCardProcessor.class));
    				System.out.println("Tarjeta ingresada: Falabella");
    				break;
    				
    			default:			
    				injector = Guice.createInjector(new BillingModule(PaypalCreditCardProcessor.class));
    				System.out.println("Tarjeta no valida");
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
