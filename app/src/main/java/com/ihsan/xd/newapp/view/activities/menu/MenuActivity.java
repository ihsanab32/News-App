package com.ihsan.xd.newapp.view.activities.menu;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ihsan.xd.newapp.R;
import com.ihsan.xd.newapp.view.base.mvp.MvpActivity;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;
import com.ihsan.xd.newapp.view.fragment.menu.MenuFragment;
import com.ihsan.xd.newapp.view.fragment.menu.headline.HeadlineFragment;
import com.ihsan.xd.newapp.view.fragment.menu.search.SearchFragment;

import butterknife.BindView;

public class MenuActivity extends MvpActivity<BasePresenter> implements  BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigationView)
    BottomNavigationView navigationView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getActivityView() {
        return R.layout.activity_menu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.headline:
                fragment = new HeadlineFragment();
                loadFragment(fragment);
                break;
            case R.id.category:
                fragment = new MenuFragment();
                loadFragment(fragment);
                break;
            case R.id.search:
                fragment = new SearchFragment();
                loadFragment(fragment);
                break;
        }
        return true;
    }

    private void initView(){
        navigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new HeadlineFragment());
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, fragment).commit();
        }
    }
}