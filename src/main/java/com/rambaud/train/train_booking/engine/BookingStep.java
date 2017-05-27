package com.rambaud.train.train_booking.engine;

import com.rambaud.train.train_booking.exception.FunctionalException;
import com.rambaud.train.train_booking.exception.TechnicalException;

public interface BookingStep {
	
	void run() throws TechnicalException, FunctionalException;
	
	void validate() throws TechnicalException, FunctionalException;

}
