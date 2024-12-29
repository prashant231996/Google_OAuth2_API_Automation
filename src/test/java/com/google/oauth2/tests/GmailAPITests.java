package com.google.oauth2.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.oauth2.applicationApi.GmailApi;
import com.google.oauth2.utils.PropertyUtils;
import com.spotify.oauth2.pojo.UserProfile;

import io.restassured.response.Response;

public class GmailAPITests {
	
	@Test(priority=1,description="User should able to get user profile details")
	public void shouldBeAbleToGetProfileDetails() throws IOException
	{
		Response res=GmailApi.getUserProfileDetails(PropertyUtils.getPropertyValue("userId"));
		Assert.assertEquals(res.getStatusCode(), 200);
		UserProfile userProfile=res.as(UserProfile.class);
		//Asserting userId/email from response
		Assert.assertEquals(userProfile.getEmailAddress(), PropertyUtils.getPropertyValue("userId"));
	}

}
