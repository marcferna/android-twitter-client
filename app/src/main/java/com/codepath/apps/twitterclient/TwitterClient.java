package com.codepath.apps.twitterclient;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "NPi6bwzbNa2VvSTXdbl6ipEN9";       // Change this
	public static final String REST_CONSUMER_SECRET = "2l7ohL0wY74qQIY8vpdNZtXTDC95cEt0o6yyYgxmCsPJIGDoax"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://oauth_callback"; // Change this (here and in manifest)

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

  public void getHomeTimeline(AsyncHttpResponseHandler handler) {
    getHomeTimeline(0, handler);
  }
	public void getHomeTimeline(long maxId, AsyncHttpResponseHandler handler) {
    String apiUrl = getApiUrl("statuses/home_timeline.json");
    RequestParams params = null;
    if (maxId != 0) {
      params = new RequestParams();
      params.put("max_id", Long.toString(maxId));
    }
    client.get(apiUrl, params, handler);
  }

  public void getMentions(AsyncHttpResponseHandler handler) {
    getHomeTimeline(0, handler);
  }
  public void getMentions(long maxId, AsyncHttpResponseHandler handler) {
    String apiUrl = getApiUrl("statuses/mentions_timeline.json");
    RequestParams params = null;
    if (maxId != 0) {
      params = new RequestParams();
      params.put("max_id", Long.toString(maxId));
    }
    client.get(apiUrl, params, handler);
  }

  public void postStatuses(String status, AsyncHttpResponseHandler handler) {
    String apiUrl = getApiUrl("statuses/update.json");
    RequestParams params = new RequestParams();
    params.put("status", status);
    client.post(apiUrl, params, handler);
  }

  public void getLoggedInUserInfo(AsyncHttpResponseHandler handler) {
    String apiUrl = getApiUrl("account/verify_credentials.json");
    client.get(apiUrl, null, handler);
  }

  public void getUserInfo(long uid, AsyncHttpResponseHandler handler) {
    String apiUrl = getApiUrl("users/show.json");
    RequestParams params = new RequestParams();
    params.put("user_id", String.valueOf(uid));
    client.get(apiUrl, params, handler);
  }

  public void getUserTimeline(long uid, AsyncHttpResponseHandler handler) {
    String apiUrl = getApiUrl("statuses/user_timeline.json");
    RequestParams params = new RequestParams();
    params.put("user_id", String.valueOf(uid));
    client.get(apiUrl, params, handler);
  }
}