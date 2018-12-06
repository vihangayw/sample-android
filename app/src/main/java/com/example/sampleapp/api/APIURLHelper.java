package com.example.sampleapp.api;


/**
 * This class is used to format and return all the API URLs.
 * Any URL API/google/facebook should be implemented in this helper class as a constant
 */
public class APIURLHelper {

	private static final String BASE_URL = "http://dev.sample.com";
	private static final String API = "/example/public/api";
	private static final String LANGUAGE = "/language";
	private static final String USER = "/user";
	private static final String PAGE = "/page";
	private static final String ITEMS = "/items";

	private static final String PROFILE_IMAGE = "/uploads/profile/";
	private static final String LOGIN = "/login";

	/**
	 * This method return the login URL
	 *
	 * @return - formatted login url
	 */
	public static String login() {
		return BASE_URL + API.concat(LOGIN);
	}


	public static String uploadProfileImage() {
		return null;
	}
}