package com.test.before.interview.Module.Content;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.test.before.interview.Apps;
import com.test.before.interview.Component.Base.BasePresenterImpl;
import com.test.before.interview.Module.Content.Fragment.FragmentUserView;
import com.test.before.interview.R;

public class ContentPresenter extends BasePresenterImpl<ContentInteractor.ContentView> implements ContentInteractor.ContentPresenter {

    ContentsView parent;
    FragmentUserView userView;

    public ContentPresenter(ContentsView parentActivity){
        this.parent = parentActivity;
        onAttach(this.parent, this.parent.getApplicationContext());
        Apps.getInstance().getDatabase().ClearUser();
    }

    @Override
    public void OnLoadInitialFragment() {
        userView = new FragmentUserView();
        FragmentTransaction fragmentTransaction = parent.fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, userView);
        fragmentTransaction.commit();
    }
}
