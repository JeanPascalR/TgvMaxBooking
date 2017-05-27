package com.rambaud.train.train_booking.engine.steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.rambaud.train.train_booking.engine.AbstractBookingStep;
import com.rambaud.train.train_booking.engine.BookingStep;
import com.rambaud.train.train_booking.exception.FunctionalException;
import com.rambaud.train.train_booking.exception.TechnicalException;
import com.rambaud.train.train_booking.model.Customer;

public class AccountLoggerStep extends AbstractBookingStep implements BookingStep {
	
	private static final String SUBMIT_BASKET_BUTTON_ID = "action:CmdValidBasket";
	private static final String EMAIL_INPUT_ID          = "ccl-authentication-email";
	private static final String PASSWORD_INPUT_ID       = "ccl-authentication-password";
	private static final String SUBMIT_BUTTON_XPATH     = "//input[@type='submit']";
	private static final String SUBMIT_BUTTON_VALUE     = "Connectez-vous";
	
	private static final String ORDER_PAGE_URL          = "https://www.voyages-sncf.com/reservation/commande";
	
	private Customer customer;
	
	public AccountLoggerStep(Customer customer) {
		this.customer = customer;
	}
	
	public void run() throws TechnicalException {
		driver.findElement(By.id(SUBMIT_BASKET_BUTTON_ID)).click();
		waitOneSecond();
		driver.findElement(By.id(EMAIL_INPUT_ID)).sendKeys(customer.getEmail());
		waitOneSecond();
		driver.findElement(By.id(PASSWORD_INPUT_ID)).sendKeys(customer.getPassword());
		
		List<WebElement> submitInputs = driver.findElements(By.xpath(SUBMIT_BUTTON_XPATH));
		
		for (WebElement submitInput: submitInputs) {
			if (SUBMIT_BUTTON_VALUE.equals(submitInput.getAttribute("value"))) {
				submitInput.click();
				break;
			}
		}
	}
	
	public void validate() throws TechnicalException, FunctionalException {
		checkCurrentPage(ORDER_PAGE_URL);
	}

}
