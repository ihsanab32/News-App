package com.ihsan.xd.newapp;

import androidx.annotation.StringRes;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     :  16/09/2020.
 * ------------------------------
 * This class for
 */
public interface BasicView {

    void showProgressDialog(String message);

    void showProgressDialog(@StringRes int message);

    void dismissProgressDialog();

    void showMessage(String message);

    void showMassage(@StringRes int message);
}
