package com.codepath.apps.twitterclient.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.helpers.UserHandler;
import com.codepath.apps.twitterclient.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;

public class UserHeaderFragment extends SherlockFragment {

  private long uid;

  private ImageView ivProfile;
  private TextView tvName;
  private TextView tvTagline;
  private TextView tvFollowersCount;
  private TextView tvFollowingCount;

  public static final UserHeaderFragment newInstance(long uid) {
    UserHeaderFragment fragment = new UserHeaderFragment();
    Bundle bundle = new Bundle(1);
    bundle.putLong("user_uid", uid);
    fragment.setArguments(bundle);
    return fragment;
  }

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    uid = getArguments().getLong("user_uid");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v =  inflater.inflate(R.layout.fragment_user_header, container, false);
    ivProfile = (ImageView) v.findViewById(R.id.ivUserProfileImage);
    tvName = (TextView) v.findViewById(R.id.tvName);
    tvTagline = (TextView) v.findViewById(R.id.tvTagline);
    tvFollowersCount = (TextView) v.findViewById(R.id.tvFollowersCount);
    tvFollowingCount = (TextView) v.findViewById(R.id.tvFollowingCount);
    User.getUser(uid, new UserHandler() {
      @Override
      public void onSuccess(User user) {
        loadUserInfo(user);
      }

      @Override
      public void onFailure() {

      }
    });
    return v;
  }


  public void loadUserInfo(User user) {
    getActivity().getActionBar().setTitle("@" + user.getScreenName());
    tvName.setText(user.getName());
    ImageLoader imageLoader = ImageLoader.getInstance();
    imageLoader.displayImage(user.getProfileImageUrl(), ivProfile);
    tvTagline.setText(user.getTagline());
    tvFollowersCount.setText(Integer.toString(user.getFollowersCount()) + " Followers");
    tvFollowingCount.setText(Integer.toString(user.getFriendsCount()) + " Following");
  }
}
