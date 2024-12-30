package com.google.oauth2.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.oauth2.applicationApi.GmailApi;
import com.google.oauth2.pojo.UserProfile;
import com.google.oauth2.utils.PropertyUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GmailAPITests {
	
	public static String idValue="";
	
	@Test(priority=1,description="User should able to get user profile details")
	public void shouldBeAbleToGetProfileDetails() throws IOException
	{
		Response res=GmailApi.getUserProfileDetails(PropertyUtils.getPropertyValue("userId"));
		Assert.assertEquals(res.getStatusCode(), 200);
		UserProfile userProfile=res.as(UserProfile.class);
		//Asserting userId/email from response
		Assert.assertEquals(userProfile.getEmailAddress(), PropertyUtils.getPropertyValue("userId"));
	}
	
	@Test(priority=2,description="User should able to send the email",enabled=true)
	public void shouldBeAbleToSendEmail() throws IOException
	{
		String base64EmailContent="RnJvbTogbW9yZXByMjNAZ21haWwuY29tClRvOiBtb3JlcHIyM0BnbWFpbC5jb20KU3ViamVjdDogVGVzdCBFbWFpbAoKU2VuZGluZyBmcm9tIEdtYWlsIEFQSQ==";
		Map<String,Object>mapData=new HashMap<String,Object>();
		mapData.put("raw",base64EmailContent);
		Response res=GmailApi.sendMessage(PropertyUtils.getPropertyValue("userId"), mapData);
		Assert.assertEquals(res.getStatusCode(),200);
		//Verifying response contains SENT word in it
		Assert.assertTrue(res.asString().contains("SENT"));
		//Getting Id value for creted message
		JsonPath js=new JsonPath(res.asString());
		idValue=js.get("id").toString();
	}
	
	@Test(priority=3, description="User should able to get message")
	public void shouldBeAbleToGetMessage() throws IOException
	{
		Response res=GmailApi.getMessage(PropertyUtils.getPropertyValue("userId"), idValue);
		Assert.assertEquals(res.getStatusCode(), 200);
		//Verifying user mail address from response
		Assert.assertTrue(res.asString().contains(PropertyUtils.getPropertyValue("userId")));
	}
	
	@Test(priority=4,description="User should able to get list of all messages")
	public void getListOfAllMessage() throws IOException
	{
		Response res=GmailApi.getListMessage(PropertyUtils.getPropertyValue("userId"));
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	
	@Test(priority=5, description="User Should able to delete message")
	public void shouldBeABleToDeleteMessage() throws IOException
	{
		Response res=GmailApi.deleteMessage(PropertyUtils.getPropertyValue("userId"), idValue);
		//Asserting response code it should be 204
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority=6,description="Trying to delete alredy deleted content in this case will get 204(NO CONTENT)",enabled=false)
	public void shouldBeABleToDeleteMessageAgain() throws IOException
	{
		Response res=GmailApi.deleteMessage(PropertyUtils.getPropertyValue("userId"), idValue);
		//Asserting response code it should be 204 ast it is already deleted
		Assert.assertEquals(res.getStatusCode(), 204);
	}

}
