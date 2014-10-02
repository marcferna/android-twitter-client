package com.codepath.apps.twitterclient.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.TweetArrayAdapter;

import com.codepath.apps.twitterclient.models.Tweet;


import java.util.ArrayList;


public class TweetsListFragment extends SherlockFragment {

  private ArrayList<Tweet> tweets;
  private TweetArrayAdapter aTweets;
  private ListView lvTweets;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    tweets = new ArrayList<Tweet>();
    aTweets = new TweetArrayAdapter(getActivity(), tweets);
    aTweets.notifyDataSetChanged();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v =  inflater.inflate(R.layout.fragment_tweets_list, container, false);

    lvTweets = (ListView) v.findViewById(R.id.lvTweets);
    lvTweets.setAdapter(aTweets);
//    lvTweets.setOnScrollListener(new EndlessScrollListener() {
//      @Override
//      public void onLoadMore(int page, int totalItemsCount) {
//        Tweet lastTweet = tweets.get(tweets.size() - 1);
//        TweetsListFragment.this.populateTimeline(lastTweet.getUid());
//      }
//    });

    return v;
  }

  public void addAll(ArrayList<Tweet> tweets) {
    aTweets.addAll(tweets);
  }

}
