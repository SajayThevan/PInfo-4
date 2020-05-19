package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;


public class ProfileRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/profiles";
		RestAssured.port = 8080;
	}

	@Test
	public void testGetAll() {
		when().get("/").then().body(containsString("deniz"));
	}

	@Test
	public void testGet() {
		when().get("/2").then().body(containsString("malkah"));
	}

	@Test
	public void testCount() {
		when().get("/count").then().body(containsString("6"));
	}

	@Test
	public void testGetIngredient() {
		when().get("/2/ingredients").then().body(containsString("4"));
	}

	@Test
	public void testGetFavourite() {
		when().get("/2/favourites").then().body(containsString("14"));
	}

}
