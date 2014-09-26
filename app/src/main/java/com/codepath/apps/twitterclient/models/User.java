package com.codepath.apps.twitterclient.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marc on 9/25/14.
 */
public class User {
  private String name;
  private long uid;
  private String screenName;
  private String profileImageUrl;

  public static User fromJSON(JSONObject jsonObject) {
    User user = new User();
    try {
      user.name = jsonObject.getString("name");
      user.uid = jsonObject.getLong("id");
      user.screenName = jsonObject.getString("screen_name");
      user.profileImageUrl = jsonObject.getString("profile_image_url");
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return user;
  }

  public String getName() {
    return name;
  }

  public long getUid() {
    return uid;
  }

  public String getScreenName() {
    return screenName;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

}
