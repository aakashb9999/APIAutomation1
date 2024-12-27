package com.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;

import com.Clients.RestUtils;
import com.configManager.configurationManager;
import com.listeners.ExtentReportListener;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	protected configurationManager config;
	protected Properties pro;
	protected RestUtils restUtils;
	
	public static final String amedausendpoint="/v1/security/oauth2/token";
	public static final String flightEndPoint ="/v1/shopping/flight-destinations";
	
	@BeforeTest
	public void setup() {
		RestAssured.filters(new AllureRestAssured());
		config = new configurationManager();
		pro = config.initProp();
	}
	
	
}
