package com.example.sampleapp.api;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.sampleapp.api.response.Ancestor;
import com.example.sampleapp.api.response.Error;
import com.example.sampleapp.api.response.factory.AncestorsFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class where all the API request are added for the request queue from here.
 * Json request - send the request with a json body
 * String request - urlencoded string request
 * Multipart request - send images/files to server over as multipart type
 * <p>
 * Created by Vihanga on 27/2/18.
 */
public class APIHelper {
	private static APIHelper instance;

	private APIHelper() {
	}

	/**
	 * APIHelper instance
	 *
	 * @return - APIHelper  instance
	 */
	public static APIHelper getInstance() {
		if (instance == null) instance = new APIHelper();
		return instance;
	}

	//	public void sendMultipartRequest(final PostManResponseListener context,
	//	                                 final AncestorsFactory factory,
	//	                                 int httpMethod, String apiUrl,
	//	                                 final Map<String, String> paramMap) {
	//
	//		VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(httpMethod, apiUrl,
	//				new Response.Listener<NetworkResponse>() {
	//					@Override
	//					public void onResponse(NetworkResponse response) {
	//						if (context != null) {
	//							try {
	//								String reStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
	//								context.onResponse(factory.parse(new JSONObject(reStr)));
	//							} catch (IOException e) {
	//								e.printStackTrace();
	//								context.onError(new Error("IOException", e.getLocalizedMessage(), false));
	//							} catch (JSONException e) {
	//								e.printStackTrace();
	//								context.onError(new Error("JSONException", e.getLocalizedMessage(), false));
	//							}
	//						}
	//					}
	//				},
	//				new Response.ErrorListener() {
	//					@Override
	//					public void onErrorResponse(VolleyError error) {
	//						if (context != null)
	//							context.onError(VolleySingleton.getInstance().getErrorMessage(error));
	//					}
	//				}) {
	//			@Override
	//			protected Map<String, String> getParams() {
	//				return paramMap;
	//			}
	//
	//			@Override
	//			protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
	//				Map<String, DataPart> params = new HashMap<>();
	//				// for now just get bitmap data from ImageView
	//				Random random = new Random();
	//				if (Constants.image != null) {
	//					ByteArrayOutputStream stream = new ByteArrayOutputStream();
	//					Constants.image.compress(Bitmap.CompressFormat.JPEG, 60, stream);
	//					byte[] byteArray = stream.toByteArray();
	//					params.put("file", new DataPart("cz_" + Math.abs(random.nextLong()) + ".jpg",
	//							byteArray, "image/jpeg"));
	//				}
	//
	//				return params;
	//			}
	//		};
	//
	//		VolleySingleton.getInstance().addToRequestQueue(multipartRequest);
	//
	//	}
	//
	//	public void sendMultipartProfileRequest(final PostManResponseListener context,
	//	                                        final AncestorsFactory factory,
	//	                                        int httpMethod, String apiUrl,
	//	                                        final Map<String, String> paramMap) {
	//
	//		VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(httpMethod, apiUrl,
	//				new Response.Listener<NetworkResponse>() {
	//					@Override
	//					public void onResponse(NetworkResponse response) {
	//						if (context != null) {
	//							try {
	//								String reStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
	//								context.onResponse(factory.parse(new JSONObject(reStr)));
	//							} catch (IOException e) {
	//								e.printStackTrace();
	//								context.onError(new Error("IOException", e.getLocalizedMessage(), false));
	//							} catch (JSONException e) {
	//								e.printStackTrace();
	//								context.onError(new Error("JSONException", e.getLocalizedMessage(), false));
	//							}
	//						}
	//					}
	//				},
	//				new Response.ErrorListener() {
	//					@Override
	//					public void onErrorResponse(VolleyError error) {
	//						if (context != null)
	//							context.onError(VolleySingleton.getInstance().getErrorMessage(error));
	//					}
	//				}) {
	//			@Override
	//			protected Map<String, String> getParams() {
	//				return paramMap;
	//			}
	//
	//			@Override
	//			protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
	//				Map<String, DataPart> params = new HashMap<>();
	//				// for now just get bitmap data from ImageView
	//				Random random = new Random();
	//				if (Constants.imagePrf != null) {
	//					ByteArrayOutputStream stream = new ByteArrayOutputStream();
	//					Constants.imagePrf.compress(Bitmap.CompressFormat.JPEG, 60, stream);
	//					byte[] byteArray = stream.toByteArray();
	//					params.put("file", new DataPart("prf_" + Math.abs(random.nextLong()) + ".jpg",
	//							byteArray, "image/jpeg"));
	//				}
	//
	//				return params;
	//			}
	//		};
	//
	//		VolleySingleton.getInstance().addToRequestQueue(multipartRequest);
	//
	//	}

