package api.rest;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class ProfileRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/profiles";
		RestAssured.port = 8080;
	}

//	@Test
//	public void testGetAll() {
//		when().get("/").then().body(containsString("deniz"));
//	}

	@Test
	public void testGet() {
//		when().get("/"+id).then().body(containsString("malkah"));

		String id = "b16c6934-fe0f-45e5-ae5c-ee2d6277365e";
		String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJIYUxvYjd5NnpFTG1PQ1laRlE5SkIyY1BpelpubGpSMUZCbHllSXVOcUJFIn0.eyJqdGkiOiJlOTJmZTRlYS0wOGM5LTRmZjYtOWI4YS1hY2M2NWQ4ZTUzYjciLCJleHAiOjE1OTA1NzM3MDUsIm5iZiI6MCwiaWF0IjoxNTkwNTczNjQ1LCJpc3MiOiJodHRwczovL3BpbmZvNC51bmlnZS5jaC9hdXRoL3JlYWxtcy9hcGlndyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJiMTZjNjkzNC1mZTBmLTQ1ZTUtYWU1Yy1lZTJkNjI3NzM2NWUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ3ZWItc3NvIiwibm9uY2UiOiI5NDczZmMwNi0yOTdlLTRlOGYtODZmNC1jNDQyNWY4MzY0MDUiLCJhdXRoX3RpbWUiOjE1OTA1NzM1MTMsInNlc3Npb25fc3RhdGUiOiJkMWEzYjQyOS0zNDdjLTQ4NDEtODBkZS0xMmNlODRmNzA3NmMiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0LyoiLCJodHRwczovL3BpbmZvNC51bmlnZS5jaC8qIiwiKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJMdWtlIFNtaXRoIiwicHJlZmVycmVkX3VzZXJuYW1lIjoibHVsdSIsImdpdmVuX25hbWUiOiJMdWtlIiwiZmFtaWx5X25hbWUiOiJTbWl0aCIsImVtYWlsIjoibHVrZS5zbWl0aEBibHVld2luLmNoIn0.b-5lc4eej3LA9TKUJK8ltx5oqZGzHvVqm2yxZCbc_crdKHxEp_G-zaIFsZSz6lIWbC0pmfwee78hJTCe-RJThwQ_vTzNsZ6uEfEt7eX5r758sKjeIwJthUrMWXtFypn7S4piZPj82-KSoqh4oySja30uA8i1TWnJw6xxj1KvaBA";
		
        given().
            header("Authorization", "Bearer "+token).
        when().
            get("/"+id).
        then().
            body(containsString("Mathias"));
	}

//	@Test
//	public void testCount() {
//		when().get("/count").then().body(containsString("1"));
//	}
//
//	@Test
//	public void testGetIngredient() {
//		when().get("/2/ingredients").then().body(containsString("4"));
//	}
//
//	@Test
//	public void testGetFavourite() {
//		when().get("/2/favourites").then().body(containsString("14"));
//	}
//	
//	@Test
//	public void testGetCheck() {
//		when().get("/2/exists").then().body(containsString("true"));
//	}

}
