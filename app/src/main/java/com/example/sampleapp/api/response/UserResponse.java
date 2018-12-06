package com.example.sampleapp.api.response;

import com.example.sampleapp.modal.User;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vihanga on 3/20/17.
 */

public class UserResponse extends Ancestor<User> {
	UserResponse(@JsonProperty("message") String message,
	             @JsonProperty("data") User data,
	             @JsonProperty("status") boolean code) {
		super(message, code, data);
	}
}
