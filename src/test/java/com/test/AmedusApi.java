package com.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Clients.RestUtils;
import com.base.BaseTest;
import com.constants.APIHttpStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AmedusApi extends BaseTest {
	private String accessToken;
	
	@Parameters({"baseURI","grantType","clientId","clientSecret"})
	@BeforeMethod
	public void flightSetup(String baseURI,String grantType,String clientId,String ClientSecret) {
		restUtils = new RestUtils(pro, baseURI);
		accessToken=restUtils.getAccessToken(amedausendpoint, grantType, clientId, ClientSecret);
	}
	
	@Test
	public void getFlightDetails() {
		 Map<String,String > header = new HashMap<String,String>();
		 header.put("Authorization", "Bearer "+accessToken);
		 
		 Map<String,Object> queryParam = new HashMap<String,Object>();
		 queryParam.put("origin", "PAR");
		 queryParam.put("maxPrice", 200);
		 
		Response restFlight= restUtils.get(flightEndPoint, false, header,queryParam)
				             .then().log().all().assertThat()
				             .statusCode(APIHttpStatus.OK_200.getCode())
				             .extract()
				             .response();
		
		JsonPath js = restFlight.jsonPath();
		String type = js.get("data[0].type");
		System.out.println(type);//flight-destination
		
		System.out.println(accessToken);
		 
	}

}
