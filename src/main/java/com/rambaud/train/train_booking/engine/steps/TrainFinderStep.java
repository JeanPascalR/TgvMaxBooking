package com.rambaud.train.train_booking.engine.steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.rambaud.train.train_booking.engine.AbstractBookingStep;
import com.rambaud.train.train_booking.engine.BookingStep;
import com.rambaud.train.train_booking.exception.FunctionalException;
import com.rambaud.train.train_booking.exception.TechnicalException;
import com.rambaud.train.train_booking.model.TravelDetails;

public class TrainFinderStep extends AbstractBookingStep implements BookingStep {
	
	private static final String BOOKING_BASKET_URL      = "https://www.voyages-sncf.com/reservation/panier";
	private static final String MAIN_ROW_XPATH          = "//div[@class='main-row-wrapper']";
	private static final String TIME_XPATH              = ".//time[@class='departure-time']";
	private static final String PRICE_BUTTON_XPATH      = ".//button[@class='price-btn']";
	private static final String PRICE_BUTTONS_DIV_XPATH = ".//div[@class='price-buttons']";

	private TravelDetails travel;

	public TrainFinderStep(TravelDetails travel) {
		this.travel = travel;
	}

	public void run() throws FunctionalException, TechnicalException {
		WebElement mainRow = findSuitableTrainRow();
		waitOneSecond();
		findConfirmButtonById(mainRow);
	}
	
	public void validate() throws TechnicalException, FunctionalException {
		checkCurrentPage(BOOKING_BASKET_URL);
	}

	private WebElement findSuitableTrainRow() throws FunctionalException {
		WebElement result = null;
		List<WebElement> mainRows = driver.findElements(By.xpath(MAIN_ROW_XPATH));
		for (WebElement mainRow : mainRows) {
			WebElement time = mainRow.findElement(By.xpath(TIME_XPATH));
			String timeStr = time.getText();
			if (departureTimeIsValid(timeStr)) {
				WebElement tgvMaxButton = getTgvMaxButton(mainRow);
				if (tgvMaxButton != null) {
					tgvMaxButton.click();
					result = mainRow;
					break;
				}
			}
		}
		if (result == null) {
			throw new FunctionalException("Unable to find available train after " + travel.getHourOfDeparture() + ":"
					+ travel.getMinuteOfDeparture());
		}

		return result;
	}

	private boolean departureTimeIsValid(String departureTime) {
		String[] hourAndMinute = departureTime.split("h");
		Integer hour = Integer.valueOf(hourAndMinute[0]);
		Integer minute = Integer.valueOf(hourAndMinute[1]);
		return hour > travel.getHourOfDeparture()
				|| (hour == travel.getHourOfDeparture() && minute >= travel.getMinuteOfDeparture());
	}

	private WebElement getTgvMaxButton(WebElement mainRow) {
		List<WebElement> buttons = mainRow.findElements(By.xpath(PRICE_BUTTON_XPATH));
		WebElement buttonPrice = null;
		for (WebElement button : buttons) {
			if (button.getText().contains("max")) {
				buttonPrice = button;
				break;
			}
		}
		return buttonPrice;
	}

	private void findConfirmButtonById(WebElement mainRow) {
		WebElement divButtonsWrapper = mainRow.findElement(By.xpath(PRICE_BUTTONS_DIV_XPATH));
		String idStr = divButtonsWrapper.getAttribute("id");
		String id;
		if (idStr.charAt(10) == '-') {
			id = idStr.substring(9, 10);
		} else {
			id = idStr.substring(9, 11);
		}
		driver.findElement(By.id("proposal-" + id + "-select-button")).click();
	}

}
