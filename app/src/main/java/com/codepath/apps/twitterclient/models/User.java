package com.codepath.apps.twitterclient.models;

import com.codepath.apps.twitterclient.TwitterApplication;
import com.codepath.apps.twitterclient.helpers.UserHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marc on 9/25/14.
 */
public class User {

  @SerializedName("name")
  private String name;
  @SerializedName("uid")
  private long uid;
  @SerializedName("screenName")
  private String screenName;
  @SerializedName("profileImageUrl")
  private String profileImageUrl;
  @SerializedName("tagline")
  private String tagline;
  @SerializedName("friendsCount")
  private int friendsCount;
  @SerializedName("followersCount")
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

  public static void getUser(long uid, final UserHandler handler) {
    User user = TwitterApplication.getLoginHelper().getUser();

    // Check if the user requested is the logged in user
    if (user.getUid() == uid) {
      handler.onSuccess(user);
      return;
    }

    // Get the information for the none logged in user
    TwitterApplication.getRestClient().getUserInfo(uid, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(JSONObject jsonObject) {
        super.onSuccess(jsonObject);
        handler.onSuccess(User.fromJSON(jsonObject));
      }
    });
  }
}
