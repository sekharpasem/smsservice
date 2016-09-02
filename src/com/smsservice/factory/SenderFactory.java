package com.smsservice.factory;

import com.smsservice.sender.SMSSender;
import com.smsservice.sender.impl.Account;
import com.smsservice.sender.impl.TwilioSmsSender;

public class SenderFactory {
	private static volatile SenderFactory senderFactory = null;

	private SenderFactory() {

	}

	/**
	 * gets smssender
	 * 
	 * @param account
	 * @return
	 */
	public SMSSender getSMSSender(Account account) {
		switch (account.getSenderType()) {
		case TWILIO:
			return new TwilioSmsSender(account);

		default:
			return null;
		}
	}

	public static SenderFactory getSenderFactory() {
		if (senderFactory == null) {
			synchronized (SenderFactory.class) {
				if (senderFactory == null) {
					senderFactory = new SenderFactory();
				}
			}
		}
		return senderFactory;
	}
}
