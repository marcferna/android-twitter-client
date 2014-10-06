package com.codepath.apps.twitterclient.fragments.profile;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitterclient.fragments.TweetsListFragment;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

/**
 * Created by marc on 10/5/14.
 */
public class UserTimelineFragment extends TweetsListFragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected void populateWithTweets(long maxId) {
    client.getUserTimeline(new JsonHttpResponseHandler() {
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
