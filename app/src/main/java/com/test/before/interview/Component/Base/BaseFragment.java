package com.test.before.interview.Component.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
  static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }
  protected Context mContext;
//  public MainView mainView;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = getActivity();
    try {
//      mainView = (MainView) getActivity();
    }catch (Exception e){}
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(initLayout(), container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    onInitialize(savedInstanceState);
  }

  protected abstract int initLayout();

  protected abstract void onInitialize(@Nullable Bundle savedInstanceState);

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }

}
