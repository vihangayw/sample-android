package com.example.sampleapp.api.request.helper.impl;

import com.android.volley.Request;
import com.example.sampleapp.api.APIHelper;
import com.example.sampleapp.api.APIURLHelper;
import com.example.sampleapp.api.request.helper.UserRequestHelper;
import com.example.sampleapp.api.response.factory.impl.AncestorStringResponseFactory;
import com.example.sampleapp.api.response.factory.impl.AncestorUserResponseFactory;
import com.example.sampleapp.util.JsonService;

import org.json.JSONException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class UserRequestHelperImpl implements UserRequestHelper {

	@Override
	public void uploadImage(String fileName, byte[] bitmap, APIHelper.PostManResponseListener context) {
		Map<String, String> params = new HashMap<>();
		APIHelper.getInstance().sendMultipartRequest(context, new AncestorStringResponseFactory(),
				Request.Method.POST, APIURLHelper.uploadProfileImage(), params, bitmap, fileName);
	}

	@Override
	public void login(String email, String pw, APIHelper.PostManResponseListener listener) throws JSONException {
		APIHelper.getInstance().sendJsonRequestsWithParams(listener, new AncestorUserResponseFactory(),
				Request.Method.POST, APIURLHelper.login(), JsonService.toJsonNode(new Login(email, pw)));
	}

	class Login implements Serializable {
		private String email;
		private String password;

		public Login() {
		}

		public Login(String email, String password) {
			this.email = email;
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
