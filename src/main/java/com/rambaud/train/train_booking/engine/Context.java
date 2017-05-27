package com.rambaud.train.train_booking.engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Context {
	
	private static Context instance = null;
	
	private WebDriver driver;

	private Context() {
		driver = new ChromeDriver();
	}
	
	public static Context getInstance() {
		if (instance == null) {
			instance = new Context();
		}
		return instance;
	}

	public WebDriver getDriver() {
		return driver;
	}
}
