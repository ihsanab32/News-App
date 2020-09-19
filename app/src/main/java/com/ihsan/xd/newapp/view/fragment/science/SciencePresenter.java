package com.ihsan.xd.newapp.view.fragment.science;

import com.ihsan.xd.newapp.BuildConfig;
import com.ihsan.xd.newapp.model.response.NewsResponse;
import com.ihsan.xd.newapp.untils.api.NetworkCallback;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     :  18/09/2020.
 * ------------------------------
 * This class for
 */
public class SciencePresenter extends BasePresenter<ScienceView> {

    SciencePresenter(ScienceView view) {
        super.attachView(view);
    }

    void getScience() {
        view.showDialog();
        addSubscribe(apiStores.getHeadline(BuildConfig.Country, "science", BuildConfig.API_KEY), new NetworkCallback<NewsResponse>() {
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
    void getPage(int page){
        addSubscribe(apiStores.getHeadlinePage(BuildConfig.Country, page, "science", BuildConfig.API_KEY), new NetworkCallback<NewsResponse>() {
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
