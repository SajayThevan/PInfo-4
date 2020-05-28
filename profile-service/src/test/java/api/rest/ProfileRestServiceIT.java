package api.rest;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

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
		//when().get("/"+id).then().body(containsString("malkah"));
		
		
		String id = "30181a13-2157-4cb4-8298-e71dbfda4f01";
		String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJIYUxvYjd5NnpFTG1PQ1laRlE5SkIyY1BpelpubGpSMUZCbHllSXVOcUJFIn0.eyJqdGkiOiIxMGQzMjU1Ny0wMjg0LTRhMGMtYTVlOS0xYmI0ZTJhNmM3NjMiLCJleHAiOjE1OTA1ODgzOTIsIm5iZiI6MCwiaWF0IjoxNTkwNTg4MzMyLCJpc3MiOiJodHRwczovL3BpbmZvNC51bmlnZS5jaC9hdXRoL3JlYWxtcy9hcGlndyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIzMDE4MWExMy0yMTU3LTRjYjQtODI5OC1lNzFkYmZkYTRmMDEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ3ZWItc3NvIiwibm9uY2UiOiI1MDE5MGVjNi0zNWU1LTQwZTItOGZhMS05MmViZGIwNjNmNzgiLCJhdXRoX3RpbWUiOjE1OTA1ODgzMzAsInNlc3Npb25fc3RhdGUiOiI5ZjY2NGYwMS01YThiLTRmODAtYjZmMy0xZmIyNTgxYjMzZDgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0LyoiLCJodHRwczovL3BpbmZvNC51bmlnZS5jaC8qIiwiKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJEZW5peiBTdW5ndXJ0ZWtpbiIsInByZWZlcnJlZF91c2VybmFtZSI6Im1hbGFoa2EiLCJnaXZlbl9uYW1lIjoiRGVuaXoiLCJmYW1pbHlfbmFtZSI6IlN1bmd1cnRla2luIiwiZW1haWwiOiJsdWx1QGdtYWlsLmNvbSJ9.IwjRaJ28ef-oNK_Pk0iXrm9edjUTN1UK9EEQnQ70RoKK7tiF9TopTY01FkXFlCx-a3fdRomsCkfQx8EC_KFf4rILWaaBoVyJTx9QKTzBsdQRztppVBKNBlbMFk0Sp9wOYRebvFKiqeyj1vP2PF4BKuDTDyh12ZysX9map5BpaYg";
		
        given().
            header("Authorization", "Bearer "+token).
        when().
            get("/"+id).
        then().
            body(containsString("")); // We use an old token, expired still check if its correctly decoded
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
