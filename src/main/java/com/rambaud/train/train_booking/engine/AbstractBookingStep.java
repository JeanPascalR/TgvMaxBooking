package com.rambaud.train.train_booking.engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rambaud.train.train_booking.exception.TechnicalException;

public abstract class AbstractBookingStep {
	
	private static final Logger LOGGER = LogManager.getLogger(AbstractBookingStep.class);
	
	protected WebDriver driver;
	
	public AbstractBookingStep() {
		driver = Context.getInstance().getDriver();
	}

	protected void checkCurrentPage(String expectedUrl) throws TechnicalException {
		boolean onExpectedPage = false;
		String url = null;
		for (int i = 0; i < 5; ++i) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				throw new TechnicalException("Expected page waiting has been interrupted", e);
			}
			url = driver.getCurrentUrl();
			if (url.startsWith(expectedUrl)) {
				LOGGER.info("On Expected page: {}", url);
				onExpectedPage = true;
				break;
			}
		}

		if (!onExpectedPage) {
			throw new TechnicalException("Unable to access the expected page after 25 seconds. Current URL: " + url);
		}
	}
	
	protected void waitOneSecond() throws TechnicalException {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new TechnicalException("Unable to wait one second", e);
		}
	}
	
}
