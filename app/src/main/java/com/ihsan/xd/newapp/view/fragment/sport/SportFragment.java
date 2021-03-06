package com.ihsan.xd.newapp.view.fragment.sport;


import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ihsan.xd.newapp.R;
import com.ihsan.xd.newapp.adapter.NewsAdapter;
import com.ihsan.xd.newapp.model.Articles;
import com.ihsan.xd.newapp.model.response.NewsResponse;
import com.ihsan.xd.newapp.view.base.mvp.MvpFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pl.droidsonroids.gif.GifImageView;

public class SportFragment extends MvpFragment<SportPresenter> implements SportView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.img_not_found)
    AppCompatImageView imgNotFound;
    @BindView(R.id.txt_not_found)
    TextView txtNotFound;
    @BindView(R.id.iv_loading)
    GifImageView ivLoading;
    @BindView(R.id.rv_sport)
    RecyclerView rvSport;
    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout swRefresh;
    List<Articles> listNews = new ArrayList<>();
    NewsAdapter newsAdapter;
    LinearLayoutManager mLinearLayoutManager;
    int page = 1;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sport;
    }

    @Override
    protected SportPresenter createPresenter() {
        return new SportPresenter(this);
    }

    @Override
    protected void onBindView() {
        initView();
    }

    @Override
    public void showDialog() {
        rvSport.setVisibility(View.GONE);
        ivLoading.setVisibility(View.VISIBLE);
        swRefresh.setRefreshing(false);
    }

    @Override
    public void dismissDialog() {
        ivLoading.setVisibility(View.GONE);
    }

    @Override
    public void getDataSuccess(NewsResponse model) {
        listNews = model.getArticles();
        newsAdapter.refresh(listNews);
        if (listNews.size() == 0) {
            rvSport.setVisibility(View.GONE);
            imgNotFound.setVisibility(View.VISIBLE);
            txtNotFound.setVisibility(View.VISIBLE);
        } else {
            rvSport.setVisibility(View.VISIBLE);
            imgNotFound.setVisibility(View.GONE);
            txtNotFound.setVisibility(View.GONE);
        }
    }

    @Override
    public void getPage(NewsResponse model) {
        newsAdapter.addNews(model.getArticles());
    }

    @Override
    public void getDataError(String message) {
        Log.d("Error Data Business", message);

    }

    @Override
    public void onRefresh() {
        listNews.clear();
        presenter.getSport();
    }

    private void initView() {
        presenter.getSport();
        swRefresh.setOnRefreshListener(this);
        newsAdapter = new NewsAdapter(getContext(), listNews);
        rvSport.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvSport.setLayoutManager(mLinearLayoutManager);
        rvSport.setAdapter(newsAdapter);
        rvSport.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    Log.e("test", "reached the last element of recyclerview");
                    int visibleItemCount = mLinearLayoutManager.getChildCount();
                    int totalItemCount = mLinearLayoutManager.getItemCount();
                    int pastVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        page = page + 1;
                        presenter.getPage(page);
                    }
                }
            }
        });
    }
}