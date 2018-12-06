package com.example.sampleapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sampleapp.BuildConfig;
import com.example.sampleapp.ExampleApplication;

/**
 * Created by vihanga on 5/11/18 in ion-app.
 * Save and manage all shared preferences of the app.
 */
public class UserSessionManager {

	private static final String PREF_NAME = BuildConfig.APPLICATION_ID + ".pref";
	private static final String KEY_USER = "User";
	private static final String KEY_USER_ID = "User_ID";
	private static final String KEY_LANGUAGE = "Lang";
	private static final String KEY_IS_LOGIN = "IsLogin";
	private static final String KEY_TOKEN = "AuthToken";


	private final static UserSessionManager instance =
			new UserSessionManager(ExampleApplication.getInstance().getApplicationContext());

	private final SharedPreferences pref;
	private final SharedPreferences.Editor editor;

	private UserSessionManager(Context context) {
		int PRIVATE_MODE = 0;
		pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public static UserSessionManager getInstance() {
		return instance;
	}

	public void createUser(String json, int uid) {
		editor.putString(KEY_USER, json);
		editor.putInt(KEY_USER_ID, uid);
		editor.putBoolean(KEY_IS_LOGIN, true);
		editor.commit();
	}

	public void createAuthToken(String token) {
		editor.putString(KEY_TOKEN, token);
		editor.commit();
	}

	public void clearPref() {
		editor.clear();
		editor.commit();
	}

//	public User getUser() {
//		String modulePref = getUserPref();
//		if (!TextUtils.isEmpty(modulePref)) {
//			try {
//				return new ObjectMapper().readValue(modulePref, User.class);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}

	private String getUserPref() {
		return pref.getString(KEY_USER, null);
	}

	public String getToken() {
		return pref.getString(KEY_TOKEN, "");
	}

	public int getUID() {
		return pref.getInt(KEY_USER_ID, 0);
	}

	public int getLanguage() {
		return pref.getInt(KEY_LANGUAGE, Constants.LANG_EN);
	}

	public boolean isLogin() {
		return pref.getBoolean(KEY_IS_LOGIN, false);
	}

}
