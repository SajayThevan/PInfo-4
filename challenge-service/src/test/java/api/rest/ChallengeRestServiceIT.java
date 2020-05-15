package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class ChallengeRestServiceIT {

	@BeforeAll
	public static void setup() {
		System.out.println("-----------------DEBUT SETUP SERVEUR-----------------");
		RestAssured.baseURI = "http://localhost:28080/challenge";
		RestAssured.port = 8080;
		System.out.println("-----------------FIN SETUP SERVEUR-----------------");
	}

	@Test
	public void testGetAll() {
		System.out.println("-----------------DEBUT TEST GETAllREST-----------------");
		when().get("/").then().body(containsString("CREPESAMERE"));
		System.out.println("-----------------FIN TEST GETAllREST-----------------");

	}

	@Test
	public void testGet() {
		System.out.println("-----------------DEBUT TEST GETREST-----------------");
		when().get("/1").then().body(containsString("CREPESAMERE"));
		System.out.println("-----------------FIN TEST GETREST-----------------");
	}
	
	@Test
	public void testCount() {
		System.out.println("-----------------DEBUT TEST COUNTREST-----------------");
		when().get("/count").then().body(containsString("3"));
		System.out.println("-----------------FIN TEST COUNTREST-----------------");
	}
	
	@Test
	public void testGetIngredient() {
		System.out.println("-----------------DEBUT TEST GetIngredient-----------------");
		when().get("/ingredients/2").then().body(containsString("20"));
		System.out.println("-----------------FIN TEST GetIngredient-----------------");
	}
	
	@Test
	public void testGetSolution() {
		System.out.println("-----------------DEBUT TEST GetSolution-----------------");
		when().get("/solutions/2").then().body(containsString("14"));
		System.out.println("-----------------FIN TEST GetSolution-----------------");
	}
	
	@Test
	public void testGetName() {
		System.out.println("-----------------DEBUT TEST GetName-----------------");
		when().get("/name/2").then().body(containsString("CREPESAMERE"));
		System.out.println("-----------------FIN TEST GetName-----------------");
	}
	
	@Test
	public void testGetAuthor() {
		System.out.println("-----------------DEBUT TEST GetAuthorID-----------------");
		when().get("/author/2").then().body(containsString("14"));
		System.out.println("-----------------FIN TEST GetAuthorID-----------------");
	}

}