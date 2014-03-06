package com.fyp.guice.example.domain;

public enum CardType {
    Paypal(0),
    GoogleWallet(1),
    Falabella(2);
    
    private int value;
    
    private CardType (int value){
    	this.setValue(value);
    }

	public int getValue(){
		return value;
	}

	private void setValue(int value){
		this.value = value;
	}
}
