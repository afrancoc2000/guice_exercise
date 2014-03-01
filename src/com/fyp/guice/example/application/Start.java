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
	    Injector injector = Guice.createInjector(new BillingModule());

	    /*
	     * Now that we've got the injector, we can build objects.
	     */
	    BillingService billingService = injector.getInstance(BillingService.class);
	    
	    System.out.println("Hola Mundo: " + billingService.toString());
	    
	  }
	
}
