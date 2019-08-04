package com.test.before.interview.Module.Main.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.test.before.interview.Component.Base.BaseFragmentPresenter;
import com.test.before.interview.Module.Main.MainInteractor;
import com.test.before.interview.R;

import butterknife.BindView;

public class FragmentSplashView extends BaseFragmentPresenter<FragmentSplashPresenter> implements MainInteractor.MainView.SplashView {
    @BindView(R.id.fragment1_bg)
    RelativeLayout mbg;
    @BindView(R.id.fragment1_logo)
    ImageView mLogo;

    public FragmentSplashView(){}

    public static Fragment newInstance()
    {
        return new FragmentSplashView();
    }

    @Override
    protected FragmentSplashPresenter initPresenter() {
        return new FragmentSplashPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_splash;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onInitialize(@Nullable Bundle savedInstanceState) {
    }
}

