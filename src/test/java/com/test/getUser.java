package com.test;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Clients.RestUtils;
import com.base.BaseTest;
import com.configManager.configurationManager;

public class getUser extends BaseTest {
	@BeforeMethod
	public void getUserSetup() {
		restUtils = new RestUtils(pro, "https://gorest.co.in");
	}
	
	@Test
	public void getUserDeatils() {
		restUtils.get("/public/v2/users",true).then().log().all();
	}
	
	


}
