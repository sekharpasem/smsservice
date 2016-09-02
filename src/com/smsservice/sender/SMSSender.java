package com.smsservice.sender;

import java.util.Map;

public interface SMSSender {
	/**
	 * sends sms by setting to,message
	 * 
	 * @param params
	 */
	void send(Map<String, String> params);

}
