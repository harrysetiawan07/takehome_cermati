package com.test.before.interview.Interactor;

import android.content.Context;

import com.test.before.interview.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class ApiFactory {
  private static final long UNIVERSAL_TIMEOUT = 30;

  public ApiInterface getApi(Context mContext) {
    return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL_PUBLIC)
            .client(mClient(mContext))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(ApiInterface.class);
  }

  private OkHttpClient mClient(Context mContext) {
    Cache cache = null;
    try {
      cache = new Cache(new File(mContext.getCacheDir(), "cache"), 10 * 1024 * 1024); // 10 MB
    } catch (Exception e) {
      Timber.e(e, "Error creating cache file");
    }
    return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor())
        .connectTimeout(UNIVERSAL_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(UNIVERSAL_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(UNIVERSAL_TIMEOUT, TimeUnit.SECONDS)
        .cache(cache)
        .build();
  }

  private HttpLoggingInterceptor httpLoggingInterceptor() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.d(message));
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return httpLoggingInterceptor;
  }
}
