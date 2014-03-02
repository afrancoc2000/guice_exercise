package com.fyp.guice.example.tests;

import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.estructure.*;
import com.google.inject.*;

public class RealBillingServiceTest{
	
	private final CreditCardProcessor processor;
	private final TransactionLog transactionLog;
    
    @Inject
    RealBillingServiceTest(CreditCardProcessor processor, 
    	TransactionLog transactionLog) {
    	this.processor = processor;
    	this.transactionLog = transactionLog;
    }
    
    public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
        try {
          ChargeResult result = processor.charge(creditCard, order.getAmount());
          transactionLog.logChargeResult(result);

          return result.wasSuccessful()
              ? Receipt.forSuccessfulCharge(order.getAmount())
              : Receipt.forDeclinedCharge(result.getDeclineMessage());
         } catch (UnreachableException e) {
          transactionLog.logConnectException(e);
          return Receipt.forSystemFailure(e.getMessage());
        }
      }
    
}
