package com.codepath.apps.twitterclient.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.TwitterApplication;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by marc on 9/28/14.
 */
public class ComposeFragment extends Fragment {

  private EditText etCompose;

  // Define the listener of the interface type
  // listener is the activity itself
  private OnItemSelectedListener listener;

  // Define the events that the fragment will use to communicate
  public interface OnItemSelectedListener {
    public void onTweetComposed(Tweet tweet);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Defines the xml file for the fragment
    View view = inflater.inflate(R.layout.fragment_compose, container, false);
    etCompose = (EditText) view.findViewById(R.id.etCompose);
    setHasOptionsMenu(true);
    return view;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    menu.findItem(R.id.action_compose).setVisible(false);
    inflater.inflate(R.menu.fragment_compose_menu, menu);
  }

  // Store the listener (activity) that will have events fired once the fragment is attached
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof OnItemSelectedListener) {
      listener = (OnItemSelectedListener) activity;
    } else {
      throw new ClassCastException(activity.toString()
                                       + " must implement ComposeFragment.OnItemSelectedListener");
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // handle item selection
    switch (item.getItemId()) {
      case R.id.action_button_tweet:
        onClickTweet();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }


  private void onClickTweet() {
    String body = etCompose.getText().toString();
    TwitterClient client = TwitterApplication.getRestClient();
    client.postStatuses(body, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(JSONObject tweetJSON) {
        listener.onTweetComposed(Tweet.fromJSON(tweetJSON));
      }

      @Override
      public void onFailure(Throwable throwable, String s) {
        Log.i("DEBUG", throwable.toString());
        Log.i("DEBUG", s.toString());
      }
    });

    // Make request to post the tweet
    // return the whole tweet to the activity

  }
}
