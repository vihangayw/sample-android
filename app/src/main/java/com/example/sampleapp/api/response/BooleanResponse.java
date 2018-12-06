package com.example.sampleapp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Boolean responses (true/false/null)
 * Created by vihanga on 3/20/17.
 */

public class BooleanResponse extends Ancestor<Boolean> {
	BooleanResponse(@JsonProperty("message") String message,
	                @JsonProperty("data") Boolean data,
	                @JsonProperty("status") boolean code) {
		super(message, code, data);
	}
}
