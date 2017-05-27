package com.rambaud.train.train_booking.model;

public class TravelDetails {
	
	private String fromCityName;
	private String destinationCityName;
	
	private String departureDate;
	
	private Integer hourOfDeparture;
	private Integer minuteOfDeparture;

	public TravelDetails(String fromCityName, String destinationCityName, String departureDate,
			Integer hourOfDeparture, Integer minuteOfDeparture) {
		super();
		this.fromCityName = fromCityName;
		this.destinationCityName = destinationCityName;
		this.departureDate = departureDate;
		this.hourOfDeparture = hourOfDeparture;
		this.setMinuteOfDeparture(minuteOfDeparture);
	}

	public String getFromCityName() {
		return fromCityName;
	}

	public void setFromCityName(String fromCityName) {
		this.fromCityName = fromCityName;
	}

	public String getDestinationCityName() {
		return destinationCityName;
	}

	public void setDestinationCityName(String destinationCityName) {
		this.destinationCityName = destinationCityName;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public Integer getHourOfDeparture() {
		return hourOfDeparture;
	}

	public void setHourOfDeparture(Integer hourOfDeparture) {
		this.hourOfDeparture = hourOfDeparture;
	}

	public Integer getMinuteOfDeparture() {
		return minuteOfDeparture;
	}

	public void setMinuteOfDeparture(Integer minuteOfDeparture) {
		this.minuteOfDeparture = minuteOfDeparture;
	}

}
