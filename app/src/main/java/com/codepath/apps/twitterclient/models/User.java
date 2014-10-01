package com.codepath.apps.twitterclient.models;

import android.provider.BaseColumns;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by marc on 9/25/14.
 */

@Table(name = "Users", id = BaseColumns._ID)
public class User extends Model {
  @Column(name = "uid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
  private long uid;

  @Column(name = "name")
  private String name;

  @Column(name = "screen_name")
  private String screenName;

  @Column(name = "profile_image_url")
  private String profileImageUrl;

  public User() {
    super();
  }

  public User(long uid, String name, String screenName, String profileImageUrl) {
    this.uid = uid;
    this.name = name;
    this.screenName = screenName;
    this.profileImageUrl = profileImageUrl;
  }



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
    user.save();
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

  public List<Tweet> tweets() {
    return getMany(Tweet.class, "Tweet");
  }

}
