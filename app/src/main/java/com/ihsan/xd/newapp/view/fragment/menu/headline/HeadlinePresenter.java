package com.ihsan.xd.newapp.view.fragment.menu.headline;

import com.ihsan.xd.newapp.BuildConfig;
import com.ihsan.xd.newapp.model.response.NewsResponse;
import com.ihsan.xd.newapp.untils.api.NetworkCallback;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;
import com.ihsan.xd.newapp.view.fragment.health.HealthView;
import com.ihsan.xd.newapp.view.fragment.technology.TechnologyView;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     :  18/09/2020.
 * ------------------------------
 * This class for
 */
public class HeadlinePresenter extends BasePresenter<HeadlineView> {

    HeadlinePresenter(HeadlineView view) {
        super.attachView(view);
    }

    void getHeadline() {
        view.showDialog();
        addSubscribe(apiStores.getHeadlineWithOutCategory(BuildConfig.Country, BuildConfig.API_KEY), new NetworkCallback<NewsResponse>() {
            @Override
            public void onSuccess(NewsResponse model) {
                view.getDataSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                view.getDataError(message);
            }

            @Override
            public void onFinish() {
                view.dismissDialog();
            }
        });
    }

    void getHeadlinePage(int page) {
        addSubscribe(apiStores.getHeadlineWithOutCategoryPage(BuildConfig.Country, page, BuildConfig.API_KEY), new NetworkCallback<NewsResponse>() {
            @Override
            public void onSuccess(NewsResponse model) {
                view.getPage(model);
            }

            @Override
            public void onFailure(String message) {
                view.getDataError(message);
            }

            @Override
            public void onFinish() {
                view.dismissDialog();
            }
        });
    }
}
