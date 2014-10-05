package com.codepath.apps.twitterclient.fragments.profile;

import android.os.Bundle;

import com.codepath.apps.twitterclient.TwitterApplication;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.fragments.TweetsListFragment;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

/**
 * Created by marc on 10/5/14.
 */
public class UserTimelineFragment extends TweetsListFragment {

  private TwitterClient client;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    client = TwitterApplication.getRestClient();
    client.getUserTimeline(new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(JSONArray jsonArray) {
        addAll(Tweet.fromJSONArray(jsonArray));
      }
    });
  }
}