	//	public void sendMultipartRequestPosts(final PostManResponseListener context,
	//	                                      final AncestorsFactory factory,
	//	                                      int httpMethod, String apiUrl,
	//	                                      final Map<String, String> paramMap) {
	//
	//		VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(httpMethod, apiUrl,
	//				new Response.Listener<NetworkResponse>() {
	//					@Override
	//					public void onResponse(NetworkResponse response) {
	//						if (context != null) {
	//							try {
	//								String reStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
	//								context.onResponse(factory.parse(new JSONObject(reStr)));
	//							} catch (IOException e) {
	//								e.printStackTrace();
	//								context.onError(new Error("IOException", e.getLocalizedMessage(), false));
	//							} catch (JSONException e) {
	//								e.printStackTrace();
	//								context.onError(new Error("JSONException", e.getLocalizedMessage(), false));
	//							}
	//						}
	//					}
	//				},
	//				new Response.ErrorListener() {
	//					@Override
	//					public void onErrorResponse(VolleyError error) {
	//						if (context != null)
	//							context.onError(VolleySingleton.getInstance().getErrorMessage(error));
	//					}
	//				}) {
	//			@Override
	//			protected Map<String, String> getParams() {
	//				return paramMap;
	//			}
	//
	//			@Override
	//			protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
	//				Map<String, DataPart> params = new HashMap<>();
	//				// for now just get bitmap data from ImageView
	//				Random random = new Random();
	//				for (int i = 0; i < Constants.bitmaps.size(); i++) {
	//					Bitmap bitmap = Constants.bitmaps.get(i);
	//					ByteArrayOutputStream stream = new ByteArrayOutputStream();
	//					bitmap.compress(Bitmap.CompressFormat.JPEG, 40, stream);
	//					byte[] byteArray = stream.toByteArray();
	//					params.put("image[" + i + "]", new DataPart("post_" + i + "_" + Math.abs(random.nextLong()) + ".jpg",
	//							byteArray, "image/jpeg"));
	//				}
	//				return params;
	//			}
	//		};
	//
	//		VolleySingleton.getInstance().addToRequestQueue(multipartRequest);

	//	}

