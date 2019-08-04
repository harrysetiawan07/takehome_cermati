package com.test.before.interview.Module.Content;

public interface ContentInteractor {
    interface ContentPresenter {
        void OnLoadInitialFragment();

        interface UserPresenter {
            void OnRequestUser();
        }
    }

    interface ContentView {
        void initText();
        void initTypeface();
        void callAlertDialog(String content, String button);

        void onSetToolbar();

        interface UserView {
            void initTypeface();
            void setSwipeRefreshColor();
            void declareAdapter();
        }
    }
}
