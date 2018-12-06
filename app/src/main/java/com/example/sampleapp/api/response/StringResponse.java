package com.example.sampleapp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to get String type responses
 * Created by vihanga on 3/20/17.
 */

public class StringResponse extends Ancestor<String> {
	StringResponse(@JsonProperty("message") String message,
	               @JsonProperty("data") String data,
	               @JsonProperty("status") boolean code) {
		super(message, code, data);
	}
}
