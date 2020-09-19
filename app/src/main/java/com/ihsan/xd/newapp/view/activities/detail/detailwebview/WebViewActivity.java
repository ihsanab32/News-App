package com.ihsan.xd.newapp.view.activities.detail.detailwebview;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ihsan.xd.newapp.R;
import com.ihsan.xd.newapp.view.base.mvp.MvpActivity;
import com.ihsan.xd.newapp.view.base.ui.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends MvpActivity<BasePresenter> {
    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getActivityView() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @OnClick({R.id.iv_back, R.id.iv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_next:
                break;
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initData() {
        String url = getBundle().getString("url", null);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                showMessage(description);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                showProgressDialog("Please Wait.....");
            }

            @Override
            public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
                dismissProgressDialog();
                return super.onRenderProcessGone(view, detail);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissProgressDialog();
            }
        });
        webView.loadUrl(url);
    }
}