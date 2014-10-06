package com.codepath.apps.twitterclient.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codepath.apps.twitterclient.fragments.timeline.HomeTimelineFragment;
import com.codepath.apps.twitterclient.fragments.timeline.MentionsTimelineFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

  // Declare the number of ViewPager pages
  final int PAGE_COUNT = 2;

  public ViewPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int arg0) {
    switch (arg0) {

      // Open FragmentTab1.java
      case 0:
        HomeTimelineFragment fragmenttab1 = new HomeTimelineFragment();
        return fragmenttab1;

      // Open FragmentTab2.java
      case 1:
        MentionsTimelineFragment fragmenttab2 = new MentionsTimelineFragment();
        return fragmenttab2;
    }
    return null;
  }

  @Override
  public int getCount() {
    return PAGE_COUNT;
  }

}
