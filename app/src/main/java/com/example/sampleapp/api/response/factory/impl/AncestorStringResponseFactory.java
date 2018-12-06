package com.example.sampleapp.api.response.factory.impl;


import com.example.sampleapp.api.response.Ancestor;
import com.example.sampleapp.api.response.StringResponse;
import com.example.sampleapp.api.response.factory.AncestorsFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by vihanga on 3/20/17.
 */

public class AncestorStringResponseFactory implements AncestorsFactory {
	@Override
	public Ancestor parse(JSONObject response) throws IOException {
		return new ObjectMapper().readValue(response.toString(), StringResponse.class);
	}

}
