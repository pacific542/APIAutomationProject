package com.Api.RestAssured.Practice.Demo;

import static org.hamcrest.MatcherAssert.assertThat; // ✅ Correct one
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Core.StatusCode;
import Core.TestBase;
import Pojo.postReqPOJO;
import Utils.ConfigReader;
import Utils.JsonTestDataReader;
import Utils.SoftAssertUtil;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
@Listeners(Utils.TestListener.class)
public class FirstClass extends TestBase{


	@Test(groups = { "smoke", "regression" })
	public void sample() {
		RestAssured.baseURI = "https://reqres.in";
		Response response = RestAssured.given().when().get("/api/users?page=2").then().extract().response();
		System.out.println(response.body().asPrettyString());
		// HOw to get individual string from response
		String supportText = response.jsonPath().get("support.text").toString();
		// HOw to get list of string from response
		List<String> emails = response.jsonPath().getList("data.email");
		// use of hasitems
		assertThat(emails, hasItems("michael.lawson@reqres.in", "byron.fields@reqres.in"));
		// use of has size
		assertThat(emails, hasSize(6));
		// use of contain
		assertThat(emails, contains("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in",
				"byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"));

	}

	@Test(groups = { "smoke", "regression" })
	public void validateDataofFirstUser() {
		RestAssured.baseURI = "https://reqres.in";

		Response response = RestAssured.given().contentType("application/json").header("x-api-key", "reqres-free-v1")
				.body("{\"name\": \"John Doe\"}")
				// .formParam("name", "John Doe")
				.pathParam("users", "users").when().post("/api/{users}").then().extract().response();

		System.out.println(response.asPrettyString());
	}

	@Test(groups = { "smoke", "regression" })
	public void fetchResponseHeaders() {
		RestAssured.baseURI = "https://reqres.in";

		Response response = RestAssured.given().auth().basic("", "").contentType("application/json")
				.header("x-api-key", "reqres-free-v1").pathParam("users", "users").queryParam("page", "2").when()
				.get("/api/{users}").then().extract().response();
		Map<String, String> c = response.getCookies();

		Headers header = response.getHeaders();
		System.out.println(c);

		// System.out.println(response.asPrettyString());
	}

	@Test(groups = { "regression" })
	public void sample3() {
		Assert.assertEquals(DayOfWeek.Monday.getString(), "Mon");

		// System.out.println(response.asPrettyString());
	}

	@Test(groups = { "regression" })
	public void bodyAsString() {
		RestAssured.baseURI = "https://reqres.in/";
		Response response = RestAssured.given().header("x-api-key","reqres-free-v1").body("{\n" +
						"    \"name\": \"morpheus\",\n" +
						"    \"job\": \"leader\"\n" +
						"}").header("Content-Type","application/json")
				.when().post("api/users");
		
		System.out.println(response.getBody().asString());
	}
	@Test(groups = { "regression" })
	public void bodyJsonFile() {
		 File jsonFile = new File("Resource/TestData/postReqData.json");
		RestAssured.baseURI = "https://reqres.in/";
		Response response = RestAssured.given().header("x-api-key","reqres-free-v1").body(jsonFile).header("Content-Type","application/json")
				.when().post("api/users");
		
		System.out.println(response.getBody().asString());
	}
	@Test(groups = { "regression" })
	public void bodyUsingPOJO() {
		postReqPOJO postreq=new postReqPOJO("nitu", "wife");
		RestAssured.baseURI = "https://reqres.in/";
		Response response = RestAssured.given().header("x-api-key","reqres-free-v1").body(postreq).header("Content-Type","application/json")
				.when().post("api/users");
		sAssert.assertEquals(response.statusCode(),StatusCode.CREATED.code,"Status code is not matching");
		
		System.out.println(response.getBody().asString());
		sAssert.assertAll();
	}
	@Test(enabled = false)
    public void uploadFile() {
        File fileToUpload = new File("resources/sample.txt");

        Response response = RestAssured
                .given()
                .baseUri("https://example.com")
                .multiPart("file", fileToUpload)  // 'file' is the form field name
                .when()
                .post("/upload");

        response.then().statusCode(200);  // or 201 depending on API
        System.out.println(response.prettyPrint());
    }

	@DataProvider
	public String[][] testData() {
		String[][] arr = { { "1", "Suresh" }, { "2", "Ramesh" }, { "3", "Kamlesh" } };

		return arr;

	}

	@Test(dataProvider = "testData", groups = { "smoke" })
	public void printTestData(String Roll, String Name) {
		System.out.println("Roll number" + Roll + "Name is " + Name);
	}
	
}
