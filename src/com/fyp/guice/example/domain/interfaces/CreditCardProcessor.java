package com.fyp.guice.example.domain.interfaces;

import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.estructure.*;

public interface CreditCardProcessor{
	public ChargeResult charge(CreditCard creditCard, int orderAmount) throws UnreachableException;
}
