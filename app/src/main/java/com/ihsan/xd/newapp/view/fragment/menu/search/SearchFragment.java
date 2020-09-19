package com.ihsan.xd.newapp.view.fragment.menu.search;


import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ihsan.xd.newapp.R;
import com.ihsan.xd.newapp.adapter.NewsAdapter;
import com.ihsan.xd.newapp.model.Articles;
import com.ihsan.xd.newapp.model.response.NewsResponse;
import com.ihsan.xd.newapp.view.base.mvp.MvpFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.droidsonroids.gif.GifImageView;

public class SearchFragment extends MvpFragment<SearchPresenter> implements SearchView {


    @BindView(R.id.iv_back)
    AppCompatEditText ivBack;
    @BindView(R.id.img_not_found)
    AppCompatImageView imgNotFound;
    @BindView(R.id.txt_not_found)
    TextView txtNotFound;
    @BindView(R.id.iv_loading)
    GifImageView ivLoading;
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    List<Articles> listNews = new ArrayList<>();
    NewsAdapter newsAdapter;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected void onBindView() {
        initView();
    }

    @Override
    public void showDialog() {
        rvSearch.setVisibility(View.GONE);
        ivLoading.setVisibility(View.VISIBLE);
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
            rvSearch.setVisibility(View.GONE);
            imgNotFound.setVisibility(View.VISIBLE);
            txtNotFound.setVisibility(View.VISIBLE);
        } else {
            rvSearch.setVisibility(View.VISIBLE);
            imgNotFound.setVisibility(View.GONE);
            txtNotFound.setVisibility(View.GONE);
        }
    }

    @Override
    public void getPage(NewsResponse model) {

    }

    @Override
    public void getDataError(String message) {
        Log.d("Error Data Business", message);

    }

    @OnClick(R.id.iv_next)
    public void onViewClicked() {
        if (ivBack.getText().toString().equals("")) {
            ivBack.setError("Please Enter Keyword");
        } else {
            presenter.getSearch(ivBack.getText().toString());
        }
    }

    private void initView() {
        newsAdapter = new NewsAdapter(getContext(), listNews);
        rvSearch.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvSearch.setLayoutManager(mLinearLayoutManager);
        rvSearch.setAdapter(newsAdapter);
    }
}