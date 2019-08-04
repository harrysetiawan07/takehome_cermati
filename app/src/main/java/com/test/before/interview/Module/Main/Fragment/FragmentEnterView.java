package com.test.before.interview.Module.Main.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.before.interview.BuildConfig;
import com.test.before.interview.Component.Base.BaseFragmentPresenter;
import com.test.before.interview.Component.Utils;
import com.test.before.interview.Module.Main.MainInteractor;
import com.test.before.interview.Module.Main.MainView;
import com.test.before.interview.R;

import butterknife.BindView;

public class FragmentEnterView extends BaseFragmentPresenter<FragmentEnterPresenter> implements
        MainInteractor.MainView.EnterView,
        View.OnClickListener{
    @BindView(R.id.fragment2_bg)
    RelativeLayout mbg;
    @BindView(R.id.fragment2_logo)
    ImageView mLogo;
    @BindView(R.id.tvName) public TextView tvName;
    @BindView(R.id.tvMail) public TextView tvMail;
    @BindView(R.id.tvVersion) public TextView tvVersion;
    @BindView(R.id.transitions_container) public ViewGroup transitionsContainer;
    @BindView(R.id.layoutLogin) public RelativeLayout layoutLogin;
    @BindView(R.id.layoutInfoApps) public LinearLayout layoutInfoApps;
    @BindView(R.id.btnLogin) public Button btnLogin;

    MainView parentActivity;

    public FragmentEnterView(){ }

    public static FragmentEnterView newInstance()
    {
        return new FragmentEnterView();
    }

    @Override
    protected FragmentEnterPresenter initPresenter() {
        return new FragmentEnterPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_enter;
    }

    @Override
    protected void onInitialize(@Nullable Bundle savedInstanceState) {
        this.parentActivity = (MainView) getActivity();
        initText();
        initTypeface();
        transitionForm();
        declareOnclick();
    }

    @Override
    public void initText() {
        tvVersion.setText(BuildConfig.VERSION_NAME);
    }

    @Override
    public void initTypeface() {
        btnLogin.setTypeface(Utils.typefaceBold());
        tvName.setTypeface(Utils.typefaceNormal());
        tvMail.setTypeface(Utils.typefaceNormal());
        tvVersion.setTypeface(Utils.typefaceNormal());
    }

    @Override
    public void transitionForm() {
        mPresenter.handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layoutLogin.animate()
                                .alpha(1.0f)
                                .setDuration(400)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        layoutLogin.setVisibility(View.VISIBLE);
                                    }
                                });
                    }
                }, 200);
            }
        }, 1000);
    }

    @Override
    public void declareOnclick() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                mPresenter.callContent();
                break;
        }
    }
}
