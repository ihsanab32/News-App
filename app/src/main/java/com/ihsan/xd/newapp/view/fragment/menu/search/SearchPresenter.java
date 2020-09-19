package com.ihsan.xd.newapp.view.fragment.menu.search;

import com.ihsan.xd.newapp.BuildConfig;
import com.ihsan.xd.newapp.model.response.NewsResponse;
import com.ihsan.xd.newapp.untils.api.NetworkCallback;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     :  19/09/2020.
 * ------------------------------
 * This class for
 */
public class SearchPresenter extends BasePresenter<SearchView> {

    SearchPresenter(SearchView view){
        super.attachView(view);
    }

    void getSearch(String q){
        view.showDialog();
        addSubscribe(apiStores.getEverything(q, BuildConfig.API_KEY), new NetworkCallback<NewsResponse>() {
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
