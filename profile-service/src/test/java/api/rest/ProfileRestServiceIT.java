package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class ProfileRestServiceIT {

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
		when().get("/").then().body(containsString("254900LAW6SKNVPBBN21"));
		System.out.println("-----------------FIN TEST GETAllREST-----------------");

	}

	@Test
	public void testGet() {
		System.out.println("-----------------DEBUT TEST GETREST-----------------");
		when().get("/1").then().body(containsString("254900LAW6SKNVPBBN21"));
		System.out.println("-----------------DEBUT TEST GETREST-----------------");
	}
	
	@Test
	public void testCount() {
		System.out.println("-----------------DEBUT TEST COUNTREST-----------------");
		when().get("/count").then().body(containsString("10"));
		System.out.println("-----------------DEBUT TEST COUNTREST-----------------");
	}

}