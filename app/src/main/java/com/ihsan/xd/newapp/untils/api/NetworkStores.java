package com.ihsan.xd.newapp.untils.api;


import com.ihsan.xd.newapp.model.response.NewsResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * Date     : 7/16/2019.
 * ------------------------------
 * This class for
 */
public interface NetworkStores {

    @GET("v2/top-headlines")
    Observable<NewsResponse>
    getHeadline(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );

    @GET("v2/everything")
    Observable<NewsResponse>
    getEverything(
            @Query("q") String q,
            @Query("apiKey") String apiKey
    );

    @GET("v2/top-headlines")
    Observable<NewsResponse>
    getHeadlinePage(
            @Query("country") String country,
            @Query("page") int page,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );

    @GET("v2/everything")
    Observable<NewsResponse>
    getEverythingPage(
            @Query("q") String q,
            @Query("page") int page,
            @Query("apiKey") String apiKey
    );

    @GET("v2/top-headlines")
    Observable<NewsResponse>
    getHeadlineWithOutCategory(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("v2/top-headlines")
    Observable<NewsResponse>
    getHeadlineWithOutCategoryPage(
            @Query("country") String country,
            @Query("page") int page,
            @Query("apiKey") String apiKey
    );
}
