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
  private String tagline;
  private int friendsCount;
  private int followersCount;

  public static User fromJSON(JSONObject jsonObject) {
    User user = new User();
    try {
      user.name = jsonObject.getString("name");
      user.uid = jsonObject.getLong("id");
      user.screenName = jsonObject.getString("screen_name");
      user.profileImageUrl = jsonObject.getString("profile_image_url");
      user.tagline = jsonObject.getString("description");
      user.friendsCount = jsonObject.getInt("friends_count");
      user.followersCount = jsonObject.getInt("followers_count");

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

  public String getTagline() {
    return tagline;
  }

  public int getFriendsCount() {
    return friendsCount;
  }

  public int getFollowersCount() {
    return followersCount;
  }

}
