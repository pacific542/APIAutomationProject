package Core;

import java.io.File;

import Utils.Constants;
import Utils.JsonSchema;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class Endpoints extends TestBase {

	public static Response getAllProducts()
	{
		
		return  RestAssured
				.given()
				.header("Accept",Constants.ACCEPT)
				.when()
				.get("/products");
				
				
	}
	public static Response createUserWithLIst()
	{
		header_map.put("accept", Constants.ACCEPT);
		header_map.put("Content-Type", Constants.ACCEPT);
		return  RestAssured
				.given()
				.headers(header_map)
				.body(JsonSchema.getBody("createUsersWithList.json"))
				.when()
				.post("/v2/user/createWithList");
				
				
	}
	public static Response getUserByUsername(String username)
	{
		header_map.put("accept", Constants.ACCEPT);
		return  RestAssured
				.given()
				.headers(header_map)
				.when()
				.get("/v2/user/"+username);
				
				
	}
}
