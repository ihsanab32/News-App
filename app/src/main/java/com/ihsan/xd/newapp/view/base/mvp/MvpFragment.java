package com.ihsan.xd.newapp.view.base.mvp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;


import com.ihsan.xd.newapp.BasicView;
import com.ihsan.xd.newapp.view.base.ui.BaseFragment;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     : 7/16/2019.
 * ------------------------------
 * This class for
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment implements BasicView {
    protected P presenter;
    private ProgressDialog progressDialog;
    private boolean isPaused;

    @LayoutRes
    protected abstract int getFragmentLayout();

    protected abstract P createPresenter();

    protected abstract void onBindView();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            presenter = createPresenter();
            onBindView();
        }
    public void startActivity(Class target) {
        startActivity(new Intent(getActivity(), target));
    }

    public void startActivityNoHistory(Class targer) {
        Intent intent = new Intent(getActivity(), targer);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showProgressDialog(String message) {
        if (isPaused) return;
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }

        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    @Override
    public void showProgressDialog(@StringRes int message) {
        if (isPaused) return;
        showProgressDialog(getString(message));
    }

    @Override
    public void dismissProgressDialog() {
        if (isPaused) return;
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showMassage(@StringRes int message) {
        if (isPaused) return;
        showMessage(getString(message));
    }

    @Override
    public void showMessage(String message) {
        if (isPaused) return;
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
