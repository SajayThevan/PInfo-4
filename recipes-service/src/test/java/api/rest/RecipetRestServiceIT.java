package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import domain.service.RecipeService;

public class RecipetRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/Recipe";
		RestAssured.port = 8080;
	}
	
	RecipeService rs;
	
	@Test
	public void testGet() {
		
		when().post("create").then().body(containsString("254900LAW6SKNVPBBN21"));
	}
	
	
	/*
	  
	 *******************Keep for test example *********************** 
	 
	@Test
	public void testGetAll() {
		when().get("/").then().body(containsString("254900LAW6SKNVPBBN21"));

	}


	@Test
	public void testCount() {
		when().get("/count").then().body(containsString("10"));
	}
	*/
}