package com.ihsan.xd.newapp.view.fragment.menu;


import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ihsan.xd.newapp.R;
import com.ihsan.xd.newapp.adapter.ViewPagerAdapter;
import com.ihsan.xd.newapp.view.base.mvp.MvpFragment;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;
import com.ihsan.xd.newapp.view.fragment.business.BusinessFragment;
import com.ihsan.xd.newapp.view.fragment.entertainment.EntertainmentFragment;
import com.ihsan.xd.newapp.view.fragment.health.HealthFragment;
import com.ihsan.xd.newapp.view.fragment.science.ScienceFragment;
import com.ihsan.xd.newapp.view.fragment.sport.SportFragment;
import com.ihsan.xd.newapp.view.fragment.technology.TechnologyFragment;

import butterknife.BindView;

public class MenuFragment extends MvpFragment<BasePresenter> {
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_picture,
            R.drawable.ic_monitor,
            R.drawable.ic_money,
            R.drawable.ic_tv,
            R.drawable.ic_trophy,
            R.drawable.ic_doctor
    };
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_menu;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onBindView() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ScienceFragment(), null);
        adapter.addFragment(new TechnologyFragment(), null);
        adapter.addFragment(new BusinessFragment(), null);
        adapter.addFragment(new EntertainmentFragment(), null);
        adapter.addFragment(new SportFragment(), null);
        adapter.addFragment(new HealthFragment(), null);
        viewPager.setAdapter(adapter);
    }
}