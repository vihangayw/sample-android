package com.example.sampleapp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * THis is string array response type
 * Created by vihanga on 3/20/17.
 */

public class StringListResponse extends Ancestor<List<String>> {
	StringListResponse(@JsonProperty("message") String message,
	                   @JsonProperty("data") List<String> data,
	                   @JsonProperty("status") boolean code) {
		super(message, code, data);
	}
}
