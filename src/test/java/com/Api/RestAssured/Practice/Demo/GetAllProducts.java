package com.Api.RestAssured.Practice.Demo;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;

import Core.Endpoints;
import Core.StatusCode;
import Core.TestBase;
import Utils.Constants;
import Utils.JsonSchema;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class GetAllProducts extends TestBase {
	@Test
	public void getAllProducts() {
		File schema = new File("Resource/Schema/getAllProducts.json");
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = Endpoints.getAllProducts();
		Response resp = RestAssured.given().header("Accept", Constants.ACCEPT).when().get("/products").then()
				.assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema)).extract().response();
		List<Integer> ids = resp.body().jsonPath().getList("id");

		sAssert.assertEquals(resp.statusCode(), StatusCode.SUCCESS.code,
				"Status code not mtching expected" + StatusCode.SUCCESS.code + "" + resp.statusCode());
		sAssert.assertTrue(resp.getHeader("Content-Type").contains("application/json"),
				Constants.ACCEPT + "is expected Content-Type but found" + resp.getHeader("Content-Type"));
		response_time = resp.time();
		sAssert.assertTrue(response_time < Constants.TIMEOUT_FOR_API, "Test");
		sAssert.assertEquals(resp.body().jsonPath().getList("id").size(), 20,
				"THe endpoint should return list of 20 products but it is retring list of "
						+ resp.body().jsonPath().getList("id").size() + "products");
		sAssert.assertEquals(ids.size(), (int) ids.stream().distinct().count(), "Duplicate IDs found in response: ");
		System.out.println(resp.getHeader("Content-Type"));
		System.out.println(resp.asPrettyString());

	}
	@Test
	public void verifyStatusCode() {
		Response response = Endpoints.getAllProducts();
		response
		.then()
		.assertThat()
			.statusCode(StatusCode.SUCCESS.code);

	}
	@Test
	public void verifySchema() {
		Response response = Endpoints.getAllProducts();
		response
		.then()
		.assertThat()
				.body(JsonSchemaValidator.matchesJsonSchema(JsonSchema.getSchema("getAllProducts.json")));

	}
	@Test
	public void verifyContentType() {
		Response response = Endpoints.getAllProducts();
		sAssert.assertTrue(response.getHeader("Content-Type").contains("application/json"),Constants.ACCEPT+"is expected but found"+response.getHeader("Content-Type"));
		

	}
	@Test
	public void verifyResponseTime() {
		Response response = Endpoints.getAllProducts();
		sAssert.assertTrue(response.time()<Constants.TIMEOUT_FOR_API,"Response time should be less than"+Constants.TIMEOUT_FOR_API+"but it is "+response.time());
		

	}
	
	@Test
	public void verifyNumberOfProducts() {
		Response response = Endpoints.getAllProducts();
		sAssert.assertTrue(response.body().jsonPath().getList("id").size()==Constants.TOTAL_PRODUCTS,"Expected"+Constants.TOTAL_PRODUCTS+"but found"+response.body().jsonPath().getList("id").size()+"products");

	}
	@Test
	public void verifyUnqueProductIDs() {
		Response response = Endpoints.getAllProducts();
		sAssert.assertTrue(response.body().jsonPath().getList("id").stream().distinct().count()==Constants.TOTAL_PRODUCTS,"Dupe IDs found");
	}
	
	@Test
	public void verifyCatagories() {
		Response response = Endpoints.getAllProducts();
		List<String> category = response.body().jsonPath().getList("category");
		sAssert.assertTrue(Constants.ALLOWEDCATAGORIES_LIST.containsAll(category), "Response has some catagories that are not allowed");
		
	}
	@Test
	public void verifyTitlesAndDescriptionsNotEmpty() {
		Response response = Endpoints.getAllProducts();
		List<String> title = response.body().jsonPath().getList("title");
		List<String> description = response.body().jsonPath().getList("description");
		for (String each:title)
		{
			sAssert.assertFalse(each.isEmpty(),"empty title found");
		}
		for (String each:description)
		{
			sAssert.assertFalse(each.isEmpty(),"empty description found");
		}
		
	}

}
