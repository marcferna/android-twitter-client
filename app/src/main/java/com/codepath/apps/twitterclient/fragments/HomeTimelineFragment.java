package com.codepath.apps.twitterclient.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitterclient.TwitterApplication;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

/**
 * Created by marc on 10/2/14.
 */
public class HomeTimelineFragment extends TweetsListFragment {
  private TwitterClient client;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    client = TwitterApplication.getRestClient();
    populateTimeline();
  }

  public void populateTimeline() {
    this.populateTimeline(0);
  }
  public void populateTimeline(long maxId) {
    client.getHomeTimeline(maxId, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(JSONArray jsonArray) {
        addAll(Tweet.fromJSONArray(jsonArray));
      }

      @Override
      public void onFailure(Throwable throwable, String s) {
        Log.i("DEBUG", throwable.toString());
        Log.i("DEBUG", s.toString());
      }
    });
  }
}
