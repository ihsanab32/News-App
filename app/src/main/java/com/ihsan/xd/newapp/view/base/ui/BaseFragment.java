package com.ihsan.xd.newapp.view.base.ui;



import androidx.fragment.app.Fragment;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     :  16/09/2020.
 * ------------------------------
 * This class for
 */
public class BaseFragment extends Fragment {
    private CompositeSubscription compositeSubscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }
}
