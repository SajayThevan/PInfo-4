package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class ChallengeRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/challenge";
		RestAssured.port = 8080;
	}

	@Test
	public void testGetAll() {
		when().get("/").then().body(containsString("CREPESAMERE"));
	}

	@Test
	public void testGet() {
		when().get("/1").then().body(containsString("CREPESAMERE"));
	}
	
	@Test
	public void testCount() {
		when().get("/count").then().body(containsString("3"));
	}
	
	@Test
	public void testGetIngredient() {
		when().get("/ingredients/2").then().body(containsString("20"));
	}
	
	@Test
	public void testGetSolution() {
		when().get("/solutions/2").then().body(containsString("14"));
	}
	
	@Test
	public void testGetName() {
		when().get("/name/2").then().body(containsString("CREPESAMERE"));
	}
	
	@Test
	public void testGetAuthor() {
		when().get("/author/2").then().body(containsString("14"));
	}

}