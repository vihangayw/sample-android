package com.example.sampleapp.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Generic type class that serialize or de-serialize any json request that goes/received by the app
 * Created by Vihanga on 27/2/18.
 */

public class Ancestor<T> {

	private String message;
	private boolean status;
	private T data;

	@JsonCreator
	protected Ancestor(@JsonProperty("message") String message,
	                   @JsonProperty("status") boolean status,
	                   @JsonProperty("data") T data) {
		this.message = message;
		this.data = data;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public boolean getStatus() {
		return status;
	}
}