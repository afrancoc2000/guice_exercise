package com.fyp.guice.example.bindings;

import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.domain.interfaces.*;
import com.google.inject.*;

public class BillingModule extends AbstractModule{
	
	Class<? extends CreditCardProcessor> creditCardProcessorClass;
	
	public BillingModule(Class<? extends CreditCardProcessor> creditCardProcessorClass){
		super();
		this.creditCardProcessorClass = creditCardProcessorClass;
	}
	
	@Override
	protected void configure(){
		/*
	      * This tells Guice that whenever it sees a dependency on a TransactionLog,
	      * it should satisfy the dependency using a DatabaseTransactionLog.
	      */
	    bind(TransactionLog.class).to(DatabaseTransactionLog.class);

    	bind(CreditCardProcessor.class).to(creditCardProcessorClass);

		
	}
	
}
