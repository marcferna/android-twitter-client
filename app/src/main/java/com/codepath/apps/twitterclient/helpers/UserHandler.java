package com.codepath.apps.twitterclient.helpers;

import com.codepath.apps.twitterclient.models.User;

/**
 * Created by marc on 10/5/14.
 */
public abstract class UserHandler {

  public abstract void onSuccess(User user);
  public abstract void onFailure();
}
