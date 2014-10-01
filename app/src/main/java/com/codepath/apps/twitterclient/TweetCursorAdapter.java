package com.codepath.apps.twitterclient;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by marc on 9/30/14.
 */
public class TweetCursorAdapter extends CursorAdapter {
  private LayoutInflater cursorInflater;

  public TweetCursorAdapter(Context context, Cursor cursor, int flags) {
    super(context, cursor, flags);
    cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public void bindView(View view, Context context, Cursor cursor) {
    ImageView ivProfileImage = (ImageView) view.findViewById(R.id.ivProfileImage);
    TextView tvUserName = (TextView) view.findViewById(R.id.tvUserName);
    TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
    TextView tvName = (TextView) view.findViewById(R.id.tvName);
    TextView tvCreatedAt = (TextView) view.findViewById(R.id.tvCreatedAt);


//    String imageUrl = cursor.getString(cursor.getColumnIndex(""));
//    cursor.getType("user");
//    textViewTitle.setText(title);
//    ...
  }

  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    return cursorInflater.inflate(R.layout.tweet_item, parent, false);
  }
}
