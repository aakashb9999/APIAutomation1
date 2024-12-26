package com.Clients;

import java.util.Map;
import java.util.Properties;

import com.configManager.configurationManager;
import com.constants.APIHttpStatus;

import groovyjarjarasm.asm.tree.IntInsnNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtils {

	private RequestSpecBuilder specBuilder;
	private Properties pro;
	private String baseURI;
	boolean includeAuth=false;
    public RestUtils(Properties pro,String baseURI) {
    	specBuilder = new RequestSpecBuilder();
    	this.pro=pro;
    	this.baseURI=baseURI;
    }
	
    public void addContentType(String contentType) {
    	switch(contentType.toLowerCase()) {
    	case "json":
    		  specBuilder.setContentType(ContentType.JSON);
    		  break;
    	case "xml":
    		 specBuilder.setContentType(ContentType.XML);
    		 break;
    	case "text":
    		   specBuilder.setContentType(ContentType.TEXT);
    		   break;
    	case "multipart":
    	      specBuilder.setContentType(ContentType.MULTIPART);
    	      break;
    	  default:
    		  System.out.println("please enter valid contenttype");
    		  break;
    	}
    }
    
	public void addAuthorization() {
		if(!includeAuth) {
		specBuilder.addHeader("Authorization", "Bearer " + pro.getProperty("tokenid"));
		includeAuth=true;
		}
	}
	
	private RequestSpecification createSpec(boolean includeAuth) {
		   specBuilder.setBaseUri(baseURI);
		   if(includeAuth) {
		   addAuthorization();
		   }
		   return specBuilder.build();
	}
	
	private RequestSpecification createSpec(boolean includeAuth, Map <String,String> headers)
	{
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorization();
		}
		specBuilder.addHeaders(headers);
		specBuilder.setContentType(ContentType.JSON);
		return specBuilder.build();
	}
	
	private RequestSpecification createSpec(boolean includeAuth,Map<String,String>headers,Map<String,Object>queryparam) 
	{
		specBuilder.setBaseUri(baseURI);
		specBuilder.setContentType(ContentType.JSON);
		if(includeAuth) {
			addAuthorization();
		}
		
		specBuilder.addHeaders(headers);
		specBuilder.addQueryParams(queryparam);
		return specBuilder.build();
	}
	
	private RequestSpecification createSpec(boolean includeAuth,Object body,Map<String,String>headers,Map<String,Object> queryparams)
	{
		specBuilder.setBaseUri(baseURI);
		specBuilder.setContentType(ContentType.JSON);
		if(includeAuth) {
			addAuthorization();
		}
		specBuilder.setBody(body);
		specBuilder.addHeaders(headers);
		specBuilder.addQueryParams(queryparams);
		return specBuilder.build();
	}
	
	private RequestSpecification createSpec(boolean includeAuth,Object body)
	{
		specBuilder.setBaseUri(baseURI);
		specBuilder.setContentType(ContentType.JSON);
		if(includeAuth) {
			addAuthorization();
		}
		specBuilder.setBody(body);
		return specBuilder.build();
	}
	
	
	public Response get(String endpoint, boolean flag) {
		return RestAssured.given(createSpec(flag)).log().all()
				.when().get(endpoint);
	}
	
	public Response get(String endpoint , boolean includeAuth,Map<String,String>headers,Map<String,Object>query) {
		return RestAssured.given(createSpec(includeAuth, headers, query))
				.when().get(endpoint);
	}
	
	public Response get(String endpoint, boolean includeAuth,Map<String,String>headers) {
		return RestAssured.given(createSpec(includeAuth, headers))
				.when().get(endpoint);
	}
	
	public Response Post(String endpoint,boolean includeAuth,Object body) { 
		return RestAssured.given(createSpec(includeAuth, body))
				 .when().post(endpoint);
	}
	
	public Response Post(String endpoint, boolean includeAuth, Object body, Map<String,String>headers, Map<String,Object> queryparams)
	{
		return RestAssured.given(createSpec(includeAuth, body, headers, queryparams))
				.when().post(endpoint);
	}
	
	public String getAccessToken(String serviceUrl, String grantType, String clientId, String clientsecret) {
		RestAssured.baseURI = "https://test.api.amadeus.com";
		String tokenid= RestAssured.given().log().all()
				        .contentType(ContentType.URLENC)
				        .formParam("grant_type", grantType)
				        .formParam("client_id", clientId)
				        .formParam("client_secret", clientsecret)
				        .when().post(serviceUrl)
				        .then().log().all()
				        .extract().path("access_token");
		return tokenid;
	}
//	public static void main(String[] args) {
//		
//		String token=getAccessToken("v1/security/oauth2/token", "client_credentials", "RISbkLgssgXTjClZoZAM08CrAKCGaoER", "lTo37PBAsx0an25A");
//		System.out.println(token);
//	}
}
