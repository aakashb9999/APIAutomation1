package com.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Clients.RestUtils;
import com.configManager.configurationManager;

public class BaseTest {
	protected configurationManager config;
	protected Properties pro;
	protected RestUtils restUtils;
	
	public static final String amedausendpoint="/v1/security/oauth2/token";
	public static final String flightEndPoint ="/v1/shopping/flight-destinations";
	
	@BeforeTest
	public void setup() {
		config = new configurationManager();
		pro = config.initProp();
	}
	
	
}
