package com.Api.RestAssured.Practice.Demo;

import java.io.File;

import org.testng.annotations.Test;

import Core.PetStatus;
import Core.StatusCode;
import Core.TestBase;
import Utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EasyAPIScenarios extends TestBase {

	@Test
	public  void findPetByID()
	{
		RestAssured.baseURI=ConfigReader.getPropertyValue("petstoreurl");
		
		Response response = RestAssured
		.given()
		.header("accept","application/json")
		.header("api_key","special-key")
		.pathParam("version","v2")
		.when()
		.get("/{version}/pet/1")
		.then()
		.extract()
		.response();
		sAssert.assertEquals(response.statusCode(),StatusCode.SUCCESS.code , "Code is not matching");
		
	}
	@Test
	public  void findPetByStatus()
	{RestAssured.baseURI=ConfigReader.getPropertyValue("petstoreurl");
		
		Response response = RestAssured
		.given()
		.header("accept","application/json")
		.header("api_key","special-key")
		.pathParam("version","v2")
		.queryParam("status", PetStatus.SOLD.getStatus())
		.when()
		.get("/{version}/pet/findByStatus")
		.then()
		.extract()
		.response();
		
		sAssert.assertEquals(response.statusCode(),StatusCode.SUCCESS.code , "Code is not matching");
		
	}
	
	@Test
	public  void updatePetForm()
	{RestAssured.baseURI=ConfigReader.getPropertyValue("petstoreurl");
	header_map.put("accept","application/json");
	header_map.put("api_key","special-key");
	header_map.put("Content-Type","application/x-www-form-urlencoded");
	form_map.put("name", "prashant");
	form_map.put("status", "sold");
		Response response = RestAssured
		.given()
		.headers(header_map)
		.pathParam("version","v2")
		.formParams(form_map)
		.when()
		.post("/{version}/pet/1")
		.then()
		.extract()
		.response();
		System.out.println(response.asPrettyString());
		sAssert.assertEquals(response.statusCode(),StatusCode.SUCCESS.code , "Expected status code 200 but found "+response.statusCode());
		
	}
	
	@Test 
	public  void updatePetBody()
	{
		RestAssured.baseURI="https://petstore.swagger.io";
		header_map.put("accept","application/json");
		header_map.put("api_key","special-key");
		header_map.put("Content-Type","application/json");
		Response response=RestAssured
				.given()
				.headers(header_map)
				.body("{\"id\":300,\"category\":{\"id\":33,\"name\":\"parrot\"},\"name\":\"Nitu_parrot\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}")
				.when()
				.put("/v2/pet")
				.then()
				.extract()
				.response();
		System.out.println(response.asPrettyString());
		sAssert.assertEquals(response.statusCode(),StatusCode.SUCCESS.code,"Expected"+StatusCode.SUCCESS.code+"But found"+response.statusCode());
		
				
				
		
	}
	@Test 
	public  void deletePet()
	{
		RestAssured.baseURI="https://petstore.swagger.io";
		header_map.put("accept","application/json");
		header_map.put("api_key","special-key");
		Response response=RestAssured
				.given()
				.headers(header_map)
				.pathParam("petid", "1")
				.when()
				.delete("/v2/pet/{petid}")
				.then()
				.extract()
				.response();
		System.out.println(response.asPrettyString());
		sAssert.assertEquals(response.statusCode(),StatusCode.SUCCESS.code,"Expected"+StatusCode.SUCCESS.code+"But found"+response.statusCode());
		
				
				
		
	}
	@Test (groups = {"regression"})
	public  void uploadImage()
	{
		RestAssured.baseURI="https://petstore.swagger.io";
		header_map.put("accept","application/json");
		header_map.put("api_key","special-key");
		header_map.put("Content-Type","multipart/form-data");
		form_map.put("additionalMetadata", "fucker");
		File file =new File("Resource/sample.txt");
		Response response=RestAssured
				.given()
				.headers(header_map)
				.formParams(form_map)
				.pathParam("petid", "9223372036854744000")
				.multiPart(file)
				.when()
				.post("/v2/pet/{petid}/uploadImage")
				.then()
				.extract()
				.response();
		System.out.println(response.asPrettyString());
		sAssert.assertEquals(response.statusCode(),StatusCode.SUCCESS.code,"Expected"+StatusCode.SUCCESS.code+"But found"+response.statusCode());
		
				
				
		
	}
	
}
