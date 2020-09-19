package com.ihsan.xd.newapp.view.fragment.business;

import com.ihsan.xd.newapp.model.response.NewsResponse;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     :  17/09/2020.
 * ------------------------------
 * This class for
 */
public interface BusinessView {

    void showDialog();

    void dismissDialog();

    void getDataSuccess(NewsResponse model);

    void getPage(NewsResponse model);

    void getDataError(String message);
}
