package com.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Clients.RestUtils;
import com.Utils.Excel;
import com.Utils.stringUtils;
import com.base.BaseTest;
import com.pojo.*;
import static org.hamcrest.Matchers.equalTo;

public class createUser extends BaseTest {
	
	@BeforeMethod
	public void getUserSetUp() {
		restUtils = new RestUtils(pro, "https://gorest.co.in");
	}
	
	@DataProvider
	public Object [][] adduser(){
			return new Object [][] {
				{"akash","male","active"}
			};
	}
	
	@DataProvider
	public Object [][] excelSheetData(){
		return Excel.getTestData("Sheet1");
	}
	
	@Test(dataProvider = "excelSheetData")
	public void createUserdata(String name,String gender,String status) {
		User user = new User( name, stringUtils.generateRandomemail(), gender, status);
		Integer userid=restUtils.Post("/public/v2/users", true, user)
		.then().log().all()
		.extract().response().path("id");
		System.out.println("user id is " + userid);
		
		RestUtils clientGet = new RestUtils(pro, "https://gorest.co.in");
		
		    clientGet.get("/public/v2/users"+"/"+userid, true).
		    then().log().all()
		    .assertThat().body("id", equalTo(userid));
		
		
	}

}
