package com.Api.RestAssured.Practice.Demo;

import static org.hamcrest.MatcherAssert.assertThat; // ✅ Correct one
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Core.StatusCode;
import Core.TestBase;
import Utils.ConfigReader;
import Utils.JsonTestDataReader;
import Utils.SoftAssertUtil;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
@Listeners(Utils.TestListener.class)
public class SecondClass extends TestBase{
	SoftAssertUtil sassert = new SoftAssertUtil();

	

	

	@Test (groups = {"regression"})
	public void validateGetRequestWithDataFromJsonFile() {
		RestAssured.baseURI = ConfigReader.getPropertyValue("url");

		Response response = RestAssured.given().auth()
				.basic(ConfigReader.getPropertyValue("username"),"password").when()
				.get("basic-auth").then().extract().response();
		System.out.println("Value from jason file" + StatusCode.SUCCESS.code);
		System.out.println("Value from response" + response.statusCode());
		sassert.assertEquals(response.statusCode(), StatusCode.SUCCESS.code, "States codes are not matching ");
		sassert.assertAll();

	}

	

}
