package com.fyp.guice.example.domain;

import com.fyp.guice.example.estructure.*;

public abstract class TransactionLog{

	public void logChargeResult(ChargeResult result){
		if(result.wasSuccessful()){
			System.out.print("Transacción aceptada, por un valor de: " + result.getTransValue());
		}
		else{
			System.out.print("Transacción denegada por el sistema");
		}
		
	}

	public void logConnectException(UnreachableException e){
		System.out.print("Transacción no realizada por errores de comunicación");
	}
	
}
