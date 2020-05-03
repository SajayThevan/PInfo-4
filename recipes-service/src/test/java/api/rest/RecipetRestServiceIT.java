package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import java.util.ArrayList;

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
		
		when().get("/count").then().body(containsString("1"));

	}
	
	@Test
	public void recipesProfil() {
		when().get("/recipesProfil/1").then().body(containsString("pizza"));
	}
	
//	@Test
//	public void getRecipiesRestTest() {
//		
//		//Seemes to receive only ingredients and multiple times
//		
//		when().get("/getRecipe/1").then().body(containsString("pizza")); //Test name  -OK
//		when().get("/getRecipe/1").then().body(containsString("90")); //Test ingredients-OK
//		when().get("/getRecipe/1").then().body(containsString("Mauvais")); //Test commentaire
//	}
//	
	

}