# smsservice

Use this smsservice to send sms using Twilio Account

-Step1:   Extract zip file <br>
-Step2:   Import it into your desired IDE as a maven project (example:eclips,netbeans etc)<br>
-Step3:   Create an account in https://www.twilio.com/<br>
-Step4:    SignUp and Login into your account<br>
-Step5:   Goto Menu  on left side select "Programmable sms",click on get started and click on get your first twilio number<br>
-step5:   Random number appears on screen,if you want to change you can<br>
-step6:   GOTO "Console" on left top,you will see Console dashboard and credentials, Copy ACCOUNT SID,AUTH TOKEN<br>

So which we are using is 
<br>
    \<dependency\><br>
			\<groupId\>com.twilio.sdk\</groupId\><br>
			\<artifactId\>twilio-java-sdk\</artifactId\><br>
			\<version\>5.9.0\</version\><br>
			\<scope\>compile\</scope\><br>
		\</dependency\><br>
		
		Code to send SMS:
		static Account account = new Account("SID", "TOKEN","fromnumber", SMSSenderType.TWILIO);
	  static SenderFactory factory = null;
	  static SMSSender sender = null;

    	public static void main(String[] args) {
    		if (factory == null) {
    			factory = SenderFactory.getSenderFactory();
    		}
    		sender = factory.getSMSSender(account);
    		Map<String, String> dataMap = new HashMap<String, String>();
    		dataMap.put(Constants.TO, "tonumber");
    		// dataMap.put(Constants.FROM, null);//If you want you can set from number while sending
    		dataMap.put(Constants.MESSAGE, "Congrats,You are gootle now...Enjoy");
    		sender.send(dataMap);
        }
