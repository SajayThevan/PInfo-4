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
		RestAssured.baseURI = "http://localhost:28080/ingredient";
		RestAssured.port = 8080;
	}
	
	@Test
	public void testGetAll() {
		when().get("/").then().body(containsString("chocolat"));
	}
	/*
	@Test
	public void testGet() {
		// ingredientsService.create(getRandomIngredient());
		Ingredient ingredients1 = getRandomIngredient();
		Long id = ingredients1.getId();
		String idLink = "/" + id;
		System.out.println("TEST : " + ingredients1);
		System.out.println("TEST : " + idLink);
		//when().get("/1").then().body(containsString("chocolat"));
		when().get(idLink).then().body(containsString("chocolat"));

	}
	*/
	@Test
	public void testGet() {
		when().get("/1").then().body(containsString("chocolat"));
	}

	// see how we want to manage paths !!  
	@Test
	public void testComputeCalories() {
		when().get("/computeCalories").then().body(containsString("2"));
	}
	
	@Test
	public void testGetPossibleIngredients() {
		when().get("/getPossibleIngredients/choco").then().body(containsString("chocolat"));
	}

}