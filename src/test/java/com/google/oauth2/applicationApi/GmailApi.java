package com.google.oauth2.applicationApi;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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
	
	public static Response sendMessage(String userId, Map payloadData)
	{
		return given().spec(SpecBuilder.getRequestSpec())
				.auth().oauth2(TokenManager.getToken())
				.pathParam("userId", userId)
				.body(payloadData)
				.when()
				.post(Routes.sendEmail)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getMessage(String userId,String id)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.pathParam("userId", userId)
				.pathParam("id", id)
				.auth().oauth2(TokenManager.getToken())
				.when()
				.get(Routes.getMessage)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response deleteMessage(String userId,String id)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.pathParam("userId", userId)
				.pathParam("id", id)
				.auth().oauth2(TokenManager.getToken())
				.when()
				.get(Routes.deleteMessage)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response getListMessage(String userId)
	{
		return given()
				.spec(SpecBuilder.getRequestSpec())
				.pathParam("userId", userId)
				.auth().oauth2(TokenManager.getToken())
				.when()
				.get(Routes.getListOfMessages)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	
	
	

}
