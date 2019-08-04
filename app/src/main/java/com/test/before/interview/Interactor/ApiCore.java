package com.test.before.interview.Interactor;

import com.test.before.interview.Apps;
import com.test.before.interview.Component.Base.BaseSubscriber;
import com.test.before.interview.Interactor.Request.DTL;
import com.test.before.interview.Interactor.Response.DataUser;
import com.test.before.interview.Interactor.Response.ObjRequestCallback;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ApiCore {

    CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    public APICallback callback;
    ObjRequestCallback objRequestCallback;

    public void doGetUser(String searchUser, int page, int perPage, APICallback callback) {
        setAPICallback(callback);
        onSubscribeObservable(Apps.getInstance().getAPIInterface().requestUser(searchUser, page, perPage),
                new BaseSubscriber<DTL<DataUser>>() {
            @Override
            public void onSuccess(DTL<DataUser> response) {
                Apps.getInstance().getDatabaseMapper().mapUser(response.getItems());
                objRequestCallback = new ObjRequestCallback(true, "success");
                callback.onResponseSuccess(objRequestCallback);
            }

            @Override
            public void onFailure(String message) {
                objRequestCallback = new ObjRequestCallback(false, message);
                callback.onResponseFailed(objRequestCallback);
            }

            @Override
            public void onError(String message) {
                objRequestCallback = new ObjRequestCallback(false, message);
                callback.onResponseError(objRequestCallback);
            }

            @Override
            public void onFinish() {
            }
        });
    }

    protected void onSubscribeObservable(Observable observable, Subscriber subscriber) {
        mCompositeSubscription.add(
                observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .retry(1)
                .subscribe(subscriber)
        );
    }

    public interface APICallback{
        void onResponseSuccess(ObjRequestCallback objRequestCallback);
        void onResponseFailed(ObjRequestCallback objRequestCallback);
        void onResponseError(ObjRequestCallback objRequestCallback);
    }

    public void setAPICallback(final APICallback callback){
        this.callback = callback;
    }
}
