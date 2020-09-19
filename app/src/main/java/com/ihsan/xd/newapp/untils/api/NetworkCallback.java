package com.ihsan.xd.newapp.untils.api;

import android.util.Log;

import retrofit2.HttpException;
import rx.Subscriber;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     : 7/16/2019.
 * ------------------------------
 * This class for
 */
public abstract class NetworkCallback<M> extends Subscriber<M> {
    private static final String TAG = NetworkCallback.class.getName();

    public abstract void onSuccess(M model);

    public abstract void onFailure(String message);

    public abstract void onFinish();

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String message = httpException.getMessage();
            Log.i(TAG, "code : " + code);
            onFailure(message);

        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}
