package com.rambaud.train.train_booking.engine.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rambaud.train.train_booking.engine.BookingStep;
import com.rambaud.train.train_booking.exception.FunctionalException;
import com.rambaud.train.train_booking.exception.TechnicalException;
import com.rambaud.train.train_booking.model.Customer;
import com.rambaud.train.train_booking.model.TravelDetails;

public class EngineRoutineStep implements BookingStep {
	
	private static final Logger LOGGER = LogManager.getLogger(EngineRoutineStep.class);
	
	private BookingStep[] steps;
	
	public EngineRoutineStep(TravelDetails travel, Customer customer) {
		steps = new BookingStep[]{
			new TrainSearchLaunchStep(customer, travel),
        	new TrainFinderStep(travel),
        	new AccountLoggerStep(customer),
        	new SubmitOrderBookingStep(),
		};
	}

	public void run() throws TechnicalException, FunctionalException {
		for (BookingStep step: steps) {
			step.run();
			step.validate();
			LOGGER.info("Step {} ---> OK", step.getClass().getSimpleName());
		}
	}

	public void validate() throws TechnicalException, FunctionalException {
		// nothing to do
	}

}
