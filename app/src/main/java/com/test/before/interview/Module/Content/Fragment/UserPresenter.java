package com.test.before.interview.Module.Content.Fragment;

import android.util.Log;

import com.test.before.interview.Apps;
import com.test.before.interview.Component.Base.BasePresenterImpl;
import com.test.before.interview.Database.Model.User;
import com.test.before.interview.Interactor.ApiCore;
import com.test.before.interview.Interactor.Response.ObjRequestCallback;
import com.test.before.interview.Module.Content.ContentInteractor;

import java.util.ArrayList;
import java.util.List;

public class UserPresenter extends BasePresenterImpl<ContentInteractor.ContentView.UserView> implements ContentInteractor.ContentPresenter.UserPresenter{


    List<User> users = new ArrayList<>();

    FragmentUserView parent;

    public UserPresenter(FragmentUserView parent){
        this.parent = parent;
        super.onAttach(this.parent, this.parent.getContext());
    }

    @Override
    public void OnRequestUser() {
        parent.swRefresh.setRefreshing(true);
        Apps.getInstance().getAPICore().doGetUser(
                parent.parentActivity.searchName, parent.parentActivity.currentPage, parent.parentActivity.perPage, new ApiCore.APICallback() {
                    @Override
                    public void onResponseSuccess(ObjRequestCallback objRequestCallback) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                parent.swRefresh.setRefreshing(false);
                                users.addAll(Apps.getInstance().getDatabase().ListAllUser((parent.parentActivity.currentPage - 1) * parent.parentActivity.perPage, parent.parentActivity.currentPage * parent.parentActivity.perPage));
                                parent.userAdapter.notifyDataSetChanged();
                            }
                        }, 200);
                    }

                    @Override
                    public void onResponseFailed(ObjRequestCallback objRequestCallback) {
                        parent.swRefresh.setRefreshing(false);
                        Log.e("Response Failed : ", objRequestCallback.getMessage());
                    }

                    @Override
                    public void onResponseError(ObjRequestCallback objRequestCallback) {
                        parent.swRefresh.setRefreshing(false);
                        Log.e("Response Error : ", objRequestCallback.getMessage());
                    }
                }
        );
    }
}
