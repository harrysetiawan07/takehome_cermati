package com.test.before.interview.Module.Main.Fragment;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import com.test.before.interview.Apps;
import com.test.before.interview.Component.Base.BasePresenterImpl;
import com.test.before.interview.Component.Constant;
import com.test.before.interview.Module.Content.ContentsView;
import com.test.before.interview.Module.Main.MainInteractor;

import static com.test.before.interview.Apps.getInstance;

public class FragmentEnterPresenter extends BasePresenterImpl<MainInteractor.MainView.EnterView> implements MainInteractor.MainPresenter.EnterPresenter{

    FragmentEnterView parent;

    public FragmentEnterPresenter(FragmentEnterView parent){
        this.parent = parent;
        super.onAttach(this.parent, this.parent.getContext());
    }

    @Override
    public void callContent() {
        Apps.getInstance().getPreferenceHelper().setBooleanPref(Constant.flagDoEnter, true);
        parent.startActivity(new Intent(getInstance().getApplicationContext(), ContentsView.class));
        ActivityCompat.finishAfterTransition(parent.parentActivity);
    }
}
