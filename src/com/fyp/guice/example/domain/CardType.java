package com.fyp.guice.example.domain;

public enum CardType {
Visa(0),
MasterCard(1),
Amex(2);

private int value;

private CardType (int value)
{
	this.value = value;
}

}
