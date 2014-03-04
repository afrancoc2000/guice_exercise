package com.fyp.guice.example.bindings;

import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.domain.interfaces.*;
import com.google.inject.*;

public class BillingModule extends AbstractModule{
	
	String processorName;
	
	public BillingModule(String processorName){
		super();
		this.processorName = processorName;
	}
	
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
	    try{
			bind(CreditCardProcessor.class).to((Class<? extends CreditCardProcessor>)Class.forName(processorName));
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}

		
	}
	
}
