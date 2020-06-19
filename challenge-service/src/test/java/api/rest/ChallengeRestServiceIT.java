package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

class ChallengeRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/challenges";
		RestAssured.port = 8080;
	}

	@Test
	void testGetAll() {
		when().get("/").then().body(containsString("CREPESAMERE"));
	}

	@Test
	void testGet() {
		when().get("/1").then().body(containsString("CREPESAMERE"));
	}

	@Test
	void testCount() {
		when().get("/count").then().body(containsString("3"));
	}

	@Test
	void testGetIngredient() {
		when().get("2/ingredients").then().body(containsString("4"));
	}

	@Test
	void testGetSolution() {
		when().get("2/solutions").then().body(containsString("14"));
	}

	@Test
	void testGetName() {
		when().get("2/name").then().body(containsString("CREPESAMERE"));
	}

	@Test
	void testGetAuthor() {
		when().get("2/author").then().body(containsString("14"));
	}

}
