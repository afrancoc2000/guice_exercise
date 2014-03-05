package com.fyp.guice.example.bindings;

import com.fyp.guice.example.domain.BitcoinProcessor;
import com.fyp.guice.example.domain.DatabaseTransactionLog;
import com.fyp.guice.example.domain.interfaces.CreditCardProcessor;
import com.fyp.guice.example.domain.interfaces.TransactionLog;
import com.google.inject.AbstractModule;

public class BitCoinModule extends AbstractModule {

	
	@Override
	protected void configure()
	{
	
	    bind(TransactionLog.class).to(DatabaseTransactionLog.class);

	 
	    bind(CreditCardProcessor.class).to(BitcoinProcessor.class);
	}
}
