package com.fyp.guice.example.domain.interfaces;

import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.estructure.*;

public interface TransactionLog{

	void logChargeResult(ChargeResult result);

	void logConnectException(UnreachableException e);
	
}
