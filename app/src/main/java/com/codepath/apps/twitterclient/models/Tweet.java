package com.codepath.apps.twitterclient.models;

import android.provider.BaseColumns;
import android.text.format.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


/**
 * Created by marc on 9/25/14.
 */
@Table(name = "Tweets", id = BaseColumns._ID)
public class Tweet extends Model {
  @Column(name = "uid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
  private long uid;

  @Column(name = "body")
  private String body;

  @Column(name = "created_at")
  private String createdAt;

  @Column(name = "user")
  private User user;

  public Tweet() {
    super();
  }

  public Tweet(long uid, String body, String createdAt, User user) {
    this.uid = uid;
    this.body = body;
    this.createdAt = createdAt;
    this.user = user;
  }

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
        tweet.save();
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

  public String getRelativeCreatedAt() {
    return this.getRelativeTimeAgo(this.getCreatedAt());
  }

  private String getRelativeTimeAgo(String rawJsonDate) {
    String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
    SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
    sf.setLenient(true);

    String relativeDate = "";
    try {
      long dateMillis = sf.parse(rawJsonDate).getTime();
      relativeDate = DateUtils.getRelativeTimeSpanString(
        dateMillis,
        System.currentTimeMillis(),
        DateUtils.SECOND_IN_MILLIS
      ).toString();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return relativeDate;
  }
}
