package com.example.sampleapp.api.response.factory;


import com.example.sampleapp.api.response.Ancestor;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Base Factory class for Ancestor, all the response classes should be implemented from the
 * AncestorsFactory
 * <p>
 * Created by vihanga on 3/20/17.
 */

public interface AncestorsFactory {
	Ancestor parse(JSONObject response) throws IOException;
}
