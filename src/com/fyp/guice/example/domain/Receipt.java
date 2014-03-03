package com.fyp.guice.example.domain;

public class Receipt{

	private String message;
	
	public Receipt(String message){
		this.setMessage(message);
	}
	
	public static Receipt forSuccessfulCharge(int amount){
		return new Receipt("Recibo: transacción aprobada para " + amount + " pizzas");
	}

	public static Receipt forDeclinedCharge(String declineMessage){
		return new Receipt("Recibo: transacción rechazada por " + declineMessage);
	}

	public static Receipt forSystemFailure(String message2){
		return new Receipt("Recibo: la transacción no se realizó por problemas en el sistema");
	}

	public String getMessage(){
		return message;
	}

	private void setMessage(String message){
		this.message = message;
	}
	
}
