package com.google.oauth2.applicationApi;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.util.HashMap;

import com.google.oauth2.api.Routes;
import com.google.oauth2.api.SpecBuilder;
import com.google.oauth2.api.TokenManager;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GmailApi {
	
	public static Response getUserProfileDetails(String userId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.pathParam("userId", userId)
				.when()
				.get(Routes.getUserProfile)
				.then().spec(SpecBuilder.getResponseSpec())
				.assertThat().statusCode(200)
				.extract().response();
	}
	
	
	

}
