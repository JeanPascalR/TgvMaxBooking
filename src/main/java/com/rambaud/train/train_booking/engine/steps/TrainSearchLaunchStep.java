package com.rambaud.train.train_booking.engine.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.rambaud.train.train_booking.engine.AbstractBookingStep;
import com.rambaud.train.train_booking.engine.BookingStep;
import com.rambaud.train.train_booking.exception.FunctionalException;
import com.rambaud.train.train_booking.exception.TechnicalException;
import com.rambaud.train.train_booking.model.Customer;
import com.rambaud.train.train_booking.model.TravelDetails;

public class TrainSearchLaunchStep extends AbstractBookingStep implements BookingStep {

	private static final String BOOKING_URL                = "https://www.voyages-sncf.com/bons-plans/tgvmax";

	private static final String FROM_CITY_BUTTON_ID        = "select2-chosen-1";
	private static final String DESTINATION_CITY_BUTTON_ID = "s2id_vsb-destination-promo";
	private static final String FROM_CITY_INPUT_ID         = "s2id_autogen1_search";
	private static final String DESTINATION_CITY_INPUT_ID  = "s2id_autogen2_search";
	private static final String DATE_INPUT_ID              = "vsb-departure-date-promo";
	private static final String HOUR_SELECT_ID             = "vsb-departure-time-promo";
	private static final String CARD_NUMBER_INPUT_ID       = "PASSENGER_1_CARD-NUMBER-promo";
	private static final String BIRTHDAY_INPUT_ID          = "PASSENGER_1_BIRTHDAY-promo";
	private static final String SUBMIT_BUTTON_XPATH        = "//button[@type='submit']";

	private static final String PROPOSITION_PAGE_URL       = "https://www.voyages-sncf.com/proposition";

	private Customer customer;
	private TravelDetails travel;

	public TrainSearchLaunchStep(Customer customer, TravelDetails travel) {
		this.customer = customer;
		this.travel = travel;
	}

	public void run() throws TechnicalException {
		driver.get(BOOKING_URL);

		fillCityName(FROM_CITY_BUTTON_ID, FROM_CITY_INPUT_ID, travel.getFromCityName());
		fillCityName(DESTINATION_CITY_BUTTON_ID, DESTINATION_CITY_INPUT_ID, travel.getDestinationCityName());

		fillingDateOfDeparture();

		fillingCustomerInfos();

		driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH)).click();
	}
	
	public void validate() throws TechnicalException, FunctionalException {
		checkCurrentPage(PROPOSITION_PAGE_URL);
	}

	private void fillCityName(String cityButtonId, String cityInputId, String cityName) {
		driver.findElement(By.id(cityButtonId)).click();
		driver.findElement(By.id(cityInputId)).sendKeys(cityName + Keys.ENTER);
	}

	private void fillingDateOfDeparture() {
		driver.findElement(By.id(DATE_INPUT_ID)).sendKeys(Keys.ARROW_LEFT + travel.getDepartureDate());

		WebElement selectElement = driver.findElement(By.id(HOUR_SELECT_ID));
		Select select = new Select(selectElement);
		Integer valueToSelect = travel.getHourOfDeparture() - 1;
		select.selectByValue(valueToSelect.toString());
	}
	
	private void fillingCustomerInfos() {
		driver.findElement(By.id(CARD_NUMBER_INPUT_ID)).sendKeys(customer.getCardNumber());

		driver.findElement(By.id(BIRTHDAY_INPUT_ID))
				.sendKeys(Keys.ARROW_LEFT + "" + Keys.ARROW_LEFT + "" + Keys.ARROW_LEFT + "" + Keys.ARROW_LEFT + ""
						+ Keys.ARROW_LEFT + "" + Keys.ARROW_LEFT + "" + Keys.ARROW_LEFT + "" + Keys.ARROW_LEFT + ""
						+ customer.getDateOfBirth());
	}

	

}
