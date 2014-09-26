package com.codepath.apps.twitterclient.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by marc on 9/25/14.
 */
public class Tweet {
  private String body;
  private long uid;
  private String createdAt;
  private User user;

  public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
    ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    for (int i = 0; i < jsonArray.length(); i++) {
      JSONObject tweetJSON = null;
      try {
        tweetJSON = jsonArray.getJSONObject(i);
      } catch (JSONException e) {
        e.printStackTrace();
        continue;
      }

      Tweet tweet = Tweet.fromJSON(tweetJSON);
      if (tweetJSON != null) {
        tweets.add(tweet);
      }
    }
    return tweets;
  }

  public static Tweet fromJSON(JSONObject jsonObject) {
    Tweet tweet = new Tweet();
    try {
      tweet.body = jsonObject.getString("text");
      tweet.uid = jsonObject.getLong("id");
      tweet.createdAt = jsonObject.getString("created_at");
      tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return tweet;
  }

  public String getBody() {
    return body;
  }

  public long getUid() {
    return uid;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public User getUser() {
    return user;
  }

  @Override
  public String toString() {
    return getBody();
  }
}
