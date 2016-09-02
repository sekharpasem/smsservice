package com.smsservice.sender.impl;

import com.smsservice.constants.Constants.SMSSenderType;

/**
 * 
 * @author Shekar
 *
 */
public class Account {
	private String username;
	private String password;
	private String fromNumber;
	private SMSSenderType senderType;

	public Account(String username, String password, String fromNumber, SMSSenderType senderType) {
		this.username = username;
		this.password = password;
		this.fromNumber = fromNumber;
		this.senderType = senderType;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFromNumber() {
		return fromNumber;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}

	public SMSSenderType getSenderType() {
		return senderType;
	}

	public void setSenderType(SMSSenderType senderType) {
		this.senderType = senderType;
	}

}