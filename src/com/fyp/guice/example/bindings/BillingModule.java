package com.fyp.guice.example.bindings;

import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.domain.interfaces.*;
import com.google.inject.*;
import com.google.inject.multibindings.Multibinder;

public class BillingModule extends AbstractModule{
	
	
	@Override
	protected void configure(){
		
				
		/*
	      * This tells Guice that whenever it sees a dependency on a TransactionLog,
	      * it should satisfy the dependency using a DatabaseTransactionLog.
	      */
	    bind(TransactionLog.class).to(DatabaseTransactionLog.class);

	     /*
	      * Similarly, this binding tells Guice that when CreditCardProcessor is used in
	      * a dependency, that should be satisfied with a PaypalCreditCardProcessor.
	      */
	    bind(CreditCardProcessor.class).to(PaypalCreditCardProcessor.class);
	
				
	}
	
}
