package com.test.before.interview.Component.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.test.before.interview.Apps;
import com.test.before.interview.Database.Manager.InterfaceDatabaseManager;
import com.test.before.interview.Interactor.ApiInterface;

import rx.subscriptions.CompositeSubscription;

public class BasePresenterImpl<View> {

  private Context ctx;
  protected View mView;
  protected CompositeSubscription mCompositeSubscription;
  protected ApiInterface mApiInterface;
  protected InterfaceDatabaseManager mDaoSession;
  public Handler handler = new Handler();

  protected void onAttach(View view, Context ctx) {
    this.ctx = ctx;
    this.mView = view;
    mCompositeSubscription = new CompositeSubscription();
    mApiInterface = Apps.getInstance().getAPIInterface();
    mDaoSession = Apps.getInstance().getDatabase();
  }

  public interface itemClickCallbackDialog{
    void onItemClick(boolean action);
  }
  public interface itemClickCallbackDialogList{
    void onItemClick(String action);
  }

  public void changeActivity(Activity activity, final Class classes) {
    Intent intent = new Intent(activity, classes);
    intent.addCategory(Intent.CATEGORY_HOME);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    activity.startActivity(intent);
  }
}
