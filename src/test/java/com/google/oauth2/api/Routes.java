package com.google.oauth2.api;

public class Routes {
	
	public static final String baseUri="https://gmail.googleapis.com/gmail";
	public static final String BASE_PATH="/v1";
	public static final String getUserProfile="/users/{userId}/profile";
	public static final String sendEmail="/users/{userId}/messages/send";
	public static final String renewToken="/token";
	public static final String getMessage="/users/{userId}/messages/{id}";
	public static final String deleteMessage="/users/{userId}/messages/{id}";
	public static final String getListOfMessages="/users/{userId}/messages";
	
}
