package com.example.sampleapp.api;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.sampleapp.ExampleApplication;
import com.example.sampleapp.api.response.Error;
import com.example.sampleapp.util.UserSessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Volley - Using for Transmitting data over network
 * <p/>
 * Volley is designed to queue all your requests.
 * It wouldn't make sense to have more than one queue,
 * and that's why it's a singleton.
 * <p/>
 * Created by Vihanga on 27/2/18.
 */
public class VolleySingleton {

	public static final boolean TIME_OUT = false;
	public static final boolean NO_NETWORK = true;
	public static final boolean PARSE_ERROR = false;
	private static final String TAG = VolleySingleton.class.getSimpleName();
	private static VolleySingleton mInstance;
	private RequestQueue mRequestQueue;

	private VolleySingleton() {
		mRequestQueue = Volley.newRequestQueue(ExampleApplication.getInstance());
	}

	/**
	 * Singleton class for VolleySingleton
	 *
	 * @return volley singleton object
	 */
	public static synchronized VolleySingleton getInstance() {
		if (mInstance == null) {
			mInstance = new VolleySingleton();
		}
		return mInstance;
	}

	public void cancelAll(Object o) {
		mRequestQueue.cancelAll(o);
	}

	/**
	 * Get the Volley Request Queue
	 *
	 * @return Request Queue
	 */
	private RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(ExampleApplication.getInstance());
		}
		return mRequestQueue;
	}

	/**
	 * Adding API request to Volley
	 *
	 * @param req request
	 * @param <T> getting the request in queue
	 */
	public <T> void addToRequestQueue(Request<T> req) {
		//getRequestQueue().getCache().clear();
		if (/*CommonUtils.isInternetAvailable()*/true) {
			req.setRetryPolicy(new DefaultRetryPolicy(
					30 * 1000, 0,
					DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
			req.setTag(TAG);

			getRequestQueue().add(req);
		}
	}

	/**
	 * Getting the ErrorResponse description from VolleyError
	 *
	 * @param error volley error
	 * @return ErrorResponse message
	 */
	public Error getErrorMessage(VolleyError error) {
		//        Log.v("Data :" , new String(error.networkResponse.data));
		if (error != null) {
			if (error instanceof com.android.volley.TimeoutError) {
				String code = "Error response";
				String message = "Network Timeout";
				return new Error(code, error.getMessage(), TIME_OUT);
			} else if (error instanceof com.android.volley.NoConnectionError) {
				String code = "No network";
				String message = "Please Check Your Internet Connection";
				return new Error(code, error.getMessage(), NO_NETWORK);
			} else if (error instanceof com.android.volley.ParseError) {
				String code = "Parse error response";
				String message = "Can't Proceed This Task";
				return new Error(code, error.getMessage(), PARSE_ERROR);
			} else {
				if (error.networkResponse != null && error.networkResponse.data != null) {
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()));
					try {
						Error errorObj = objectMapper.readValue(error.networkResponse.data, Error.class);
						if (error.networkResponse.statusCode == 401) {
							UserSessionManager.getInstance().clearPref();
							ExampleApplication.getInstance().restartApplication();
						}
						return errorObj;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return new Error(String.valueOf(error.networkResponse != null ? error.networkResponse.statusCode : 500),
						new String(error.networkResponse != null ? error.networkResponse.data != null ? error.networkResponse.data
								: String.valueOf("Couldn't proceed this request").getBytes() : String.valueOf("Couldn't proceed this request").getBytes()), false);
			}

		} else {
			return null;
		}
	}


	/**
	 * Prepare API Header with x-www-form-urlencoded
	 *
	 * @return headers
	 */
	public HashMap<String, String> getAPIHeaderUrlEncoded() {
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		headers.put("auth-token", "202cb962ac59075b964b07152d234b70");
		// headers.put("Content-Encoding", "gzip");
		return headers;
	}

	/**
	 * Prepare API Header with UserAuth Token
	 *
	 * @return headers
	 */
	public HashMap<String, String> getAPIHeaderJson() {
		HashMap<String, String> headers = new HashMap<>();
		//		headers.put("Content-Type", "application/json; charset=utf-8");
		headers.put("auth-token", UserSessionManager.getInstance().getToken());//"6b13f0f911232010d2e12831aa1fd981");//UserSessionManager.getInstance().getToken()); //// TODO: 10/31/18
		return headers;
	}

	/**
	 * Prepare API Header with UserAuth Token
	 *
	 * @return headers
	 */
	public HashMap<String, String> getAPIHeaderText() {
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "text/plain; charset=utf-8");
		// headers.put("Content-Encoding", "gzip");
		return headers;
	}

	/**
	 * Prepare API Header with x-www-form-urlencoded with token
	 *
	 * @return headers with token
	 */
	public HashMap<String, String> getAPIHeaderUrlEncodedWithAuth() {
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		headers.put("auth-token", "202cb962ac59075b964b07152d234b70");
		// headers.put("Content-Encoding", "gzip");
		return headers;
	}
}
