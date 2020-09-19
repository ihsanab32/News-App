package com.ihsan.xd.newapp.view.base.mvp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.ihsan.xd.newapp.BasicView;
import com.ihsan.xd.newapp.view.base.ui.BaseActivity;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     : 7/16/2019.
 * ------------------------------
 * This class for
 */
@RuntimePermissions
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity implements BasicView {
    private ProgressDialog progressDialog;
    private boolean isPaused;
    protected P presenter;

    protected abstract P createPresenter();

    @LayoutRes
    protected abstract int getActivityView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(getActivityView());
        MvpActivityPermissionsDispatcher.needPermisonWithPermissionCheck(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }

    @Override
    public void showProgressDialog(String message) {
        if (isPaused) return;
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public Bundle getBundle() {
        if (getIntent().getExtras() != null) return getIntent().getExtras();
        else return new Bundle();
    }

    public void startActivity(Class target) {
        startActivity(new Intent(this, target));
    }

    public void startActivityNoHistory(Class targer) {
        Intent intent = new Intent(getApplicationContext(), targer);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void fullScreen(AppCompatActivity appCompatActivity) {
        appCompatActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected void onBindView() {

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MvpActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.VIBRATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void needPermison() {
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.VIBRATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showDeniedNeedPermiison() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage("You must allow permission to use this app");
        dialog.setOnDismissListener(dialogInterface -> MvpActivity.this.finishAffinity());
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (dialogInterface, i) -> dialogInterface.dismiss());
        dialog.show();
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.VIBRATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showNeverAskNeedPermiison() {
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.VIBRATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationaleNeedPermiison(final PermissionRequest request) {
        request.proceed();
    }
}
