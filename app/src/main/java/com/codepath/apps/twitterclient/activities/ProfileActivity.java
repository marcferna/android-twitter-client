package com.codepath.apps.twitterclient.activities;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.TwitterApplication;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.fragments.profile.UserHeaderFragment;
import com.codepath.apps.twitterclient.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

public class ProfileActivity extends SherlockFragmentActivity {

  private TwitterClient client;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    loadProfileTimeline();
  }

  public void loadProfileTimeline() {
    client = TwitterApplication.getRestClient();
    client.getUserInfo(new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(JSONObject jsonObject) {
        User user = User.fromJSON(jsonObject);
        UserHeaderFragment headerFragment = (UserHeaderFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentUserHeader);
        headerFragment.loadUserInfo(user);
      }
    });
  }
}
