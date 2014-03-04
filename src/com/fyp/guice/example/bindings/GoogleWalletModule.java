package com.fyp.guice.example.bindings;

import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.domain.interfaces.*;
import com.google.inject.*;

public class GoogleWalletModule extends AbstractModule {

	@Override
	protected void configure()
	{
	
	    bind(TransactionLog.class).to(DatabaseTransactionLog.class);

	 
	    bind(CreditCardProcessor.class).to(GoogleWalletProcessor.class);
	}
	
}
