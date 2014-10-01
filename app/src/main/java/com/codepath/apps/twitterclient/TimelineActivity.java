package com.codepath.apps.twitterclient;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.activeandroid.content.ContentProvider;
import com.codepath.apps.twitterclient.fragments.ComposeFragment;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.sql.Time;
import java.util.ArrayList;

public class TimelineActivity extends FragmentActivity  implements
    ComposeFragment.OnItemSelectedListener {

  private TwitterClient client;
  private ArrayList<Tweet> tweets;
  private TweetCursorAdapter aTweets;
  private ListView lvTweets;

  ComposeFragment composeFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_timeline);
    client = TwitterApplication.getRestClient();
//    populateTimeline();
    lvTweets = (ListView) findViewById(R.id.lvTweets);
    tweets = new ArrayList<Tweet>();
    aTweets = new TweetCursorAdapter(this, null, 0);
    aTweets.notifyDataSetChanged();
    lvTweets.setAdapter(aTweets);

    this.getSupportLoaderManager().initLoader(0, null, new android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>() {
      @Override
      public android.support.v4.content.Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new android.support.v4.content.CursorLoader(TimelineActivity.this, ContentProvider.createUri(Tweet.class, null), null, null, null, null);
      }

      @Override
      public void onLoadFinished(android.support.v4.content.Loader<Cursor> cursorLoader, Cursor cursor) {
        aTweets.swapCursor(cursor);
      }

      @Override
      public void onLoaderReset(android.support.v4.content.Loader<Cursor> cursorLoader) {
        aTweets.swapCursor(null);
      }
    });

//    lvTweets.setOnScrollListener(new EndlessScrollListener() {
//      @Override
//      public void onLoadMore(int page, int totalItemsCount) {
//        Tweet lastTweet = tweets.get(tweets.size() - 1);
//        TimelineActivity.this.populateTimeline(lastTweet.getUid());
//      }
//    });
  }

  public void populateTimeline() {
    this.populateTimeline(0);
  }
  public void populateTimeline(long maxId) {
    client.getHomeTimeline(maxId, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(JSONArray jsonArray) {
        Tweet.fromJSONArray(jsonArray);
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
//    aTweets.insert(tweet, 0);
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.remove(composeFragment);
    ft.commit();

  }
}
