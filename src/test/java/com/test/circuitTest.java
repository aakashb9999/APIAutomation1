package com.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Clients.RestUtils;
import com.Utils.JsonValidator;
import com.base.BaseTest;

import io.restassured.response.Response;

public class circuitTest extends BaseTest {
	
	@BeforeMethod
	public void getdata() {
		restUtils = new RestUtils(pro, "http://ergast.com");
	}

	@Test
	public void getCircuitData() {
		Response response=restUtils.get("/api/f1/2017/circuits.json", false);
		
		JsonValidator js = new JsonValidator();
		List<String> countryList = js.readList(response, "$.MRData.CircuitTable.Circuits[?(@.circuitId == 'shanghai')].Location.country");
		System.out.println(countryList);
		Assert.assertTrue(countryList.contains("China"));
		
	}
}
