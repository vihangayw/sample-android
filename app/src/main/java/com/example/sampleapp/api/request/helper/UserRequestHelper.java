package com.example.sampleapp.api.request.helper;


import com.example.sampleapp.api.APIHelper;

import org.json.JSONException;

/**
 * This class is used to perform all the user related API calls including login and register
 * Created by Vihanga on 3/8/2018.
 */

public interface UserRequestHelper {
	/**
	 * This method is used to upload the profile image of the user
	 *
	 * @param fileName name of the selected file
	 * @param bitmap   selected image as a bitmap
	 * @param context  Postman listener
	 */
	void uploadImage(String fileName, byte[] bitmap, APIHelper.PostManResponseListener context);

	/**
	 * This method is used to login a user with email and password
	 *
	 * @param email    email
	 * @param pw       password
	 * @param listener Postman listener
	 * @throws JSONException
	 */
	void login(String email, String pw, APIHelper.PostManResponseListener listener) throws JSONException;

}
