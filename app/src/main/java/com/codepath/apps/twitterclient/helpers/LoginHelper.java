package com.codepath.apps.twitterclient.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.codepath.apps.twitterclient.TwitterApplication;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.models.User;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

/**
 * Created by marc on 10/5/14.
 */
public class LoginHelper {

  private static final String USER_PREFERENCES_KEY = "TwitterUser";

  private static LoginHelper instance = null;
  private User user;
  private boolean loggedIn;

  private LoginHelper() {}

  public static LoginHelper getInstance(){
    if(instance == null) {
      instance = new LoginHelper();
    }
    return instance;
  }

  public void fetchUserInfo(final JsonHttpResponseHandler handler) {
    TwitterClient client = TwitterApplication.getRestClient();
    client.getLoggedInUserInfo(new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(JSONObject jsonObject) {
        LoginHelper.this.setUser(User.fromJSON(jsonObject));
        handler.onSuccess(jsonObject);
      }

      @Override
      public void onFailure(Throwable throwable, JSONObject jsonObject) {
        throwable.printStackTrace();
        handler.onFailure(throwable, jsonObject);
      }
    });
  }

  public User getUser() {
    if (user == null) {
      SharedPreferences preferences = TwitterApplication.getContext().getSharedPreferences(TwitterApplication.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
      Gson gson = new Gson();
      String json = preferences.getString(USER_PREFERENCES_KEY, "");
      user = gson.fromJson(json, User.class);
    }

    if (user == null) {
      fetchUserInfo(new JsonHttpResponseHandler());
    }
    return user;
  }

  public void setUser(User user) {
    this.user = user;
    SharedPreferences preferences = TwitterApplication.getContext().getSharedPreferences(TwitterApplication.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    Gson gson = new Gson();
    String json = gson.toJson(user);
    editor.putString(USER_PREFERENCES_KEY, json);
    editor.commit();
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }

  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }
}
