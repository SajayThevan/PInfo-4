package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.model.Ingredient;
import io.restassured.RestAssured;

public class IngredientRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/ingredients";
		RestAssured.port = 8080;
	}

	@Test
	public void testGetAll() {
		when().get("/").then().body(containsString("chocolat"));
	}

	@Test
	public void testGetAllNames() {
		when().get("/names").then().body(containsString("amande"));
		when().get("/names").then().body(containsString("3"));
	}
	
	@Test
	public void testGetSelectedIngredients() {
		when().get("/selected?id=1&id=2&id=4").then().body(containsString("chocolat"));
		when().get("/selected?id=1&id=2&id=4").then().body(containsString("2"));
		when().get("/selected?id=1&id=2&id=4").then().body(containsString("pate"));
	}


	@Test
	public void testGet() {
		when().get("/2").then().body(containsString("amande"));
	}

	@Test
	public void testComputeCalories() {
		when().get("/calories?id=1&id=2&id=4").then().body(containsString("9.1"));
	}
	
	@Test
	public void testComputeFat() {
		when().get("/fat?id=1&id=2&id=4").then().body(containsString("9"));
	}
	
	@Test
	public void testComputeCholesterol() {
		when().get("/cholesterol?id=1&id=2&id=4").then().body(containsString("6.7"));
	}
	
	@Test
	public void testComputeProteins() {
		when().get("/proteins?id=1&id=2&id=4").then().body(containsString("9"));
	}
	
	@Test
	public void testComputeSalt() {
		when().get("/salt?id=1&id=2&id=4").then().body(containsString("4"));
	}

	@Test
	public void testCount() {
		when().get("/count").then().body(containsString("13"));
	}
}
