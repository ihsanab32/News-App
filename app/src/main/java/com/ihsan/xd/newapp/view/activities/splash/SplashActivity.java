package com.ihsan.xd.newapp.view.activities.splash;


import android.os.Bundle;
import android.os.Handler;

import com.ihsan.xd.newapp.R;
import com.ihsan.xd.newapp.view.activities.menu.MenuActivity;
import com.ihsan.xd.newapp.view.base.mvp.MvpActivity;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;

public class SplashActivity extends MvpActivity<BasePresenter> {
    int interval = 3000;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getActivityView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(this);
        new Handler().postDelayed(this::initView, interval);
    }

    private void initView() {
        startActivityNoHistory(MenuActivity.class);
    }
}