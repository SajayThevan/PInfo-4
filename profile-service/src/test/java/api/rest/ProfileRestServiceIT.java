package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;


public class ProfileRestServiceIT {
	
	@PersistenceContext(unitName = "ProfilePU") 
	private EntityManager em;
	
	
	
	@BeforeAll
	public static void setup() {
		System.out.println("-----------------DEBUT SETUP SERVEUR-----------------");
		RestAssured.baseURI = "http://localhost:28080/profile";
		RestAssured.port = 8080;
		System.out.println("-----------------FIN SETUP SERVEUR-----------------");
	
	}

	@Test
	public void testGetAll() {
		System.out.println("-----------------DEBUT TEST GETAllREST-----------------");
		when().get("/").then().body(containsString("deniz"));
		System.out.println("-----------------FIN TEST GETAllREST-----------------");

	}

	@Test
	public void testGet() {
		System.out.println("-----------------DEBUT TEST GETREST-----------------");
		when().get("/2").then().body(containsString("malkah"));
		System.out.println("-----------------FIN TEST GETREST-----------------");
	}
	
	@Test
	public void testCount() {
		System.out.println("-----------------DEBUT TEST COUNTREST-----------------");
		when().get("/count").then().body(containsString("6"));
		System.out.println("-----------------FIN TEST COUNTREST-----------------");
	}
	
	@Test
	public void testGetIngredient() {
		System.out.println("-----------------DEBUT TEST testGetIngredient-----------------");
		when().get("/ingredients/2").then().body(containsString("4"));
		System.out.println("-----------------FIN TEST testgetIngredient-----------------");
	}
	
	@Test
	public void testgetFavourite() {
		System.out.println("-----------------DEBUT TEST testGetFavourite-----------------");
		when().get("/favourites/2").then().body(containsString("14"));
		System.out.println("-----------------FIN TEST testGetFavourite-----------------");
	}
	
	
	
	@Test
	public void testRemoveProfileById() {
		System.out.println("-----------------DEBUT TEST testDelete-----------------");
		when().delete("/delete/2").then().body(containsString(""));
		System.out.println("-----------------FIN TEST testDelete-----------------");
	}
	
	
	
//	@Test 				WORK BUT Bizzare 
//	public void testAddIngredient() {
//		System.out.println("-----------------DEBUT TEST testGetFavourite-----------------");
//		
//
//		when().put("/add/2/24/200").then().body(containsString("200"));
//		System.out.println("-----------------FIN TEST testGetFavourite-----------------");
//	}


}