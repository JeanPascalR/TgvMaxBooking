package com.rambaud.train.train_booking.exception;

public class FunctionalException extends Exception {

	private static final long serialVersionUID = -7139448214740113774L;

	public FunctionalException() {
		super();
	}

	public FunctionalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FunctionalException(String message, Throwable cause) {
		super(message, cause);
	}

	public FunctionalException(String message) {
		super(message);
	}

	public FunctionalException(Throwable cause) {
		super(cause);
	}

}
