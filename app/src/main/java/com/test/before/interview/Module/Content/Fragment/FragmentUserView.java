package com.test.before.interview.Module.Content.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.test.before.interview.Apps;
import com.test.before.interview.Component.Adapter.EndlessScrollListener;
import com.test.before.interview.Component.Adapter.ListUserAdapter;
import com.test.before.interview.Component.Base.BaseFragmentPresenter;
import com.test.before.interview.Component.Utils;
import com.test.before.interview.Module.Content.ContentInteractor;
import com.test.before.interview.Module.Content.ContentsView;
import com.test.before.interview.R;

import butterknife.BindView;

public class FragmentUserView extends BaseFragmentPresenter<UserPresenter> implements
        ContentInteractor.ContentView.UserView,
        SwipeRefreshLayout.OnRefreshListener,
        ListUserAdapter.itemClickCallback {

    @BindView(R.id.refresh)
    SwipeRefreshLayout swRefresh;
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.tvNotFound)
    public TextView notFound;

    ListUserAdapter userAdapter;
    ContentsView parentActivity;

    public FragmentUserView(){ }

    public static FragmentUserView newInstance()
    {
        return new FragmentUserView();
    }

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_user;
    }

    @Override
    protected void onInitialize(@Nullable Bundle savedInstanceState) {
        this.parentActivity = (ContentsView) getActivity();
        initTypeface();
        setSwipeRefreshColor();
        declareAdapter();
        onRefresh();
    }

    @Override
    public void initTypeface() {
        notFound.setTypeface(Utils.typefaceNormal());
    }

    @Override
    public void setSwipeRefreshColor() {
        swRefresh.setColorSchemeColors(getResources().getColor(R.color.md_yellow_800));
        swRefresh.setOnRefreshListener(this);
    }

    @Override
    public void declareAdapter() {
        LinearLayoutManager LLayout = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(LLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(parentActivity, DividerItemDecoration.VERTICAL));
        userAdapter = new ListUserAdapter(mPresenter.users, parentActivity);
        userAdapter.setItemClickCallback(this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.addOnScrollListener(new EndlessScrollListener(LLayout) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                parentActivity.currentPage += 1;
                mPresenter.OnRequestUser();
            }
        });
    }

    @Override
    public void onRefresh() {
        if(parentActivity.searchName.equals("")){
            recyclerView.setVisibility(View.GONE);
            notFound.setVisibility(View.VISIBLE);
        }else {
            notFound.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            Apps.getInstance().getDatabase().ClearUser();
            parentActivity.currentPage = 1;
            mPresenter.users.clear();
            mPresenter.OnRequestUser();
        }
    }

    @Override
    public void onItemClick(int p) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(mPresenter.users.get(p).getHtmlURL()));
        startActivity(intent);
    }
}
