package com.smsservice.exception;

/***
 * 
 * @author Shekar
 *
 */
public class TwilioSenderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private int code;

	public TwilioSenderException() {
	}

	public TwilioSenderException(String message) {
		this(0, message);
	}

	public TwilioSenderException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

}
