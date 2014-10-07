package com.codepath.apps.twitterclient.fragments.profile;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitterclient.fragments.TweetsListFragment;
import com.codepath.apps.twitterclient.helpers.UserHandler;
import com.codepath.apps.twitterclient.models.Tweet;
import com.codepath.apps.twitterclient.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

/**
 * Created by marc on 10/5/14.
 */
public class UserTimelineFragment extends TweetsListFragment {

  private long uid;

  public static final UserTimelineFragment newInstance(long uid) {
    UserTimelineFragment fragment = new UserTimelineFragment();
    Bundle bundle = new Bundle(1);
    bundle.putLong("user_uid", uid);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    uid = getArguments().getLong("user_uid");
    super.onCreate(savedInstanceState);
  }

  @Override
  protected void populateWithTweets(long maxId) {
    client.getUserTimeline(uid, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(JSONArray jsonArray) {
        addAll(Tweet.fromJSONArray(jsonArray));
      }

      @Override
      public void onFailure(Throwable throwable, String s) {
        Log.i("DEBUG", throwable.toString());
        Log.i("DEBUG", s.toString());
      }

      @Override
      protected void handleFailureMessage(Throwable throwable, String s) {
        super.handleFailureMessage(throwable, s);
        Log.i("DEBUG", throwable.toString());
        Log.i("DEBUG", s.toString());
      }
    });
  }
}
