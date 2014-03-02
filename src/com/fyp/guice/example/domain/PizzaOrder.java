package com.fyp.guice.example.domain;

public class PizzaOrder{
	
	private int amount;
	
	public PizzaOrder(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}
	
	
}
