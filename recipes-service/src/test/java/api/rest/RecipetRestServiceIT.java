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
	public void testCount() {
		
		when().get("/count").then().body(containsString("4"));

	}
	
	@Test
	public void recipesProfil() {
		when().get("/recipesProfil/10").then().body(containsString("pizza"));
	}
	/*
	
//	@Test
//	public void test() {
//		 when().get("/1")
//	        .then()
//	        .body(containsString));
//	
//	}
	
	
	/*
	  
	 *******************Keep for test example *********************** 
	 
	


	@Test
	public void testCount() {
		when().get("/count").then().body(containsString("10"));
	}
	*/
}