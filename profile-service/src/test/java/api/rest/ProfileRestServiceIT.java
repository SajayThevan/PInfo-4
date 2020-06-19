package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

class ProfileRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/profiles";
		RestAssured.port = 8080;
	}

	@Test
	void testGetAll() {
		when().get("/").then().body(containsString("deniz"));
	}

	@Test
	void testGet() {
		when().get("/2").then().body(containsString("malkah"));

	}

	@Test
	void testCount() {
		when().get("/count").then().body(containsString("2"));
	}

	@Test
	void testGetIngredient() {
		when().get("/2/ingredients").then().body(containsString("4"));
	}

	@Test
	void testGetFavourite() {
		when().get("/2/favourites").then().body(containsString("14"));
	}

	@Test
	void testGetCheck() {
		when().get("/2/exists").then().body(containsString("true"));
	}

}
