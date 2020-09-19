package com.ihsan.xd.newapp.view.fragment.business;

import com.ihsan.xd.newapp.BuildConfig;
import com.ihsan.xd.newapp.model.response.NewsResponse;
import com.ihsan.xd.newapp.untils.api.NetworkCallback;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     :  17/09/2020.
 * ------------------------------
 * This class for
 */
public class BusinessPresenter extends BasePresenter<BusinessView> {

    BusinessPresenter(BusinessView view) {
        super.attachView(view);
    }

    void getCategoryBusiness() {
        view.showDialog();
        addSubscribe(apiStores.getHeadline(BuildConfig.Country, "business", BuildConfig.API_KEY), new NetworkCallback<NewsResponse>() {
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
        addSubscribe(apiStores.getHeadlinePage(BuildConfig.Country, page, "business", BuildConfig.API_KEY), new NetworkCallback<NewsResponse>() {
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

}
