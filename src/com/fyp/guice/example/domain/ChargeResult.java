package com.fyp.guice.example.domain;

public class ChargeResult{
	private double transValue;
	private boolean successful;
	
	public ChargeResult(){
	}

	public boolean wasSuccessful(){
		return successful;
	}

	public void setSuccessful(boolean accepted){
		this.successful = accepted;
	}

	public double getTransValue(){
		return transValue;
	}

	public void setTransValue(double transValue){
		this.transValue = transValue;
	}

	public String getDeclineMessage(){
		return "Cupo máximo superado";
	}

	
}
