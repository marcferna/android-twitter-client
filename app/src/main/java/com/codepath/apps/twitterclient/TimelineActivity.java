package com.codepath.apps.twitterclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

public class TimelineActivity extends Activity {

  private TwitterClient client;
  private ArrayList<Tweet> tweets;
  private TweetArrayAdapter aTweets;
  private ListView lvTweets;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_timeline);
    client = TwitterApplication.getRestClient();
    populateTimeline();
    lvTweets = (ListView) findViewById(R.id.lvTweets);
    tweets = new ArrayList<Tweet>();
    aTweets = new TweetArrayAdapter(this, tweets);
    lvTweets.setAdapter(aTweets);
  }

  public void populateTimeline() {
    client.getHomeTimeline(new JsonHttpResponseHandler() {
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
    getMenuInflater().inflate(R.menu.timeline, menu);
    return true;
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
}
