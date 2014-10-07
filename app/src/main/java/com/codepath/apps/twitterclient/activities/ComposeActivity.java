package com.codepath.apps.twitterclient.activities;

import android.app.Activity;
import android.content.Intent;
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
public class ComposeActivity extends Activity {

  public static String TWEET_INTENT_KEY = "tweet";

  private EditText etCompose;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_compose);
    etCompose = (EditText) findViewById(R.id.etCompose);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.compose, menu);
    return true;
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
        Intent resultIntent = new Intent();
        resultIntent.putExtra(TWEET_INTENT_KEY, Tweet.fromJSON(tweetJSON));
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
      }

      @Override
      public void onFailure(Throwable throwable, String s) {
        Log.i("DEBUG", throwable.toString());
        Log.i("DEBUG", s.toString());
      }
    });
  }
}
