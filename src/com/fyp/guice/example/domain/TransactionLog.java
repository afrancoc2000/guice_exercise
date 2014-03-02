package com.fyp.guice.example.domain;

import com.fyp.guice.example.estructure.*;

public abstract class TransactionLog{

	public void logChargeResult(ChargeResult result){
		if(result.wasSuccessful()){
			System.out.print("Transacci�n aceptada, por un valor de: " + result.getTransValue());
		}
		else{
			System.out.print("Transacci�n denegada por el sistema");
		}
		
	}

	public void logConnectException(UnreachableException e){
		System.out.print("Transacci�n no realizada por errores de comunicaci�n");
	}
	
}
