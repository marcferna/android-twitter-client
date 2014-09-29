package com.codepath.apps.twitterclient;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.twitterclient.fragments.ComposeFragment;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

public class TimelineActivity extends FragmentActivity  implements
    ComposeFragment.OnItemSelectedListener {

  private TwitterClient client;
  private ArrayList<Tweet> tweets;
  private TweetArrayAdapter aTweets;
  private ListView lvTweets;

  ComposeFragment composeFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_timeline);
    client = TwitterApplication.getRestClient();
    populateTimeline();
    lvTweets = (ListView) findViewById(R.id.lvTweets);
    tweets = new ArrayList<Tweet>();
    aTweets = new TweetArrayAdapter(this, tweets);
    aTweets.notifyDataSetChanged();
    lvTweets.setAdapter(aTweets);

    lvTweets.setOnScrollListener(new EndlessScrollListener() {
      @Override
      public void onLoadMore(int page, int totalItemsCount) {
        Tweet lastTweet = tweets.get(tweets.size() - 1);
        TimelineActivity.this.populateTimeline(lastTweet.getUid());
      }
    });
  }

  public void populateTimeline() {
    this.populateTimeline(0);
  }
  public void populateTimeline(long maxId) {
    client.getHomeTimeline(maxId, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(JSONArray jsonArray) {
        aTweets.addAll(Tweet.fromJSONArray(jsonArray));
      }

      @Override
      public void onFailure(Throwable throwable, String s) {
        Log.i("DEBUG", throwable.toString());
        Log.i("DEBUG", s.toString());
      }
    });
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.timeline, menu);
    return true;
  }

  public void onClickCompose(MenuItem item) {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//    ComposeFragment composeFragment = ComposeFragment.newInstance(5, "my title");
    composeFragment = new ComposeFragment();

    ft.replace(R.id.fgCompose, composeFragment);
    ft.commit();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
        return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void onTweetComposed(Tweet tweet) {
    // Add the newly created tweet
    aTweets.insert(tweet, 0);
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.remove(composeFragment);
    ft.commit();

  }
}
