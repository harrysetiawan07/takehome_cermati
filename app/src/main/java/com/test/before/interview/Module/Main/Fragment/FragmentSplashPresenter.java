package com.test.before.interview.Module.Main.Fragment;


import com.test.before.interview.Component.Base.BasePresenterImpl;
import com.test.before.interview.Module.Main.MainInteractor;

public class FragmentSplashPresenter extends BasePresenterImpl<MainInteractor.MainView.SplashView> implements MainInteractor.MainPresenter.SplashPresenter{

    FragmentSplashView parent;

    public FragmentSplashPresenter(FragmentSplashView parent){
        this.parent = parent;
    }

}
