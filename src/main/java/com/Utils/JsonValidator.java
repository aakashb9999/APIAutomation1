package com.Utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonValidator {

	
	private String getJsonResponse(Response response) {
		return response.getBody().asString();
	}
	
	public <T> T read(Response response,String jsonpath){
		String jsonresponse = getJsonResponse(response);
		return JsonPath.read(jsonresponse, jsonpath);
	}
	
	public <T> List<Map<String,T>> readListMap (Response response,String jsonpath){
		String jsonResponse = getJsonResponse(response);
		return JsonPath.read(jsonResponse, jsonpath);
	}
	
	public <T> List <T> readList (Response response, String jsonpath){
		String jsonresponse = getJsonResponse(response);
		return JsonPath.read(jsonresponse, jsonpath);
	}
}
