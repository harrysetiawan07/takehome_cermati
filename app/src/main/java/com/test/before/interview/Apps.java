package com.test.before.interview;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.test.before.interview.Component.PreferencesManager;
import com.test.before.interview.Database.Manager.DatabaseManager;
import com.test.before.interview.Database.Manager.DatabaseMapper;
import com.test.before.interview.Database.Manager.InterfaceDatabaseManager;
import com.test.before.interview.Interactor.ApiCore;
import com.test.before.interview.Interactor.ApiFactory;
import com.test.before.interview.Interactor.ApiInterface;

import timber.log.Timber;

public class Apps extends Application {
  private static Apps instance;
  private ApiInterface mApiInterface;
  private DatabaseMapper dbMaper;
  private PreferencesManager mPreferenceHelper;
  private InterfaceDatabaseManager DB;
  private ApiCore coreRequestAPI;
  private String transactionReturnId;

  @Override
  public void onCreate() {
    super.onCreate();
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree() {
        @Override
        protected String createStackElementTag(StackTraceElement element) {
          return super.createStackElementTag(element) + " CERMATI DEBUG : " + element.getLineNumber();
        }
      });
    }

    if (instance == null) instance = this;
  }

  public static Apps getInstance() {
    return instance;
  }

  public ApiInterface getAPIInterface() {
    if (mApiInterface == null) {
      mApiInterface = new ApiFactory().getApi(this);
    }
    return mApiInterface;
  }

  public ApiCore getAPICore() {
    if (coreRequestAPI == null) {
      coreRequestAPI = new ApiCore();
    }
    return coreRequestAPI;
  }

  public InterfaceDatabaseManager getDatabase(){
    if(DB == null) {
      DB = new DatabaseManager(getApplicationContext());
    }
    return DB;
  }

  public DatabaseMapper getDatabaseMapper() {
    if (dbMaper == null) {
      dbMaper = new DatabaseMapper();
    }
    return dbMaper;
  }

  public PreferencesManager getPreferenceHelper() {
    if (mPreferenceHelper == null) {
      mPreferenceHelper = new PreferencesManager(this);
    }
    return mPreferenceHelper;
  }

  public boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager =
        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
  }

  public String getIMEI(){
    try {
      TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
      return telephonyManager.getDeviceId();
    }catch (Exception e){
      return "";
    }
  }

  public void showToast(String content){
    Toast.makeText(this, content, Toast.LENGTH_LONG).show();
  }
}
