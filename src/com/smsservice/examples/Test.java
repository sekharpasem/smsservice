package com.smsservice.examples;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.smsservice.constants.Constants;
import com.smsservice.constants.Constants.SMSSenderType;
import com.smsservice.factory.SenderFactory;
import com.smsservice.sender.SMSSender;
import com.smsservice.sender.impl.Account;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class Test {
	static Account account = new Account("ACe5d446a04907a2d5a21fca2d8ea3c9f2", "3892b175704ac3c2f62134c0c93e33cd",
			"+12018855307", SMSSenderType.TWILIO);
	static SenderFactory factory = null;
	static SMSSender sender = null;

	public static void main(String[] args) {
		long startTime = new Date().getTime();
		if (factory == null) {
			factory = SenderFactory.getSenderFactory();
		}
		sender = factory.getSMSSender(account);
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put(Constants.TO, "+919533644111");
		// dataMap.put(Constants.FROM, "+12018855307");
		dataMap.put(Constants.MESSAGE, "Congrats,You are gootle now...Enjoy");
		sender.send(dataMap);
		long endTime = new Date().getTime();
		System.out.println("TotalTime>>" + (endTime - startTime));
		//send("+919010181010", "Hi,Sekhar welcome...Your awesome.");

	}

	public static void send(String to, String body) {
		long startTime = new Date().getTime();
		TwilioRestClient client = new TwilioRestClient("ACe5d446a04907a2d5a21fca2d8ea3c9f2",
				"3892b175704ac3c2f62134c0c93e33cd");

		// Build the parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("To", to));
		params.add(new BasicNameValuePair("From", "+12018855307"));
		params.add(new BasicNameValuePair("Body", body));

		MessageFactory messageFactory = client.getAccount().getMessageFactory();
		Message message = null;
		try {
			message = messageFactory.create(params);
		} catch (TwilioRestException e) {

		}
		long endTime = new Date().getTime();
		System.out.println("TotalTime>>" + (endTime - startTime));
	}

}
