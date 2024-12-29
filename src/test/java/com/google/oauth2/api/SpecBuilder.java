package com.google.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	
	
	public static RequestSpecification getRequestSpec()
	{
		return new RequestSpecBuilder()
				/*We are using system variable in below line so while executing cases run maven cammand  
				 * as mvn clean test -DBASE_URI="https://api.spotify.com" 
				 * So using system variables at run time we can change our base uri dynamically
				.setBaseUri(System.getProperty("BASE_URI"))*/
				.setBaseUri(Routes.baseUri)
				.setBasePath(Routes.BASE_PATH)
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();
	}
	
	public static ResponseSpecification getResponseSpec()
	{
		return new ResponseSpecBuilder()
				.log(LogDetail.ALL).build();
	}

}
