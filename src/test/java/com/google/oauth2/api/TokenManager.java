package com.google.oauth2.api;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import com.google.oauth2.utils.PropertyUtils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiryTime;
	
	public synchronized static String getToken()
	{
		try
		{
			if(access_token == null || Instant.now().isAfter(expiryTime))
			{
				System.out.println("RENEWING TOKEN...");
				Response res=renewToken();
				JsonPath js=new JsonPath(res.asString());
				access_token = js.get("access_token").toString();
				int expiryDurationInSeconds=js.get("expires_in");
				//Taking buffer of 5 minutes so we are excluding 300 seconds(5 MIN) form expiry time.
				expiryTime=Instant.now().plusSeconds(expiryDurationInSeconds - 300);
			}
			else
			{
				System.out.println("Token is good to use..");
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException("!!!ABORT FAILED TO RENEW TOKEN!!!");
		}
		return access_token;
	}
	
	public static Response renewToken() throws IOException
	{
		HashMap<String,String>formParam=new HashMap<String,String>();
		formParam.put("grant_type", PropertyUtils.getPropertyValue("grant_type"));
		formParam.put("refresh_token", PropertyUtils.getPropertyValue("refresh_token"));
		formParam.put("client_id",PropertyUtils.getPropertyValue("client_id"));
		formParam.put("client_secret",PropertyUtils.getPropertyValue("client_secret"));
		
		return given()
				.baseUri("https://oauth2.googleapis.com")
				.contentType(ContentType.URLENC)
				.formParams(formParam)
				.when()
				.post(Routes.renewToken)
				.then().spec(SpecBuilder.getResponseSpec())
				.assertThat().statusCode(200)
				.extract().response();
	}

}
