package com.fyp.guice.example.domain;

import com.google.inject.*;

public class BillingService{
	
	private final CreditCardProcessor processor;
	private final TransactionLog transactionLog;
    
    @Inject
    BillingService(CreditCardProcessor processor, 
    	TransactionLog transactionLog) {
    	this.processor = processor;
    	this.transactionLog = transactionLog;
    }
    
    
}
