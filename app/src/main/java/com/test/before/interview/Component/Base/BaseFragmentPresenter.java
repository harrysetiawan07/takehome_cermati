package com.test.before.interview.Component.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseFragmentPresenter<P extends BasePresenterImpl> extends BaseFragment {

  protected P mPresenter;

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    mPresenter = initPresenter();
    setHasOptionsMenu(true);
    super.onActivityCreated(savedInstanceState);
  }

  protected abstract P initPresenter();

  protected abstract int initLayout();

  protected abstract void onInitialize(@Nullable Bundle savedInstanceState);
}
