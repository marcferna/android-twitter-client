package com.codepath.apps.twitterclient.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.codepath.apps.twitterclient.EndlessScrollListener;
import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.adapters.TweetArrayAdapter;

import com.codepath.apps.twitterclient.TwitterApplication;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.models.Tweet;


import java.util.ArrayList;


public abstract class TweetsListFragment extends SherlockFragment {

  private ArrayList<Tweet> tweets;
  private TweetArrayAdapter aTweets;
  private ListView lvTweets;
  protected TwitterClient client;

  private OnClickListener onClickListener;

  public interface OnClickListener {
    public void onProfileImageClicked(long uid);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    tweets = new ArrayList<Tweet>();
    aTweets = new TweetArrayAdapter(getActivity(), tweets, new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (onClickListener != null) {
          onClickListener.onProfileImageClicked(Long.parseLong(v.getTag().toString()));
        }
      }
    });


    aTweets.notifyDataSetChanged();
    client = TwitterApplication.getRestClient();
    populateWithTweets();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v =  inflater.inflate(R.layout.fragment_tweets_list, container, false);

    lvTweets = (ListView) v.findViewById(R.id.lvTweets);
    lvTweets.setAdapter(aTweets);
    lvTweets.setOnScrollListener(new EndlessScrollListener() {
      @Override
      public void onLoadMore(int page, int totalItemsCount) {
        Tweet lastTweet = tweets.get(tweets.size() - 1);
        TweetsListFragment.this.populateWithTweets(lastTweet.getUid());
      }
    });
    return v;
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof OnClickListener) {
      onClickListener = (OnClickListener) activity;
    }
  }

  protected void populateWithTweets () {
    populateWithTweets(0);
  }
  protected abstract void populateWithTweets(long maxId);

  public void addAll(ArrayList<Tweet> tweets) {
    aTweets.addAll(tweets);
  }
  public void insert(Tweet tweet, int position) {
    aTweets.insert(tweet, position);
  }

}
