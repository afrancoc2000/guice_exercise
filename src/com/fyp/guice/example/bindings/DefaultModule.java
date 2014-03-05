package com.fyp.guice.example.bindings;


import com.fyp.guice.example.domain.DatabaseTransactionLog;
import com.fyp.guice.example.domain.DefaultProcessor;
import com.fyp.guice.example.domain.interfaces.CreditCardProcessor;
import com.fyp.guice.example.domain.interfaces.TransactionLog;
import com.google.inject.AbstractModule;

public class DefaultModule extends AbstractModule {
	@Override
	protected void configure()
	{
	
	    bind(TransactionLog.class).to(DatabaseTransactionLog.class);

	 
	    bind(CreditCardProcessor.class).to(DefaultProcessor.class);
	}

}
