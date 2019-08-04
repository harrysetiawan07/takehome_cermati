package com.test.before.interview.Module.Main;

public interface MainInteractor {
    interface MainPresenter {
        void onDeclareEnterFragment();
        void loadInitialFragment();
        void performTransition();
        void OnResume();

        interface SplashPresenter {
        }

        interface EnterPresenter {
            void callContent();
        }
    }

    interface MainView {
        void initialFragment();

        interface SplashView {
        }

        interface EnterView {
            void initText();
            void initTypeface();
            void transitionForm();
            void declareOnclick();
        }
    }
}
