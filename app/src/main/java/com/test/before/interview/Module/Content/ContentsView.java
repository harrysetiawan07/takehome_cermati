package com.test.before.interview.Module.Content;

import android.animation.Animator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test.before.interview.Apps;
import com.test.before.interview.Component.Base.BaseActivityPresenter;
import com.test.before.interview.Component.Constant;
import com.test.before.interview.Component.Service.ConnectivityChangeReceiver;
import com.test.before.interview.Component.Utils;
import com.test.before.interview.R;

import butterknife.BindView;

public class ContentsView extends BaseActivityPresenter<ContentPresenter>
        implements ContentInteractor.ContentView,
        ConnectivityChangeReceiver.ConnectivityReceiverListener  {

    public String searchName = "harrysetiawan";
    public int currentPage = 1;
    public int perPage = 20;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.layoutSearch)
    public LinearLayout layoutSearch;
    @BindView(R.id.layoutToolbar)
    public RelativeLayout layoutToolbar;
    @BindView(R.id.layoutContent)
    public LinearLayout layoutContent;
    @BindView(R.id.etSearch)
    public EditText etSearch;

    public ImageView toolbarIcon;
    public TextView toolbarTitle;
    public ImageView toolbarAction;

    public android.support.v4.app.FragmentManager fragmentManager;

    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(!Apps.getInstance().isNetworkAvailable()){
                callAlertDialog("Please check your connection.", "Try Again");
            }
        }
    };

    @Override
    protected int DeclareLayout() {
        return R.layout.activity_content;
    }

    @Override
    protected ContentPresenter DeclarePresenter() {
        return new ContentPresenter(this);
    }

    @Override
    protected void onInitialize(Bundle savedInstance) {
        onSetToolbar();
        fragmentManager = getSupportFragmentManager();
        mPresenter.OnLoadInitialFragment();
        initTypeface();
        initText();
    }

    @Override
    public void initText() {
        etSearch.setText(searchName);
        etSearch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,int before, int count) {
                searchName = s.toString();
                mPresenter.userView.onRefresh();
            }
        });
    }

    @Override
    public void initTypeface() {
        etSearch.setTypeface(Utils.typefaceNormal());
    }

    @Override
    public void onSetToolbar() {
        setSupportActionBar(toolbar);
        toolbarIcon = toolbar.findViewById(R.id.toolbarImage);
        toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarAction = toolbar.findViewById(R.id.btnSearch);
        toolbarIcon.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        toolbarTitle.setText(getResources().getString(R.string.titleDataUser));
        toolbarTitle.setTypeface(Utils.typefaceBold());
        toolbarAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Constant.flagIsOpenSearchUser){
                    showLayoutSearch();
                }else{
                    hideLayoutSearch();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void callAlertDialog(String content, String button){
        showAlert(content, button, new itemClickCallbackDialog() {
            @Override
            public void onItemClick(boolean action) {
                onResume();
            }
        });
    }

    @Override
    public void onResume() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, intentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public void onBackPressed() {
        if(Constant.flagIsOpenSearchUser){
            hideLayoutSearch();
        }else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Toast.makeText(this, isConnected+"", Toast.LENGTH_LONG).show();
    }

    private void showLayoutSearch(){
        if (Build.VERSION.SDK_INT >= 21) {
            int x = layoutToolbar.getRight();
            int y = layoutToolbar.getBottom();
            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutContent.getWidth(), layoutContent.getHeight());
            Animator anim = ViewAnimationUtils.createCircularReveal(layoutSearch, x, y, startRadius, endRadius);
            layoutSearch.setVisibility(View.VISIBLE);
            anim.start();
        }else{
            layoutSearch.setVisibility(View.VISIBLE);
        }
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        etSearch.requestFocus();
        etSearch.setSelection(etSearch.getText().length());
        Constant.flagIsOpenSearchUser = true;
    }

    private void hideLayoutSearch(){
        if (Build.VERSION.SDK_INT >= 21) {
            int x = layoutToolbar.getRight();
            int y = layoutToolbar.getBottom();
            int startRadius = Math.max(layoutContent.getWidth(), layoutContent.getHeight());
            int endRadius = 0;
            Animator anim = ViewAnimationUtils.createCircularReveal(layoutSearch, x, y, startRadius, endRadius);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }
                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutSearch.setVisibility(View.GONE);
                }
                @Override
                public void onAnimationCancel(Animator animator) {
                }
                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
            anim.start();
        }else{
            layoutSearch.setVisibility(View.GONE);
        }
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
        Constant.flagIsOpenSearchUser = false;
    }
}
