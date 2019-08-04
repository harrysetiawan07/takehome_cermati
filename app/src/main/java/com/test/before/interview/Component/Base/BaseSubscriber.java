package com.test.before.interview.Component.Base;

import java.io.IOException;
import retrofit2.HttpException;
import rx.Subscriber;

public abstract class BaseSubscriber<Model> extends Subscriber<Model> {

  public abstract void onSuccess(Model model);

  public abstract void onFailure(String message);

  public abstract void onError(String message);

  public abstract void onFinish();

  @Override
  public void onCompleted() {
    onFinish();
  }

  @Override
  public void onError(Throwable e) {
    String message = "";
    if (e instanceof HttpException) {
      HttpException httpException = (HttpException) e;
      message = "Http error : " + httpException.getMessage();
      onFailure(message);
    } else if (e instanceof IOException) {
      IOException exception = (IOException) e;
      message = "Connection error : " + exception.getMessage();
      onError(message);
    } else {
      message = "Something Wrong...";
      onFailure(message);
    }
    e.printStackTrace();
    onFinish();
  }

  @Override
  public void onNext(Model model) {
    onSuccess(model);
  }
}
