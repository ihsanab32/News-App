package com.ihsan.xd.newapp.view.fragment.technology;

import com.ihsan.xd.newapp.BuildConfig;
import com.ihsan.xd.newapp.model.response.NewsResponse;
import com.ihsan.xd.newapp.untils.api.NetworkCallback;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;
import com.ihsan.xd.newapp.view.fragment.sport.SportView;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     :  18/09/2020.
 * ------------------------------
 * This class for
 */
public class TechnologyPresenter extends BasePresenter<TechnologyView> {

    TechnologyPresenter(TechnologyView view) {
        super.attachView(view);
    }

    void getTechnology() {
        view.showDialog();
        addSubscribe(apiStores.getHeadline(BuildConfig.Country, "technology", BuildConfig.API_KEY), new NetworkCallback<NewsResponse>() {
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

    void getPage(int page) {
        addSubscribe(apiStores.getHeadlinePage(BuildConfig.Country, page, "technology", BuildConfig.API_KEY), new NetworkCallback<NewsResponse>() {
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
