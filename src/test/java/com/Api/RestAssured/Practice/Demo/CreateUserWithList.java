package com.Api.RestAssured.Practice.Demo;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Endpoint;

import Core.Endpoints;
import Core.StatusCode;
import Core.TestBase;
import Utils.JsonTestDataReader;
import io.restassured.response.Response;
@Listeners(Utils.TestListener.class)
public class CreateUserWithList extends TestBase {
	@Test(groups = {"Regression"})
	public void validateStatusCode()
	{
	    Response response=Endpoints.createUserWithLIst();
	    System.out.println(response.asPrettyString());
	    sAssert.assertEquals(response.statusCode(), StatusCode.BAD_REQUEST.code, "Status code epected"+StatusCode.SUCCESS.code+"But found"+response.statusCode());
	
	}
	@Test 
	public void validateCreatedData()
	{
	    Response response=Endpoints.createUserWithLIst();
	   JSONArray userArray = JsonTestDataReader.getUsersArrayFromFile("createUsersWithList.json");
	   for (int i = 0; i < userArray.length(); i++) {
		
		   Set<String> keys = userArray.getJSONObject(i).keySet();
		   Response get_response = Endpoints.getUserByUsername(userArray.getJSONObject(i).get("username").toString());
		   for(String each:keys)
		   { System.out.println("post request value"+userArray.getJSONObject(i).get(each).toString()+" \nGet request value "+get_response.getBody().jsonPath().get(each).toString());
			   sAssert.assertEquals(userArray.getJSONObject(i).get(each).toString(), get_response.getBody().jsonPath().get(each).toString(),"Data is not matching");
		   }
	}
	
	  
	   
	 
//	  Map<String, String>  get_response_map = get_response.jsonPath().getMap("$");
//	  Set<String> get_response_keys = get_response_map.keySet();
//	  
//	  sAssert.assertEquals(get_response.getBody().jsonPath().get("email"), userArray.getJSONObject(0).get("email").toString(), "Data is not matching");
//	  System.out.println(get_response.asPrettyString());
	   
	}

}
