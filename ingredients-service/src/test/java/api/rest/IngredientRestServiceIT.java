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
		System.out.println("part 1");
		when().get("/4").then().body(containsString("chocolat"));
		System.out.println("part 2");

	}
	
	@Test
	public void testGet2() {
		System.out.println("part 1 bis ");
		when().get1("/heyIciTest").then().body(containsString("10"));
		System.out.println("part 2 bis");

	}

	// see how we want to manage paths !!  
	@Test
	public void testComputeCalories() {
		when().get("/computeCalories/[4, 5]").then().body(containsString("11"));
	}
	
	@Test
	public void testGetPossibleIngredients() {
		when().get("/getPossibleIngredients/choco").then().body(containsString("chocolat"));
	}

}