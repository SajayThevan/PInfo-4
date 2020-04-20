package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class IngredientRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/ingredient";
		RestAssured.port = 8080;
	}
	
	@Test
	public void testGetAll() {
		when().get("/").then().body(containsString("254900LAW6SKNVPBBN21"));
	}

	@Test
	public void testGet() {
		when().get("/1").then().body(containsString("254900LAW6SKNVPBBN21"));
	}

	// see how we want to manage paths !!  
	@Test
	public void testComputeCalories() {
		when().get("/computeCalories/[2, 3]").then().body(containsString("10"));
	}
	
	@Test
	public void testGetPossibleIngredients() {
		when().get("/getPossibleIngredients/choco").then().body(containsString("10"));
	}

}