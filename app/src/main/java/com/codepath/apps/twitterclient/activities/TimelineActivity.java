package com.codepath.apps.twitterclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.TwitterApplication;
import com.codepath.apps.twitterclient.adapters.ViewPagerAdapter;
import com.codepath.apps.twitterclient.fragments.ComposeFragment;
import com.codepath.apps.twitterclient.models.Tweet;
import com.codepath.apps.twitterclient.page_transformers.ZoomOutPageTransformer;


public class TimelineActivity extends SherlockFragmentActivity implements
    ComposeFragment.OnItemSelectedListener {

  ComposeFragment composeFragment;

  ActionBar actionBar;
  ViewPager viewPager;
  ActionBar.Tab tab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_timeline);
    setupTabs();
  }

  private void setupTabs() {
    actionBar = getSupportActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    actionBar.setDisplayShowTitleEnabled(true);

    viewPager = (ViewPager) findViewById(R.id.vpTabs);

    // Activate Fragment Manager
    FragmentManager fm = getSupportFragmentManager();

    // Capture ViewPager page swipes
    ViewPager.SimpleOnPageChangeListener ViewPagerListener = new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        // Find the ViewPager Position
        actionBar.setSelectedNavigationItem(position);
      }

      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
      }
    };

    viewPager.setOnPageChangeListener(ViewPagerListener);
    // Locate the adapter class called ViewPagerAdapter.java
    ViewPagerAdapter viewpageradapter = new ViewPagerAdapter(fm);
    // Set the View Pager Adapter into ViewPager
    viewPager.setAdapter(viewpageradapter);

    viewPager.setPageTransformer(false, new ZoomOutPageTransformer());

    // Capture tab button clicks
    ActionBar.TabListener tabListener = new ActionBar.TabListener() {

      @Override
      public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // Pass the position on tab click to ViewPager
        viewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
      }

      @Override
      public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
      }
    };

    // Create first Tab
    tab = actionBar.newTab().setText("Home").setTabListener(tabListener);
    actionBar.addTab(tab);

    // Create second Tab
    tab = actionBar.newTab().setText("Mentions").setTabListener(tabListener);
    actionBar.addTab(tab);

  }

  @Override
  public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
    inflater.inflate(R.menu.timeline, menu);
    return true;
  }

  public void onClickCompose(com.actionbarsherlock.view.MenuItem item) {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    composeFragment = new ComposeFragment();

    ft.replace(R.id.fgCompose, composeFragment);
    ft.commit();
  }

  public void onClickProfile(com.actionbarsherlock.view.MenuItem item) {
    Intent profile_intent = new Intent(this, ProfileActivity.class);
    profile_intent.putExtra("user_uid", TwitterApplication.getLoginHelper().getUser().getUid());
    startActivity(profile_intent);
  }

//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    // Handle action bar item clicks here. The action bar will
//    // automatically handle clicks on the Home/Up button, so long
//    // as you specify a parent activity in AndroidManifest.xml.
//    int id = item.getItemId();
//
//    //noinspection SimplifiableIfStatement
//    if (id == R.id.action_settings) {
//        return true;
//    }
//
//    return super.onOptionsItemSelected(item);
//  }

  public void onTweetComposed(Tweet tweet) {
    // Add the newly created tweet
//    aTweets.insert(tweet, 0);
//    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//    ft.remove(composeFragment);
//    ft.commit();

  }
}
