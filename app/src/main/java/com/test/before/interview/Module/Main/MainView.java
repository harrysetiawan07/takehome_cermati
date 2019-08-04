package com.test.before.interview.Module.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.test.before.interview.Component.Base.BaseActivityPresenter;
import com.test.before.interview.Module.Main.Fragment.FragmentEnterView;
import com.test.before.interview.R;

public class MainView extends BaseActivityPresenter<MainPresenter> implements MainInteractor.MainView{

    public android.support.v4.app.FragmentManager fragmentManager;
    public FragmentEnterView enterView;

    @Override
    protected int DeclareLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter DeclarePresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onInitialize(Bundle savedInstance) {
        fragmentManager = getSupportFragmentManager();
        initialFragment();
        mPresenter.loadInitialFragment();
        mPresenter.mDelayedTransactionHandler.postDelayed(mPresenter.mRunnable, 5000);
    }


    @Override
    public void initialFragment() {
        enterView  = FragmentEnterView.newInstance();
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        mPresenter.mDelayedTransactionHandler.removeCallbacks(mPresenter.mRunnable);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        mPresenter.OnResume();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
