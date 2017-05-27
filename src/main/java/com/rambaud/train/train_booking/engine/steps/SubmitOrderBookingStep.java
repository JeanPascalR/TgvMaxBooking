package com.rambaud.train.train_booking.engine.steps;

import org.openqa.selenium.By;

import com.rambaud.train.train_booking.engine.AbstractBookingStep;
import com.rambaud.train.train_booking.engine.BookingStep;
import com.rambaud.train.train_booking.exception.FunctionalException;
import com.rambaud.train.train_booking.exception.TechnicalException;

public class SubmitOrderBookingStep extends AbstractBookingStep implements BookingStep {

	private static final String CONFIRMATION_URL = "https://www.voyages-sncf.com/reservation/confirmation";
	
	private static final String CGV_CHECKBOX_ID = "CGV_CHECKBOX";
	private static final String SUBMIT_ORDER_ID = "orderSubmitButton";
	
	public void run() {
		driver.findElement(By.id(CGV_CHECKBOX_ID)).click();
		driver.findElement(By.id(SUBMIT_ORDER_ID)).click();
	}
	
	public void validate() throws TechnicalException, FunctionalException {
		checkCurrentPage(CONFIRMATION_URL);
	}
	
}
