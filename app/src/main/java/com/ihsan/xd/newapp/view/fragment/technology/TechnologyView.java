package com.ihsan.xd.newapp.view.fragment.technology;

import com.ihsan.xd.newapp.model.response.NewsResponse;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     :  18/09/2020.
 * ------------------------------
 * This class for
 */
public interface TechnologyView {
    void showDialog();

    void dismissDialog();

    void getDataSuccess(NewsResponse model);

    void getPage(NewsResponse model);

    void getDataError(String message);
}
