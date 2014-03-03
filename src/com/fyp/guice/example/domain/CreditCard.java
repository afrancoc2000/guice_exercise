package com.fyp.guice.example.domain;

import com.fyp.guice.example.estructure.*;

public class CreditCard{
	
	private String nameInCard;
	private int validThroughMonth; 
	private int validThroughYear; 
	private double debt;
	private double maxDebt;
	
	public CreditCard(String nameInCard, int validThroughMonth, int validThroughYear){
		this.nameInCard = nameInCard;
		this.validThroughMonth = validThroughMonth;
		this.validThroughYear = validThroughYear;
		this.setMaxDebt(2000000);
	}

	public String getNameInCard(){
		return nameInCard;
	}

	public void setNameInCard(String nameInCard){
		this.nameInCard = nameInCard;
	}
	
	public int getValidThroughMonth(){
		return validThroughMonth;
	}

	public void setValidThroughMonth(int validThroughMonth){
		this.validThroughMonth = validThroughMonth;
	}

	public int getValidThroughYear(){
		return validThroughYear;
	}

	public void setValidThroughYear(int validThroughYear){
		this.validThroughYear = validThroughYear;
	}

	public double getDebt(){
		return debt;
	}

	public void addDebt(double debt)throws UnreachableException{
		this.debt += debt;
	}

	public double getMaxDebt(){
		return maxDebt;
	}

	public void setMaxDebt(double maxDebt){
		this.maxDebt = maxDebt;
	}

}