	/**
	 * Send String type request with parameter and authentication
	 *
	 * @param context    - PostManResponseListener, null if you neglect the response
	 * @param factory    - Response factory type (must be extended from Ancestor)
	 * @param httpMethod - POST, GET, PATCH, PUT
	 * @param apiUrl     - API request URL
	 * @param paramMap   - request body parameter (key & value)
	 */
	public void sendStringRequestsWithParams(final PostManResponseListener context,
	                                         final AncestorsFactory factory,
	                                         int httpMethod, String apiUrl,
	                                         final Map<String, String> paramMap) {

		StringRequest request = new StringRequest(httpMethod, apiUrl, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				if (context != null) {
					try {
						context.onResponse(factory.parse(new JSONObject(response)));
					} catch (IOException e) {
						e.printStackTrace();
						context.onError(new Error("IOException", e.getLocalizedMessage(), false));
					} catch (JSONException e) {
						e.printStackTrace();
						context.onError(new Error("JSONException", e.getLocalizedMessage(), false));
					}

				}
			}

		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if (context != null)
					context.onError(VolleySingleton.getInstance().getErrorMessage(error));
			}
		}) {
			@Override
			protected Map<String, String> getParams() {

				return paramMap;
			}

			@Override
			public Map<String, String> getHeaders() {
				return VolleySingleton.getInstance().getAPIHeaderUrlEncoded();
			}
		};

		VolleySingleton.getInstance().addToRequestQueue(request);
	}

	/**
	 * Send a multipart request with only one attribute in the body (image).
	 * You cannot set the authentication for this request.
	 *
	 * @param context    - PostManResponseListener, null if you neglect the response
	 * @param factory    - Response factory type (must be extended from Ancestor)
	 * @param httpMethod - POST, GET, PATCH, PUT
	 * @param apiUrl     - API request URL
	 * @param paramMap   - body map
	 * @param bitmap     - image you want to send
	 * @param fileName   - name of the image file
	 */
	public void sendMultipartRequest(final PostManResponseListener context,
	                                 final AncestorsFactory factory,
	                                 int httpMethod, String apiUrl,
	                                 final Map<String, String> paramMap,
	                                 final byte[] bitmap, final String fileName) {

		VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(httpMethod, apiUrl,
				new Response.Listener<NetworkResponse>() {
					@Override
					public void onResponse(NetworkResponse response) {
						if (context != null) {
							try {
								String reStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
								context.onResponse(factory.parse(new JSONObject(reStr)));
							} catch (IOException e) {
								e.printStackTrace();
								context.onError(new Error("IOException", e.getLocalizedMessage(), false));
							} catch (JSONException e) {
								e.printStackTrace();
								context.onError(new Error("JSONException", e.getLocalizedMessage(), false));
							}
						}
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if (context != null)
							context.onError(VolleySingleton.getInstance().getErrorMessage(error));
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				return paramMap;
			}

			@Override
			protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
				Map<String, DataPart> params = new HashMap<>();
				params.put("image", new DataPart(fileName,
						bitmap, "image/jpeg"));
				return params;
			}
		};

		VolleySingleton.getInstance().addToRequestQueue(multipartRequest);

	}

	/**
	 * Send json request with authentication and parameters.
	 *
	 * @param context    - PostManResponseListener, null if you neglect the response
	 * @param factory    - Response factory type (must be extended from Ancestor)
	 * @param httpMethod - POST, GET, PATCH, PUT
	 * @param apiUrl     - API request URL
	 * @param jsonObject - request body as a json response
	 */
	public void sendJsonRequestsWithParams(final PostManResponseListener context,
	                                       final AncestorsFactory factory,
	                                       int httpMethod, String apiUrl,
	                                       final JSONObject jsonObject) {

		JsonRequest request = new JsonObjectRequest(httpMethod, apiUrl, jsonObject, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if (context != null) {
					try {
						context.onResponse(factory.parse(response));
					} catch (IOException e) {
						e.printStackTrace();
						context.onError(new Error("IOException", e.getLocalizedMessage(), false));
					}
				}
			}
		}
				, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if (context != null)
					context.onError(VolleySingleton.getInstance().getErrorMessage(error));
			}
		}) {
			@Override
			public Map<String, String> getHeaders() {
				return VolleySingleton.getInstance().getAPIHeaderJson();
			}
		};

		VolleySingleton.getInstance().addToRequestQueue(request);
	}


	//	public void sendAuthStringRequestsWithParams(final PostManResponseListener context,
	//	                                             final AncestorsFactory factory,
	//	                                             int httpMethod, String apiUrl,
	//	                                             final Map<String, String> paramMap) {
	//
	//		StringRequest request = new StringRequest(httpMethod, apiUrl, new Response.Listener<String>() {
	//			@Override
	//			public void onResponse(String response) {
	//				if (context != null) {
	//					try {
	//						context.onResponse(factory.parse(new JSONObject(response)));
	//					} catch (IOException e) {
	//						e.printStackTrace();
	//						context.onError(new Error("IOException", e.getLocalizedMessage(), false));
	//					} catch (JSONException e) {
	//						e.printStackTrace();
	//						context.onError(new Error("JSONException", e.getLocalizedMessage(), false));
	//					}
	//				}
	//			}
	//
	//		}, new Response.ErrorListener() {
	//			@Override
	//			public void onErrorResponse(VolleyError error) {
	//				if (context != null)
	//					context.onError(VolleySingleton.getInstance().getErrorMessage(error));
	//			}
	//		}) {
	//			@Override
	//			protected Map<String, String> getParams() {
	//				return paramMap;
	//			}
	//
	//			@Override
	//			public Map<String, String> getHeaders() {
	//				return VolleySingleton.getInstance().getAPIHeaderUrlEncodedWithAuth();
	//			}
	//		};
	//
	//		VolleySingleton.getInstance().addToRequestQueue(request);
	//	}


	/**
	 * This interface is used as a callback for every request type
	 */
	public interface PostManResponseListener {
		/**
		 * This is the callback you should call when ever you get a success response - 200
		 *
		 * @param ancestor - Factory type of response
		 */
		void onResponse(Ancestor ancestor);

		/**
		 * This is the call back you should call when ever you get some response other than 200.
		 * Or you should cal this in case of any error or exception occurs
		 *
		 * @param error - your error
		 */
		void onError(Error error);
	}
}