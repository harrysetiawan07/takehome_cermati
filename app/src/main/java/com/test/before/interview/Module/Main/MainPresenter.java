package com.test.before.interview.Module.Main;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;

import com.test.before.interview.Apps;
import com.test.before.interview.Component.Base.BasePresenterImpl;
import com.test.before.interview.Component.Constant;
import com.test.before.interview.Module.Content.ContentsView;
import com.test.before.interview.Module.Main.Fragment.FragmentSplashView;
import com.test.before.interview.R;

import butterknife.ButterKnife;

public class MainPresenter extends BasePresenterImpl<MainInteractor.MainView> implements MainInteractor.MainPresenter {

    public final long MOVE_DEFAULT_TIME = 1000;
    public static final long FADE_DEFAULT_TIME = 0;
    public Handler mDelayedTransactionHandler = new Handler();
    public Runnable mRunnable = this::performTransition;

    MainView parent;

    public MainPresenter(MainView parentActivity){
        this.parent = parentActivity;
        onAttach(this.parent, this.parent.getApplicationContext());
    }

    @Override
    public void onDeclareEnterFragment() {
        FragmentManager frag = parent.getSupportFragmentManager();
        frag.beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout).replace(R.id.fragment_container, parent.enterView).commit();
    }

    @Override
    public void loadInitialFragment() {
        Fragment initialFragment = FragmentSplashView.newInstance();
        FragmentTransaction fragmentTransaction = parent.fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, initialFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void performTransition() {
        Fragment previousFragment = parent.fragmentManager.findFragmentById(R.id.fragment_container);
        if (Build.VERSION.SDK_INT >= 21) {

            FragmentTransaction fragmentTransaction = parent.fragmentManager.beginTransaction();

            Fade exitFade = new Fade();
            exitFade.setDuration(FADE_DEFAULT_TIME);
            previousFragment.setExitTransition(exitFade);

            TransitionSet enterTransitionSet = new TransitionSet();
            enterTransitionSet.addTransition(TransitionInflater.from(parent).inflateTransition(android.R.transition.move));
            enterTransitionSet.setDuration(MOVE_DEFAULT_TIME);
            enterTransitionSet.setStartDelay(FADE_DEFAULT_TIME);
            parent.enterView.setSharedElementEnterTransition(enterTransitionSet);

            Fade enterFade = new Fade();
            enterFade.setStartDelay(MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME);
            enterFade.setDuration(FADE_DEFAULT_TIME);
            parent.enterView.setEnterTransition(enterFade);

            View logo = ButterKnife.findById(parent, R.id.fragment1_logo);
            View bg = ButterKnife.findById(parent, R.id.fragment1_bg);
            fragmentTransaction.addSharedElement(logo, logo.getTransitionName());
            fragmentTransaction.addSharedElement(bg, bg.getTransitionName());
            fragmentTransaction.replace(R.id.fragment_container, parent.enterView);
            fragmentTransaction.commitAllowingStateLoss();
        }else {
            onDeclareEnterFragment();
        }
    }

    @Override
    public void OnResume() {
        if(Apps.getInstance().getPreferenceHelper().getBooleanPref(Constant.flagDoEnter)) {
            parent.startActivity(new Intent(parent.getApplicationContext(), ContentsView.class));
            ActivityCompat.finishAfterTransition(parent);
        }
    }
}
