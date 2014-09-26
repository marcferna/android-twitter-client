package com.codepath.apps.twitterclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterclient.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by marc on 9/25/14.
 */
public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
  public TweetArrayAdapter(Context context, List<Tweet> tweets) {
    super(context, 0, tweets);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Tweet tweet = getItem(position);

    View v;
    if (convertView == null) {
      LayoutInflater inflater = LayoutInflater.from(getContext());
      v = inflater.inflate(R.layout.tweet_item, parent, false);
    } else {
      v = convertView;
    }

    ImageView ivProfileImage = (ImageView) v.findViewById(R.id.ivProfileImage);
    TextView tvUserName = (TextView) v.findViewById(R.id.tvUserName);
    TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
    TextView tvName = (TextView) v.findViewById(R.id.tvName);
    TextView tvCreatedAt = (TextView) v.findViewById(R.id.tvCreatedAt);

    ivProfileImage.setImageResource(0);
    ImageLoader imageLoader = ImageLoader.getInstance();
    imageLoader.displayImage(tweet.getUser().getProfileImageUrl(), ivProfileImage);
    tvName.setText(tweet.getUser().getName());
    tvUserName.setText("@" + tweet.getUser().getScreenName());
    tvBody.setText(tweet.getBody());
    tvCreatedAt.setText(tweet.getRelativeCreatedAt());
    return v;
  }
}
