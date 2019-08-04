package com.test.before.interview.Component.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseActivityPresenter<P extends BasePresenterImpl> extends BaseActivity{
  public P mPresenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    mPresenter = DeclarePresenter();
    super.onCreate(savedInstanceState);
  }

  protected abstract int DeclareLayout();

  protected abstract void onInitialize(Bundle savedInstance);

  protected abstract P DeclarePresenter();
}
