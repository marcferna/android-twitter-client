package com.codepath.apps.twitterclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.fragments.profile.UserHeaderFragment;
import com.codepath.apps.twitterclient.fragments.profile.UserTimelineFragment;

public class ProfileActivity extends SherlockFragmentActivity {

  private UserHeaderFragment headerFragment;
  private UserTimelineFragment timelineFragment;
  private long uid;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    Intent intent = getIntent();
    uid = intent.getLongExtra("user_uid", 0);
    setupFragments();
  }

  public void setupFragments() {
    headerFragment = UserHeaderFragment.newInstance(uid);
    timelineFragment = UserTimelineFragment.newInstance(uid);

    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.add(R.id.fragmentUserHeader, headerFragment);
    ft.add(R.id.fragmentUserTimeline, timelineFragment);
    ft.commit();
  }
}
