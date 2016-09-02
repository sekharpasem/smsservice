package com.smsservice.sender.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.smsservice.constants.Constants;
import com.smsservice.constants.Constants.SMSSenderType;
import com.smsservice.exception.TwilioSenderException;
import com.smsservice.sender.SMSSender;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class TwilioSmsSender implements SMSSender {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private Account data = null;

	/***
	 * 
	 * @param username
	 * @param password
	 * @param senderType
	 */
	public TwilioSmsSender(String username, String password, SMSSenderType senderType) {
		this(username, password, null, senderType);
		logger.info("Creating TwilioSmsSender instance");

	}

	/***
	 * 
	 * @param username
	 * @param password
	 * @param fromNumber
	 * @param senderType
	 */
	public TwilioSmsSender(String username, String password, String fromNumber, SMSSenderType senderType) {
		logger.info("Creating TwilioSmsSender instance");
		this.data = new Account(username, password, fromNumber, senderType);
	}

	/***
	 * 
	 * @param data
	 */
	public TwilioSmsSender(Account data) {
		logger.info("Creating TwilioSmsSender instance");
		this.data = new Account(data.getUsername(), data.getPassword(), data.getFromNumber(), data.getSenderType());
	}

	@Override
	public void send(Map<String, String> params) throws TwilioSenderException {
		logger.info("Sending sms params " + params);
		validate(params);
		TwilioRestClient client = new TwilioRestClient(data.getUsername(), data.getPassword());

		List<NameValuePair> NVPparams = setNVPParams(params);

		MessageFactory messageFactory = client.getAccount().getMessageFactory();
		Message message = null;
		try {
			message = messageFactory.create(NVPparams);
		} catch (TwilioRestException e) {
			throw new TwilioSenderException(e.getErrorCode(), e.getMessage());
		}
		if (message != null) {
			Logger.getLogger(this.getClass().getName());
		}

	}

	/**
	 * @param params
	 * @return
	 */
	private List<NameValuePair> setNVPParams(Map<String, String> params) {
		String from = (String) params.get(Constants.FROM);
		if(from==null){
			from=data.getFromNumber();
		}
		
		String to = (String) params.get(Constants.TO);
		String body = (String) params.get(Constants.MESSAGE);

		// Build the parameters
		List<NameValuePair> NVPparams = new ArrayList<NameValuePair>();
		NVPparams.add(new BasicNameValuePair("To", to));
		NVPparams.add(new BasicNameValuePair("From", from));
		NVPparams.add(new BasicNameValuePair("Body", body));
		return NVPparams;
	}

	private void validate(Map<String, String> params) {
		logger.info("validate pramas " + params);
		String message = (String) params.get(Constants.MESSAGE);
		String to = (String) params.get(Constants.TO);
		String from = (String) params.get(Constants.FROM);
		if (params == null || to == null || to.isEmpty() || message == null || message.isEmpty()
				|| (from == null && data.getFromNumber() == null)) {
			throw new TwilioSenderException(0, "to number,message,senderType params should not be empty or null.");
		}

	}

}
